package test.service;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * Function:
 *
 * @author crossoverJie
 *         Date: 17/05/2018 18:52
 * @since JDK 1.8
 */
@Slf4j
@ChannelHandler.Sharable
public class CIMServerHandle extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.warn("建立连接:"+ctx.channel().remoteAddress());
        ctx.writeAndFlush("hello").addListeners((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                log.info("发送成功");
            }else{
                log.info("发送失败" );
            }
        }) ;
    }

    /**
     * 取消绑定
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.warn("[{}]触发 channelInactive 掉线!");
        ctx.channel().close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        log.info("定时检测客户端端是否存活");

        super.userEventTriggered(ctx, evt);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info("收到msg={}", msg.toString());

        ctx.writeAndFlush("1111").addListeners((ChannelFutureListener) future -> {
            if (!future.isSuccess()) {
                log.error("IO error,close Channel");
                future.channel().close();
            }
        }) ;

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error(cause.getMessage(), cause);
    }

}
