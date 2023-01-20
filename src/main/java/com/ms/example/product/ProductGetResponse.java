package com.ms.example.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class ProductGetResponse {

    @ApiModelProperty(example = "product name")
    private String name;

    @ApiModelProperty(example = "111")
    private long conversationCount;

    @ApiModelProperty(example = "12")
    private Long mostPopularUserId;
}
