package github.merorin.lavender.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Description: {@link FixedLengthFrameDecoder}的测试类
 *
 * @author guobin On date 2018/7/22.
 * @version 1.0
 * @since jdk 1.8
 */
public class FixedLengthFrameDecoderTest {

    private FixedLengthFrameDecoder decoder;

    @Before
    public void setUp() {
        this.decoder = new FixedLengthFrameDecoder(3);
    }

    @Test
    public void testFrameDecodeTest1() {
        // 创建一个byte,存储9字节
        ByteBuf byteBuf = Unpooled.buffer();
        IntStream.range(0, 9).forEach(byteBuf::writeByte);
        final ByteBuf input = byteBuf.duplicate();
        final EmbeddedChannel channel = new EmbeddedChannel(this.decoder);

        // 将数据写入EmbeddedChannel
        assertTrue(channel.writeInbound(input.retain()));
        assertTrue(channel.finish());

        // 读取数据,并且验证是否有3帧,其中每帧都有3字节
        ByteBuf read = channel.readInbound();
        assertEquals(byteBuf.readSlice(3), read);
        read.release();

        read = channel.readInbound();
        assertEquals(byteBuf.readSlice(3), read);
        read.release();

        read = channel.readInbound();
        assertEquals(byteBuf.readSlice(3), read);
        read.release();

        assertNull(channel.readInbound());
        byteBuf.release();
    }

    @Test
    public void testFrameDecodeTest2() {
        // 创建一个byte,存储9字节
        ByteBuf byteBuf = Unpooled.buffer();
        IntStream.range(0, 9).forEach(byteBuf::writeByte);
        final ByteBuf input = byteBuf.duplicate();
        final EmbeddedChannel channel = new EmbeddedChannel(this.decoder);

        // 只读取2字节,不会返回一个完整的帧,所以会是false
        assertFalse(channel.writeInbound(input.readBytes(2)));
        assertTrue(channel.writeInbound(input.readBytes(7)));

        assertTrue(channel.finish());

        // 读取数据,并且验证是否有3帧,其中每帧都有3字节
        ByteBuf read = channel.readInbound();
        assertEquals(byteBuf.readSlice(3), read);
        read.release();

        read = channel.readInbound();
        assertEquals(byteBuf.readSlice(3), read);
        read.release();

        read = channel.readInbound();
        assertEquals(byteBuf.readSlice(3), read);
        read.release();

        assertNull(channel.readInbound());
        byteBuf.release();
    }

}