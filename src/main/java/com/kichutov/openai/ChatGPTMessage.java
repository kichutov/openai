package com.kichutov.openai;

import lombok.Data;

@Data
public class ChatGPTMessage {
    public String role;
    public String content;

    public ChatGPTMessage(String content) {
        this.role = "system";
        this.content = content;
    }

    public ChatGPTMessage() {
    }

    // Конструктор, принимающий все поля класса
    public ChatGPTMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }
}
