package mytest;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Function:
 *
 * @author crossoverJie
 * Date: 22/05/2018 14:19
 * @since JDK 1.8
 */
@Slf4j
@Component
public class CIMClient {

    private EventLoopGroup group = new NioEventLoopGroup(0, new DefaultThreadFactory("cim-work"));

    private Channel channel;

    private String ip = "10.138.18.219";

    private int port = 9999;

    @PostConstruct
    public void startClient() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new CIMClientHandleInitializer())
        ;
        ChannelFuture future = null;
        try {
            future = bootstrap.connect(ip, port).sync();

            future.addListener(f->{
                if(f.isSuccess()){
                    log.info("连接成功");
                }else{
                    log.info("连接失败");
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (future.isSuccess()) {
            System.out.println("启动 cim client 成功");
        }
        channel =  future.channel();
    }

    public Channel getChannel(){
        return channel;
    }

}
