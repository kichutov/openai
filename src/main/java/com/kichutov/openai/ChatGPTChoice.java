package com.kichutov.openai;

import lombok.ToString;

@ToString
class ChatGPTChoice {
    public String finish_reason;
    public Integer index;
    public ChatGPTMessage message;
}
