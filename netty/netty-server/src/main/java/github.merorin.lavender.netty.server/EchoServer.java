package github.merorin.lavender.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Description:服务端的bootstrap-server
 *
 * @author guobin On date 2018/7/15.
 * @version 1.0
 * @since jdk 1.8
 */
public class EchoServer {

    private static final int EXPECTED_ARGS_LENGTH = 1;

    private final int port;

    private EchoServer(int port) {
        this.port = port;
    }

    private void start() throws Exception {
        final EventLoopGroup group = new NioEventLoopGroup();
        try {
            final ServerBootstrap sb = new ServerBootstrap();
            sb.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(this.port))
                    .childHandler(new EchoServerChannelInitializer());
            // 异步绑定服务器;调用sync()方法阻塞等待直到绑定完成
            ChannelFuture future = sb.bind().sync();
            // 获取Channel的CloseFuture,并阻塞当前线程直到它完成
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    /**
     * 用于引导服务器
     * @param args 参数数组
     * @throws Exception 抛出异常
     */
    public static void main(String[] args) throws Exception {
        if (args.length != EXPECTED_ARGS_LENGTH) {
            System.err.println("Usage: " + EchoServer.class.getSimpleName() + " <port>");
            return;
        }
        // 设置端口,启动服务器
        new EchoServer(Integer.parseInt(args[0])).start();
    }
}
