package com.ms.example.conversation;

import com.ms.example.thread.ThreadImportRequest;
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
public class ConversationImportRequest implements Serializable {

    @NotNull
    @ApiModelProperty(required = true, example = "11")
    private Long id;

    @NotBlank
    @ApiModelProperty(required = true, example = "user@example.com")
    private String fromUser;

    @NotNull
    @ApiModelProperty(required = true, example = "12")
    private Long userId;

    @NotNull
    @ApiModelProperty(example = "2022-08-16T09:31:12")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime received;

    @Valid
    @Builder.Default
    private List<ThreadImportRequest> threads = new ArrayList<>();
}
