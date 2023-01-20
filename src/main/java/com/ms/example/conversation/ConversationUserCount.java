package com.ms.example.conversation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConversationUserCount {

    private long userId;
    private long count;
}
