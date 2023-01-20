package com.ms.example.product;

import com.ms.example.conversation.ConversationImportRequest;
import com.ms.example.thread.ThreadImportRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;

    @Test
    public void shouldImportProducts() {
        // given
        var productId = 20L;
        var products = productsImportRequest(productId);

        // when
        var ids = productService.importProducts(products);

        // then
        assertThat(ids).containsExactly(new ProductImportResponse(productId, "Product " + productId));
    }

    @Test
    public void shouldGetProductDetails() {
        // given
        var productId = 21L;
        var products = productsImportRequest(productId);

        // when
        productService.importProducts(products);
        var details = productService.getProductDetails(productId);

        // then
        assertThat(details).isEqualTo(ProductGetResponse.builder()
                .name("Product " + productId)
                .conversationCount(1L)
                .build()
        );
    }

    private List<ProductImportRequest> productsImportRequest(long id) {
        return List.of(
                ProductImportRequest.builder()
                        .id(id)
                        .name("Product " + id)
                        .createdAt(LocalDateTime.parse("2019-03-01T19:20:26"))
                        .conversations(List.of(
                                ConversationImportRequest.builder()
                                        .id(id + 1)
                                        .threads(List.of(
                                                ThreadImportRequest.builder()
                                                        .id(id + 2)
                                                        .payload("a")
                                                        .build()
                                        )).build()
                        )).build()
        );
    }
}