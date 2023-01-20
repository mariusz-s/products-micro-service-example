package com.ms.example.context;

import com.ms.example.product.ProductEntity;
import com.ms.example.conversation.ConversationEntity;
import com.ms.example.thread.ThreadEntity;
import com.ms.example.product.ProductImportRequest;
import com.ms.example.conversation.ConversationImportRequest;
import com.ms.example.thread.ThreadImportRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EntityRequestMapper {

    ProductEntity productImportRequestToProductEntity(ProductImportRequest request);

    @Mapping(target = "product", ignore = true)
    ConversationEntity conversationImportRequestToConversation(ConversationImportRequest request);

    @Mapping(target = "conversation", ignore = true)
    ThreadEntity threadImportRequestToThread(ThreadImportRequest request);
}
