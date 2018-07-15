package github.merorin.lavender.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Description:客户端主类
 *
 * @author guobin On date 2018/7/15.
 * @version 1.0
 * @since jdk 1.8
 */
public class EchoClient {

    private static final int EXPECTED_ARGS_LENGTH = 2;

    private final String host;

    private final int port;

    private EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void start() throws Exception {
        final EventLoopGroup group = new NioEventLoopGroup();
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(this.host, this.port))
                    .handler(new EchoClientChannelInitializer());
            // 异步绑定服务器;调用sync()方法阻塞等待直到绑定完成
            ChannelFuture future = bootstrap.connect().sync();
            // 获取Channel的CloseFuture,并阻塞当前线程直到它完成
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != EXPECTED_ARGS_LENGTH) {
            System.err.println("Usage: " + EchoClient.class.getSimpleName() + " <host> <port>");
            return;
        }

        new EchoClient(args[0], Integer.parseInt(args[1])).start();
    }
}
