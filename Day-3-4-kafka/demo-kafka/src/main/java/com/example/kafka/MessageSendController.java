package com.example.kafka;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageSendController {
    private final StreamBridge streamBridge;

    public MessageSendController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }
    @GetMapping("/eco/{message}")
    public String eco(@PathVariable String message) {
        streamBridge.send("uppercase-in-0", message);
        return message;
    }
}
