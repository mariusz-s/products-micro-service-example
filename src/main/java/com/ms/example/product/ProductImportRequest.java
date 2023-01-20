package com.ms.example.product;

import com.ms.example.conversation.ConversationImportRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Valid
@ApiModel
@Builder
public class ProductImportRequest implements Serializable {

    @NotNull
    @ApiModelProperty(required = true, example = "1")
    private Long id;

    @NotBlank
    @ApiModelProperty(required = true, example = "product")
    private String name;

    @NotNull
    @ApiModelProperty(example = "2022-08-16T09:31:12")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    @Valid
    @Builder.Default
    private List<ConversationImportRequest> conversations = new ArrayList<>();
}


