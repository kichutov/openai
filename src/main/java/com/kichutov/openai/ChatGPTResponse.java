package com.kichutov.openai;

import lombok.Data;

import java.util.List;

@Data
class ChatGPTResponse {
    public List<ChatGPTChoice> choices;
    public Long created;
    public String id;
    public String model;
    public String object;
    public ChatGPTUsage usage;
}
