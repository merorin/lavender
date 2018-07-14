package github.merorin.lavender.netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Description:客户端用来处理数据的{@link io.netty.channel.ChannelInboundHandler}
 *
 * @author guobin On date 2018/7/15.
 * @version 1.0
 * @since jdk 1.8
 */
@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    /**
     * 当被通知的{@link io.netty.channel.Channel}是活跃之时,发送一条消息
     * 在一个连接被建立时被调用,确保数据被尽可能快地写入服务器
     * @param ctx 用于{@link ChannelHandler}和{@link io.netty.channel.ChannelPipeline}之间通讯的上下文
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 记录已接收消息的转储
     * 每次接收到数据都会调用这个方法。
     * 服务器发送的消息可能会被分块接收。如果服务器一次性附送了5 byte的数据,这里不会保证5 byte的数据被一次性接收,可能会被调用多次
     * 这里TCP保证了字节数组将会按照服务器发送的顺序接收
     * @param ctx 用于{@link ChannelHandler}和{@link io.netty.channel.ChannelPipeline}之间通讯的上下文
     * @param msg 消息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        System.out.println("Client received: " + msg.toString(CharsetUtil.UTF_8));
    }
}
