package com.example.demo1.Measurement.Weight;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "體重測量結果")
public class WeightRequest {
    @Schema(description = "使用者的體重")
    private float weight;

    @Schema(description = "使用者的體脂")
    private float body_fat;

    @Schema(description = "使用者的BMI")
    private float bmi;

    @Schema(description = "使用者的紀錄時間")
    @NotBlank
    private String recorded_at;
}
