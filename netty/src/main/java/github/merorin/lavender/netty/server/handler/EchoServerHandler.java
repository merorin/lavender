package github.merorin.lavender.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Description: 服务器上用于接收消息的handler,用来定义相应入站事件的方法{}
 * 这里只是一个demo,仅仅需要用到少量的方法,所以继承{@link ChannelInboundHandlerAdapter}就够了
 * 在这里我们只需要关注三个方法
 * <ul>
 * <li>{@link #channelRead(ChannelHandlerContext, Object)}对于每个传入的消息都要调用</li>
 * <li>
 * {@link #channelReadComplete(ChannelHandlerContext)}通知{@link io.netty.channel.ChannelInboundHandler}最后一次对{@link #channelRead(ChannelHandlerContext, Object)}
 * 的调用是当前批量读取中的最后一条信息
 * </li>
 * <li>{@link #exceptionCaught(ChannelHandlerContext, Throwable)}在读取操作期间,有异常抛出时调用</li>
 * </ul>
 *
 * {@link io.netty.channel.ChannelHandler.Sharable}标示这一个{@link ChannelHandler}能够被多个{@link io.netty.channel.Channel}安全地共享
 *
 * {@link #channelRead(ChannelHandlerContext, Object)}
 *
 * @author guobin On date 2018/7/15.
 * @version 1.0
 * @since jdk 1.8
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        // 记录消息
        System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
        // 将接受到的消息写入ctx并通过其交给ChannelPipeline,不会将message进行flush操作
        ctx.write(in);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        // 关闭这个channel
        ctx.close();
    }
}
