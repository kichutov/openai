package com.kichutov.openai;

import lombok.ToString;

@ToString
class ChatGPTUsage {
    public Integer completion_tokens;
    public Integer prompt_tokens;
    public Integer total_tokens;
}
