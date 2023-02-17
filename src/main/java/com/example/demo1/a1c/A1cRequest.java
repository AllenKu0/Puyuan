package com.example.demo1.a1c;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "A1CRequest", description = "糖化血色素請求")
public class A1cRequest {
    @Schema(description = "糖化血色素")
    private Double a1c;

    @Schema(description = "紀錄時間")
    @NotBlank
    private String recorded_at;
}
