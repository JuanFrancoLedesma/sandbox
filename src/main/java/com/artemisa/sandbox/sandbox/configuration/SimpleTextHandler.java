package com.artemisa.sandbox.sandbox.configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class SimpleTextHandler extends TextWebSocketHandler {

    private static final Set<WebSocketSession> sessions =
            Collections.synchronizedSet(new HashSet<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("Cliente conectado: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
        sessions.remove(session);
        System.out.println("Cliente desconectado: " + session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
        session.sendMessage(new TextMessage("Eco: " + message.getPayload()));
    }

    public void sendBroadcast(String message) throws Exception{
        synchronized (sessions){
            for (WebSocketSession session: sessions){
                if(session.isOpen()){
                    session.sendMessage(new TextMessage(message));
                }
            }
        }
    }
}
