package github.merorin.lavender.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.*;

import java.util.Arrays;
import java.util.List;

/**
 * Description:WebSocket数据转换器
 *
 * @author guobin On date 2018/7/22.
 * @version 1.0
 * @since jdk 1.8
 */
public class WebSocketConverterHandler
        extends MessageToMessageCodec<WebSocketFrame, WebSocketConverterHandler.MyWebSocketFrame> {


    @Override
    protected void encode(ChannelHandlerContext ctx, MyWebSocketFrame msg, List<Object> out) {
        final ByteBuf payload = msg.getData().duplicate().retain();
        out.add(msg.getType().getWebSocketFrame(payload));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, WebSocketFrame msg, List<Object> out) {
        out.add(MyWebSocketFrame.FrameType.generateMyWebSocketFrame(msg));
    }

    static final class MyWebSocketFrame {

        public enum FrameType {

            /**
             * web socket包含二进制数据
             */
            BINARY {
                @Override
                WebSocketFrame getWebSocketFrame(ByteBuf payload) {
                    return new BinaryWebSocketFrame(payload);
                }

                @Override
                boolean isTypeMatched(WebSocketFrame frame) {
                    return frame instanceof BinaryWebSocketFrame;
                }

                @Override
                MyWebSocketFrame getMyWebSocketFrame(ByteBuf payload) {
                    return new MyWebSocketFrame(BINARY, payload);
                }
            },

            /**
             * 关闭连接的web-socket帧
             */
            CLOSE {
                @Override
                WebSocketFrame getWebSocketFrame(ByteBuf payload) {
                    return new CloseWebSocketFrame(true, 0, payload);
                }

                @Override
                boolean isTypeMatched(WebSocketFrame frame) {
                    return frame instanceof CloseWebSocketFrame;
                }

                @Override
                MyWebSocketFrame getMyWebSocketFrame(ByteBuf payload) {
                    return new MyWebSocketFrame(CLOSE, payload);
                }
            },

            /**
             * web-socket为一个心跳检测
             */
            PING {
                @Override
                WebSocketFrame getWebSocketFrame(ByteBuf payload) {
                    return new PingWebSocketFrame(payload);
                }

                @Override
                boolean isTypeMatched(WebSocketFrame frame) {
                    return frame instanceof PingWebSocketFrame;
                }

                @Override
                MyWebSocketFrame getMyWebSocketFrame(ByteBuf payload) {
                    return new MyWebSocketFrame(PING, payload);
                }
            },

            /**
             * web-socket为一个心跳响应
             */
            PONG {
                @Override
                WebSocketFrame getWebSocketFrame(ByteBuf payload) {
                    return new PongWebSocketFrame(payload);
                }

                @Override
                boolean isTypeMatched(WebSocketFrame frame) {
                    return frame instanceof PongWebSocketFrame;
                }

                @Override
                MyWebSocketFrame getMyWebSocketFrame(ByteBuf payload) {
                    return new MyWebSocketFrame(PONG, payload);
                }
            },

            /**
             * web-socket携带文本数据
             */
            TEXT {
                @Override
                WebSocketFrame getWebSocketFrame(ByteBuf payload) {
                    return new TextWebSocketFrame(payload);
                }

                @Override
                boolean isTypeMatched(WebSocketFrame frame) {
                    return frame instanceof TextWebSocketFrame;
                }

                @Override
                MyWebSocketFrame getMyWebSocketFrame(ByteBuf payload) {
                    return new MyWebSocketFrame(TEXT, payload);
                }
            },

            /**
             * 携带文本或者二进制数据的web-socket帧
             */
            CONTINUATION {
                @Override
                WebSocketFrame getWebSocketFrame(ByteBuf payload) {
                    return new ContinuationWebSocketFrame(payload);
                }

                @Override
                boolean isTypeMatched(WebSocketFrame frame) {
                    return frame instanceof ContinuationWebSocketFrame;
                }

                @Override
                MyWebSocketFrame getMyWebSocketFrame(ByteBuf payload) {
                    return new MyWebSocketFrame(CONTINUATION, payload);
                }
            };

            static MyWebSocketFrame generateMyWebSocketFrame(WebSocketFrame frame) {
                final ByteBuf payload = frame.content().duplicate().retain();
                return Arrays.stream(FrameType.values())
                        .filter(type -> type.isTypeMatched(frame))
                        .findFirst()
                        .map(type -> type.getMyWebSocketFrame(payload))
                        .orElseThrow(() -> new IllegalStateException("Unsupported web-socket msg " + frame));
            }

            abstract WebSocketFrame getWebSocketFrame(ByteBuf payload);

            abstract boolean isTypeMatched(WebSocketFrame frame);

            abstract MyWebSocketFrame getMyWebSocketFrame(ByteBuf payload);
        }

        private final FrameType type;

        private final ByteBuf data;

        MyWebSocketFrame(FrameType type, ByteBuf data) {
            this.type = type;
            this.data = data;
        }

        FrameType getType() {
            return type;
        }

        ByteBuf getData() {
            return data;
        }
    }
}
