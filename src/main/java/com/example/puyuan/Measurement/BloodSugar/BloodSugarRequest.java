package com.example.puyuan.Measurement.BloodSugar;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "上傳血糖測量結果之請求")
public class BloodSugarRequest {
    @Schema(description = "使用者的血糖")
    private int sugar;

    @Schema(description = "使用者的時段")
    private int timeperiod;

    @Schema(description = "使用者的紀錄時間")
    private LocalDateTime recorded_at;
}
