package com.example.puyuan.A1c;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "A1CRequest", description = "糖化血色素請求")
public class A1cRequest {
    @Schema(description = "糖化血色素")
    private Double a1c;

    @Schema(description = "使用者紀錄時間")
    private LocalDateTime recorded_at;
}
