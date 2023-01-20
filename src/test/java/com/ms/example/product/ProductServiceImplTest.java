package com.ms.example.product;

import com.ms.example.conversation.ConversationUserCount;
import com.ms.example.conversation.ConversationRepository;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final ConversationRepository conversationRepository = mock(ConversationRepository.class);
    private final ProductServiceImpl productService = spy(
            new ProductServiceImpl(
                    productRepository,
                    conversationRepository
            )
    );

    @Test
    public void shouldImportProducts() {
        // given
        var product = List.of(
                ProductImportRequest.builder()
                        .id(10L)
                        .build()
        );

        // when
        productService.importProducts(product);

        // then
        verify(productRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void shouldGetCompanyDetails() {
        // given
        var productId = 1L;
        var product = new ProductEntity();
        product.setId(productId);
        product.setName("Test");
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(conversationRepository.countByProductId(productId)).thenReturn(2L);
        when(conversationRepository.findMostPopularUsersForProduct(productId)).thenReturn(List.of(
                new ConversationUserCount(111L, 1)
        ));

        // when
        var details = productService.getProductDetails(productId);

        // then
        assertThat(details).isEqualTo(ProductGetResponse.builder().name("Test").conversationCount(2L).mostPopularUserId(111L).build());
    }
}