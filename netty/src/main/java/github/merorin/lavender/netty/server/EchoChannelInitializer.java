package github.merorin.lavender.netty.server;

import github.merorin.lavender.netty.server.handler.EchoServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * Description:{@link io.netty.channel.ChannelHandler}的初始化器
 *
 * netty推荐为每个Channel都创建一个ChannelHandler。
 * 这里的demo采用的并不是netty所推荐的做法,而是让所有的Channel共享一个ChannelHandler实例。
 * EchoServerHandler被标注@Sharable,我们可以线程安全地使用同一个实例
 *
 * @author guobin On date 2018/7/15.
 * @version 1.0
 * @since jdk 1.8
 */
class EchoChannelInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * 创建共享实例
     */
    private static final EchoServerHandler SHARED = new EchoServerHandler();

    @Override
    protected void initChannel(SocketChannel ch) {
        ch.pipeline().addLast("handler", SHARED);
    }
}
