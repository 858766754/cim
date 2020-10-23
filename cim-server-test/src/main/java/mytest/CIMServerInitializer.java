package test.service;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * Function:
 *
 * @author crossoverJie
 *         Date: 17/05/2018 18:51
 * @since JDK 1.8
 */
public class CIMServerInitializer extends ChannelInitializer<Channel> {

    private final CIMServerHandle cimServerHandle = new CIMServerHandle() ;

    @Override
    protected void initChannel(Channel ch) throws Exception {

        ch.pipeline()
                //11 秒没有向客户端发送消息就发生心跳
                //.addLast(new IdleStateHandler(11, 0, 0))
                // google Protobuf 编解码
//                .addLast(new ProtobufVarint32FrameDecoder())
//                .addLast(new ProtobufDecoder(CIMRequestProto.CIMReqProtocol.getDefaultInstance()))
//                .addLast(new ProtobufVarint32LengthFieldPrepender())
//                .addLast(new ProtobufEncoder())

                .addLast(new StringEncoder())
                .addLast(new StringDecoder())
                .addLast(cimServerHandle);
    }
}
