package com.ms.example.thread;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Valid
@ApiModel
@Builder
public class ThreadImportRequest implements Serializable {

    @NotNull
    @ApiModelProperty(required = true, example = "1")
    private Long id;

    @NotBlank
    @ApiModelProperty(required = true, example = "thread content")
    private String payload;
}
