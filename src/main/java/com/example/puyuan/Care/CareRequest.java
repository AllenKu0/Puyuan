package com.example.puyuan.Care;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "關懷訊息之請求體")
public class CareRequest {
    @Schema(description = "關懷訊息")
    @NotBlank
    private String message;
}
