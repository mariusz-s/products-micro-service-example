package com.ms.example.conversation;

import com.ms.example.product.ProductEntity;
import com.ms.example.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RequiredArgsConstructor
public class ConversationRepositoryIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Test
    public void shouldSaveProducts() {
        // given
        var conversation = new ConversationEntity();
        conversation.setId(10L);
        conversation.setUserId(111L);
        var product = new ProductEntity();
        product.setId(1L);
        product.setConversations(List.of(conversation));

        // when
        productRepository.save(product);
        var mostPopularUsers = conversationRepository.findMostPopularUsersForProduct(1L);

        // then
        assertThat(mostPopularUsers).containsExactly(
                new ConversationUserCount(111, 1)
        );
    }
}