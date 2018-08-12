package github.merorin.lavender.netty.server.handler;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Description:
 *
 * @author guobin On date 2018/7/31.
 * @version 1.0
 * @since jdk 1.8
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final String FILE_PREFIX = "file:";
    private static final String INDEX_HTML = "index.html";

    private final String wsUri;
    private static final File INDEX;

    static {
        URL location = HttpRequestHandler.class.getProtectionDomain()
                .getCodeSource().getLocation();
        try {
            String path = location.toURI() + INDEX_HTML;
            path = !path.contains(FILE_PREFIX) ? path : path.substring(FILE_PREFIX.length());
            INDEX = new File(path);
        } catch (URISyntaxException ex) {
            throw new IllegalStateException("Unable to locate index.html", ex);
        }
    }

    public HttpRequestHandler(String wsUri) {
        this.wsUri = wsUri;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        if (this.wsUri.equalsIgnoreCase(msg.uri())) {
            // 如果请求了WebSocket协议升级,则增加引用计数(通过调用retain方法),并将它传给下一个ChannelInboundHandler
            // 调用retain方法的原因是,调用channelRead()方法完成之后,它将调用FullHttpRequest对象上的release方法释放它的资源
            ctx.fireChannelRead(msg.retain());
        } else {
            // 处理100 continue 请求以符合HTTP1.1规范
            // 100-continue 是用于客户端在发送 post 数据给服务器时，征询服务器情况，看服务器是否处理 post 的数据，如果不处理，
            // 客户端则不上传 post 是数据，反之则上传。在实际应用中，通过 post 上传大数据时，才会使用到 100-continue 协议。
            if (HttpUtil.is100ContinueExpected(msg)) {
                send100Continue(ctx);
            }
            // 读取html
            RandomAccessFile file = new RandomAccessFile(INDEX, "r");
            HttpResponse response = new DefaultFullHttpResponse(msg.protocolVersion(), HttpResponseStatus.OK);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; Charset=UTF-8");
            final boolean keepAlive = HttpUtil.isKeepAlive(msg);
            if (keepAlive) {
                response.headers().set(HttpHeaderNames.CONTENT_LENGTH, file.length());
                response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            }
            // 将HttpResponse写到客户端
            ctx.write(response);
            // 将index.html写到客户端
            if (ctx.pipeline().get(SslHandler.class) == null) {
                ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
            } else {
                ctx.write(new ChunkedNioFile(file.getChannel()));
            }
            ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            // 如果没有请求keep-alive,则在写操作完成后关闭channel
            if (!keepAlive) {
                future.addListener(ChannelFutureListener.CLOSE);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private static void send100Continue(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
        ctx.writeAndFlush(response);
    }
}
