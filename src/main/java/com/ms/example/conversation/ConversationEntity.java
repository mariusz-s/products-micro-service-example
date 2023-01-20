package com.ms.example.conversation;

import com.ms.example.product.ProductEntity;
import com.ms.example.thread.ThreadEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "conversation")
public class ConversationEntity implements Serializable {

    @Id
    private Long id;

    @Column(name = "from_user", length = 512)
    private String fromUser;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "received")
    private LocalDateTime received;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ProductEntity product;

    @OneToMany(
            mappedBy = "conversation",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<ThreadEntity> threads = new ArrayList<>();

    public void setThreads(List<ThreadEntity> threads) {
        if (this.threads == null) {
            this.threads = new ArrayList<>();
        }
        threads.forEach(this::addThread);
    }

    private void addThread(ThreadEntity thread) {
        if (thread == null) {
            return;
        }
        thread.setConversation(this);
        threads.add(thread);
    }
}