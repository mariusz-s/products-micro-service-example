package com.ms.example.product;

import com.ms.example.conversation.ConversationEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "product")
public class ProductEntity implements Serializable {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(
            mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @EqualsAndHashCode.Exclude
    private List<ConversationEntity> conversations = new ArrayList<>();

    public void setConversations(List<ConversationEntity> conversations) {
        if (conversations == null) {
            return;
        }
        this.conversations = new ArrayList<>();
        conversations.forEach(this::addConversation);
    }

    private void addConversation(ConversationEntity conversation) {
        if (conversation == null) {
            return;
        }
        conversation.setProduct(this);
        conversations.add(conversation);
    }
}
