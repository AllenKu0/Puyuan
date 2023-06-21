package com.example.puyuan.Measurement.Weight;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "體重測量結果")
@Builder
public class WeightRequest {
    @Schema(description = "使用者的體重")
    private float weight;

    @Schema(description = "使用者的體脂")
    private float body_fat;

    @Schema(description = "使用者的BMI")
    private float bmi;

    @JsonIgnore
    @Schema(description = "使用者的紀錄時間")
    private LocalDateTime recorded_at;
}
