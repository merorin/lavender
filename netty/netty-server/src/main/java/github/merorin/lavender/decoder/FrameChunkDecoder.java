package github.merorin.lavender.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * Description: 用于测试当读取的帧超过最大帧时抛出异常的解码器
 *
 * @author guobin On date 2018/7/22.
 * @version 1.0
 * @since jdk 1.8
 */
public class FrameChunkDecoder extends ByteToMessageDecoder {

    private final int maxFrameSize;

    public FrameChunkDecoder(int maxFrameSize) {
        this.maxFrameSize = maxFrameSize;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        int readableBytes = in.readableBytes();
        if (readableBytes > this.maxFrameSize) {
            // discard the bytes
            in.clear();
            throw new TooLongFrameException();
        }
        out.add(in.readBytes(readableBytes));
    }
}
