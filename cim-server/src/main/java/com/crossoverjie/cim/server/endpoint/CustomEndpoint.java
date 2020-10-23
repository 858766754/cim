package com.crossoverjie.cim.server.endpoint;

import com.crossoverjie.cim.server.util.SessionSocketHolder;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.Map;

/**
 * Function: 自定义端点监控
 *
 * @author crossoverJie
 *         Date: 17/04/2018 14:47
 * @since JDK 1.8
 */
@Endpoint(id = "custom_session")
public class CustomEndpoint{

    @ReadOperation
    public Map<Long, NioSocketChannel> invoke() {
        return SessionSocketHolder.getMAP();
    }
}
