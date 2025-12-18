package com.ruoyi.ws;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.utils.MessageUtils;
import com.ruoyi.ws.pojo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket 客服端点 - 适配微服务无状态版本
 */
@ServerEndpoint(value = "/chat")
@Component
public class ChatEndpoint {

    private static final Logger log = LoggerFactory.getLogger(ChatEndpoint.class);

    // 静态注入 TokenService，用于解析前端传来的 token 参数
    private static TokenService tokenService;

    @Autowired
    public void setTokenService(TokenService tokenService) {
        ChatEndpoint.tokenService = tokenService;
    }

    private static final Map<String, Session> onlineUsers = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        String username = null;
        try {
            // 1. 尝试从 URL 参数中获取 token (ws://host/chat?token=xxxx)
            String queryString = session.getQueryString();
            if (StringUtils.isNotEmpty(queryString) && queryString.contains("token=")) {
                String token = queryString.substring(queryString.indexOf("token=") + 6);
                LoginUser loginUser = tokenService.getLoginUser(token);
                if (loginUser != null) {
                    username = loginUser.getUsername();
                }
            }

            // 2. 如果 URL 没带 token，尝试从上下文获取 (虽然微服务握手阶段通常获取不到)
            if (StringUtils.isEmpty(username)) {
                username = SecurityUtils.getUsername();
            }

            if (StringUtils.isNotEmpty(username)) {
                onlineUsers.put(username, session);
                log.info("用户[{}]连接成功，当前在线：{}", username, onlineUsers.size());

                // 广播好友列表
                String message = MessageUtils.getMessage(true, null, getFriends());
                broadcastAllUsers(message);
            } else {
                log.error("WebSocket连接失败：无法从Token识别用户信息");
                session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "身份校验失败"));
            }
        } catch (Exception e) {
            log.error("WebSocket建立连接异常", e);
        }
    }

    public Set<String> getFriends() {
        return onlineUsers.keySet();
    }

    private void broadcastAllUsers(String message) {
        onlineUsers.forEach((user, session) -> {
            if (session.isOpen()) {
                session.getAsyncRemote().sendText(message);
            }
        });
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            Message msg = JSON.parseObject(message, Message.class);
            String toName = msg.getToName();
            Session targetSession = onlineUsers.get(toName);

            // 消息发送者的用户名获取
            String fromName = "";
            for (Map.Entry<String, Session> entry : onlineUsers.entrySet()) {
                if (entry.getValue().equals(session)) {
                    fromName = entry.getKey();
                    break;
                }
            }

            if (targetSession != null && targetSession.isOpen()) {
                String resultMsg = MessageUtils.getMessage(false, fromName, msg.getMessage());
                targetSession.getAsyncRemote().sendText(resultMsg);
            }
        } catch (Exception e) {
            log.error("消息处理异常", e);
        }
    }

    @OnClose
    public void onClose(Session session) {
        String username = null;
        for (Map.Entry<String, Session> entry : onlineUsers.entrySet()) {
            if (entry.getValue().equals(session)) {
                username = entry.getKey();
                break;
            }
        }
        if (username != null) {
            onlineUsers.remove(username);
            log.info("用户[{}]断开连接", username);
            broadcastAllUsers(MessageUtils.getMessage(true, null, getFriends()));
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("WebSocket错误: {}", error.getMessage());
    }
}