package com.study.component;

/**
 * @author 朱天乐
 */

import java.io.IOException;
import java.time.Instant;

import com.study.service.DemoService;
import com.study.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

// 使用 @ServerEndpoint 注解表示此类是一个 WebSocket 端点
// 通过 value 注解，指定 websocket 的路径
@ServerEndpoint(value = "/channel/echo")
@Component
public class EchoChannel implements
        ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoChannel.class);

    // 全局静态变量，保存 ApplicationContext
    private static ApplicationContext applicationContext;

    private Session session;

    private DemoService demoService;


    // 保存 Spring 注入的 ApplicationContext 到静态变量
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        EchoChannel.applicationContext = applicationContext;
    }

    // 收到消息
    @OnMessage
    public void onMessage(String message) throws IOException {

        LOGGER.info("[websocket] 收到消息：id={}，message={}", this.session.getId(), message);

        if (message.equalsIgnoreCase("bye")) {
            // 由服务器主动关闭连接。状态码为 NORMAL_CLOSURE（正常关闭）。
            this.session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Bye"));
            ;
            return;
        }


        this.session.getAsyncRemote().sendText("[" + Instant.now().toEpochMilli() + "] Hello " + message);
    }

    // 连接打开
    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig) {

        // 保存 session 到对象
        this.session = session;


        this.demoService = EchoChannel.applicationContext.getBean(DemoService.class);

        demoService.ok();

        LOGGER.info("[websocket] 新的连接：id={}", this.session.getId());
    }

    // 连接关闭
    @OnClose
    public void onClose(CloseReason closeReason) {
        LOGGER.info("[websocket] 连接断开：id={}，reason={}", this.session.getId(), closeReason);
    }

    // 连接异常
    @OnError
    public void onError(Throwable throwable) throws IOException {

        LOGGER.info("[websocket] 连接异常：id={}，throwable={}", this.session.getId(), throwable.getMessage());

        // 关闭连接。状态码为 UNEXPECTED_CONDITION（意料之外的异常）
        this.session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, throwable.getMessage()));
    }

}
