package com.ms.example.product;

import com.ms.example.context.EntityRequestMapper;
import com.ms.example.context.ResourceNotFound;
import com.ms.example.conversation.ConversationRepository;
import com.ms.example.conversation.ConversationUserCount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ConversationRepository conversationRepository;
    private final EntityRequestMapper mapper = Mappers.getMapper(EntityRequestMapper.class);

    @Override
    @CacheEvict(cacheNames = "product", allEntries = true)
    public Set<ProductImportResponse> importProducts(Collection<ProductImportRequest> products) {
        List<ProductEntity> collect = products.stream()
                .map(mapper::productImportRequestToProductEntity)
                .collect(Collectors.toList());
        return productRepository.saveAll(
                        collect
                )
                .stream()
                .map(c -> new ProductImportResponse(c.getId(), c.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    @Cacheable(cacheNames = "product", key = "#productId")
    public ProductGetResponse getProductDetails(long productId) {
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFound(productId));
        return ProductGetResponse.builder()
                .name(product.getName())
                .conversationCount(conversationRepository.countByProductId(productId))
                .mostPopularUserId(findMostPopularUserForProduct(productId))
                .build();
    }

    private Long findMostPopularUserForProduct(long productId) {
        List<ConversationUserCount> mostPopularUsers = conversationRepository.findMostPopularUsersForProduct(productId);
        if (CollectionUtils.isEmpty(mostPopularUsers)) {
            return null;
        }
        if (mostPopularUsers.size() == 1) {
            return mostPopularUsers.get(0).getUserId();
        }
        return mostPopularUsers.get(0).getCount() > mostPopularUsers.get(1).getCount()
                ? mostPopularUsers.get(0).getUserId() : null;
    }
}
