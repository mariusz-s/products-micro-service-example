package com.ms.example.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel
@AllArgsConstructor
public class ProductImportResponse {

    @ApiModelProperty(example = "1")
    private long id;

    @ApiModelProperty(example = "product name")
    private String name;
}
