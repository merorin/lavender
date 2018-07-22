package github.merorin.lavender.duplex;

import github.merorin.lavender.decoder.ByteToCharDecoder;
import github.merorin.lavender.encoder.CharToByteEncoder;
import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * Description:byte 和 char的双向编码/解码器
 *
 * @author guobin On date 2018/7/22.
 * @version 1.0
 * @since jdk 1.8
 */
public class CombineByteCharCodec extends CombinedChannelDuplexHandler<ByteToCharDecoder, CharToByteEncoder> {

    public CombineByteCharCodec() {
        // 父类实现
        super(new ByteToCharDecoder(), new CharToByteEncoder());
    }
}
