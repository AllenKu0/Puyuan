package com.example.puyuan.Measurement.BloodSugar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "上傳血糖測量結果之請求")
public class BloodSugarRequest {
    @Schema(description = "使用者的血糖")
    private int sugar;

    @Schema(description = "使用者的鍛鍊")
    private Integer exercise;

    @Schema(description = "使用者的藥品")
    private Integer drug;
    @Schema(description = "使用者的時段")
    private int timeperiod;

    @Schema(description = "使用者的紀錄時間")
    private LocalDateTime recorded_at;
}
