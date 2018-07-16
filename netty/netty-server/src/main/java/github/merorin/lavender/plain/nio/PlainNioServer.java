package github.merorin.lavender.plain.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Description:这是未使用netty的非阻塞网络编程
 *
 * @author guobin On date 2018/7/16.
 * @version 1.0
 * @since jdk 1.8
 */
public class PlainNioServer {

    public void serve(int port) throws IOException {
        final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        serverSocket.bind(address);

        // 打开Selector来处理channel
        Selector selector = Selector.open();
        // 将ServerSocket注册到Selector以接收连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        final ByteBuffer msg = ByteBuffer.wrap("Hi!\r\n".getBytes());
        while (!Thread.interrupted()) {
            try {
                selector.select();
            } catch (IOException ex) {
                ex.printStackTrace();
                break;
            }
            // 获取所有接收时间的SelectionKey实例
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                try (SelectableChannel channel = key.channel()) {
                    // 检查时间似乎否是一个新的已经就绪可以被接收的连接
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) channel;
                        try (SocketChannel client = server.accept()) {
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());
                            System.out.println("Accepted connection from " + client);
                        }
                    }
                    // 检查socket是否已经准备好写数据
                    if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) channel;
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        while (buffer.hasRemaining()) {
                            if (client.write(buffer) == 0) {
                                break;
                            }
                        }
                    }
                } catch (IOException ex) {
                    key.cancel();
                    ex.printStackTrace();
                }
            }
        }
    }
}
