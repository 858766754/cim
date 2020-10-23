package mytest;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * Function:
 *
 * @author crossoverJie
 *         Date: 16/02/2018 18:09
 * @since JDK 1.8
 */
@Slf4j
@ChannelHandler.Sharable
public class CIMClientHandle extends SimpleChannelInboundHandler<String> {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        log.info("心跳");

        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //客户端和服务端建立连接时调用
        log.info("cim server connect success!");
        ctx.writeAndFlush("11111".getBytes()).addListeners((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                log.info("发送成功");
            }else{
                log.info("发送失败" );
            }
        }) ;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端断开了，请重新连接！");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info("收到消息："+msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //异常时断开连接
        log.info("异常", cause);
        ctx.close() ;
    }
}
