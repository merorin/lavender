package github.merorin.lavender.netty.client;

import github.merorin.lavender.netty.client.handler.EchoClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * Description:{@link io.netty.channel.ChannelHandler}的初始化器
 *
 * @author guobin On date 2018/7/15.
 * @version 1.0
 * @since jdk 1.8
 */
class EchoClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * 创建共享实例
     */
    private static final EchoClientHandler SHARED = new EchoClientHandler();

    @Override
    protected void initChannel(SocketChannel ch) {
        ch.pipeline().addLast("handler", SHARED);
    }
}
