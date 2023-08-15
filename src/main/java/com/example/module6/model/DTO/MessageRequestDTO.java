package com.example.module6.model.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageRequestDTO {
    private String content;
    private Long senderId;
    private Long receiverId;
}
