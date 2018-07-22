package github.merorin.lavender.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.TooLongFrameException;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Description:{@link FrameChunkDecoder}的测试类
 *
 * @author guobin On date 2018/7/22.
 * @version 1.0
 * @since jdk 1.8
 */
public class FrameChunkDecoderTest {

    private FrameChunkDecoder decoder;

    @Before
    public void setUp() {
        this.decoder = new FrameChunkDecoder(3);
    }

    @Test
    public void testFramesDecoded() {
        ByteBuf byteBuf = Unpooled.buffer();
        IntStream.range(0, 9).forEach(byteBuf::writeInt);
        ByteBuf input = byteBuf.duplicate();

        EmbeddedChannel channel = new EmbeddedChannel(this.decoder);
        // 向它们写入2字节,并断言他们将会产生一个新帧
        assertTrue(channel.writeInbound(input.readBytes(2)));
        try {
            // 写入一个4字节大小的帧,并捕获预期的异常
            channel.writeInbound(input.readBytes(4));
            // 如果上面一步没有抛出异常,那么测试失败,因为我们限定最多只能读取3字节,这里读取了4字节,应该抛出异常的
            fail();
        } catch (TooLongFrameException ex) {
            // 这个情况是我们所期望的,不用做任何事
        }

        // 写入剩余的3字节,并断言会产生一个有效帧
        assertTrue(channel.writeInbound(input.readBytes(3)));
        assertTrue(channel.finish());

        // read frames
        // 读取第一次写入的2字节帧
        ByteBuf read = channel.readInbound();
        assertEquals(byteBuf.readSlice(2), read);
        read.release();

        // 由于第二次写入的4帧太长被丢弃,所以必须跳过3-6帧,第二次读取的是最后写入的3帧
        read = channel.readInbound();
        assertEquals(byteBuf.skipBytes(4).readSlice(3), read);
        read.release();
        byteBuf.release();
    }
}