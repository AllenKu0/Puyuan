package com.example.demo1.Measurement.BloodSugar;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "上傳血糖測量結果之請求")
public class BloodSugarRequest {
    @Schema(description = "使用者的血糖")
    private int sugar;

    @Schema(description = "使用者的時段")
    private int timeperiod;

    @Schema(description = "使用者的紀錄時間")
    @NotBlank
    private String recorded_at;
}
