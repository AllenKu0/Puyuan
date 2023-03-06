package com.example.puyuan.UserSet.Default;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "DefaultRequest", description = "個人預設值更新")
public class DefaultRequest {
    @Schema(description = "最大糖分")
    private Integer sugar_delta_max;
    @Schema(description = "最小糖分")
    private Integer sugar_delta_min;
    @Schema(description = "早上最大糖分")
    private Integer sugar_morning_max;
    @Schema(description = "早上最小糖分")
    private Integer sugar_morning_min;
    @Schema(description = "晚上最大糖分")
    private Integer sugar_evening_max;
    @Schema(description = "晚上最小糖分")
    private Integer sugar_evening_min;
    @Schema(description = "之前最大糖分")
    private Integer sugar_before_max;
    @Schema(description = "之前最小糖分")
    private Integer sugar_before_min;
    @Schema(description = "之後最大糖分")
    private Integer sugar_after_max;
    @Schema(description = "之後最小糖分")
    private Integer sugar_after_min;
    @Schema(description = "收縮最大")
    private Integer systolic_max;
    @Schema(description = "收縮最小")
    private Integer systolic_min;
    @Schema(description = "舒張最大")
    private Integer diastolic_max;
    @Schema(description = "舒張最小")
    private Integer diastolic_min;
    @Schema(description = "脈搏最大")
    private Integer pulse_max;
    @Schema(description = "脈搏最小")
    private Integer pulse_min;
    @Schema(description = "重量最大")
    private Integer weight_max;
    @Schema(description = "重量最小")
    private Integer weight_min;
    @Schema(description = "bmi最大")
    private Integer bmi_max;
    @Schema(description = "bmi最小")
    private Integer bmi_min;
    @Schema(description = "體內脂肪最大")
    private Integer body_fat_max;
    @Schema(description = "體內脂肪最小")
    private Integer body_fat_min;
}
