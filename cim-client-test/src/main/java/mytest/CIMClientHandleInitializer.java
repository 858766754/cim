package mytest;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * Function:
 *
 * @author crossoverJie
 *         Date: 23/02/2018 22:47
 * @since JDK 1.8
 */
public class CIMClientHandleInitializer extends ChannelInitializer<Channel> {

    private final CIMClientHandle cimClientHandle = new CIMClientHandle();

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline()
                //10 秒没发送消息 将IdleStateHandler 添加到 ChannelPipeline 中
                .addLast(new IdleStateHandler(0, 10, 0))

                //心跳解码
                //.addLast(new HeartbeatEncode())

                // google Protobuf 编解码
                //拆包解码
//                .addLast(new ProtobufVarint32FrameDecoder())
//                .addLast(new ProtobufDecoder(CIMResponseProto.CIMResProtocol.getDefaultInstance()))
                //
                //拆包编码
//                .addLast(new ProtobufVarint32LengthFieldPrepender())
//                .addLast(new ProtobufEncoder())

                .addLast(new StringEncoder())
                .addLast(new StringDecoder())
                .addLast(cimClientHandle)
        ;
    }
}
