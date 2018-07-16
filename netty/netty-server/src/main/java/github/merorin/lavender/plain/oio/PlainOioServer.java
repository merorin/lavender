package github.merorin.lavender.plain.oio;

import github.merorin.lavender.ExecutorServiceUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;

/**
 * Description:这是未使用netty的阻塞网络编程
 *
 * @author guobin On date 2018/7/16.
 * @version 1.0
 * @since jdk 1.8
 */
public class PlainOioServer {

    private static final ExecutorService EXECUTOR_SERVICE =
            ExecutorServiceUtils.getOrCreateThreadPoolExecutor("PlainOioServiceWorker-");

    public void serve(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        while (!Thread.interrupted()) {
            try (Socket clientSocket = socket.accept()) {
                System.out.println("Accepted connection from " + clientSocket);
                EXECUTOR_SERVICE.submit(() -> {
                    try (OutputStream out = clientSocket.getOutputStream()) {
                        out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
