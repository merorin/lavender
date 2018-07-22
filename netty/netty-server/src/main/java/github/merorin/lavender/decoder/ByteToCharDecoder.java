package github.merorin.lavender.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Description:字节码转字符的解码器
 *
 * @author guobin On date 2018/7/22.
 * @version 1.0
 * @since jdk 1.8
 */
public class ByteToCharDecoder extends ByteToMessageDecoder {

    private static final int CHAR_LENGTH = 2;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        while (in.readableBytes() >= CHAR_LENGTH) {
            out.add(in.readChar());
        }
    }
}
