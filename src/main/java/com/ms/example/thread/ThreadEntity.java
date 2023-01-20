package com.ms.example.thread;

import com.ms.example.conversation.ConversationEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "thread")
@EqualsAndHashCode
public class ThreadEntity implements Serializable {

    @Id
    private Long id;

    @Column(name = "payload", length = 5120)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private String payload;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conversation_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ConversationEntity conversation;

}