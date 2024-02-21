package com.codecampn.spring.ai.rag.app.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatResponseDto {

    private String systemMessage;
    private String userMessage;
    private String answer;
}
