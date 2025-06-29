package com.study.config;

/**
 * @author 朱天乐
 */
import com.study.component.EchoChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


@Configuration
public class WebSocketConfiguration {

    @Bean
    public ServerEndpointExporter serverEndpointExporter (){

        ServerEndpointExporter exporter = new ServerEndpointExporter();

        // 手动注册 WebSocket 端点可以用注解替代（在EchoChannel上加Component）
//        exporter.setAnnotatedEndpointClasses(EchoChannel.class);

        return exporter;
    }
}


