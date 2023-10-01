package com.kichutov.openai;

import java.util.List;

public class ChatGPTRequest {
    public List<ChatGPTMessage> messages;
    public String model;

    public ChatGPTRequest(List<ChatGPTMessage> messages, String model) {
        this.messages = messages;
        this.model = model;
    }
}
