package com.ms.example.conversation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<ConversationEntity, Long> {

    long countByProductId(long productId);

    @Query("select new com.ms.example.conversation.ConversationUserCount(cv.userId, count(cv.id)) from ConversationEntity cv " +
            "where cv.product.id = :id and cv.userId is not null group by cv.userId order by count(cv.id) desc")
    List<ConversationUserCount> findMostPopularUsersForProduct(long id);
}
