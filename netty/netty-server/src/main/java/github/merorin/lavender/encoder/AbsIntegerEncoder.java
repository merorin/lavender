package github.merorin.lavender.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Description:netty绝对值编码器
 *
 * @author guobin On date 2018/7/22.
 * @version 1.0
 * @since jdk 1.8
 */
public class AbsIntegerEncoder extends MessageToMessageEncoder<ByteBuf> {

    private static final int INT_LENGTH = 4;

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        while (msg.readableBytes() >= INT_LENGTH) {
            int value = Math.abs(msg.readInt());
            out.add(value);
        }
    }
}
