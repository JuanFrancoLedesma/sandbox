package com.artemisa.sandbox.sandbox.controllers;

import com.artemisa.sandbox.sandbox.configuration.SimpleTextHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/broadcast/ws")
public class BroadcastController {
    private final SimpleTextHandler handler;

    public BroadcastController(SimpleTextHandler handler){
        this.handler = handler;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody String message) throws Exception{
        handler.sendBroadcast(message);
        return "Mensaje enviado a todos los clientes conectados";
    }
}
