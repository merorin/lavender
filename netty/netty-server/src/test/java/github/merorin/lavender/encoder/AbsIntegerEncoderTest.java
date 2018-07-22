package github.merorin.lavender.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Description:{@link AbsIntegerEncoder}的测试类
 *
 * @author guobin On date 2018/7/22.
 * @version 1.0
 * @since jdk 1.8
 */
public class AbsIntegerEncoderTest {

    private AbsIntegerEncoder encoder;

    @Before
    public void setUp() {
        this.encoder = new AbsIntegerEncoder();
    }

    @Test
    public void testEncoded1() {
        final ByteBuf byteBuf = Unpooled.buffer();
        IntStream.range(0, 10).map(i -> -i).forEach(byteBuf::writeInt);

        EmbeddedChannel channel = new EmbeddedChannel(this.encoder);
        // 写入byteBuf,断言调用readOutbound方法将会产生数据
        assertTrue(channel.writeOutbound(byteBuf));
        // 标记该channel为已完成状态
        assertTrue(channel.finish());

        // read bytes
        IntStream.range(0, 10).forEach(i -> assertEquals(i, (int) channel.readOutbound()));

        assertNull(channel.readOutbound());
    }
}