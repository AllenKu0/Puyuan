package com.example.demo1.Default;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_default")
public class Default {
    @Id
    @GeneratedValue
    private Long id;
    @Schema(description = "最大糖分")
    private String sugar_delta_max;
    @Schema(description = "最小糖分")
    private String sugar_delta_min;
    @Schema(description = "早上最大糖分")
    private String sugar_morning_max;
    @Schema(description = "早上最小糖分")
    private String sugar_morning_min;
    @Schema(description = "晚上最大糖分")
    private String sugar_evening_max;
    @Schema(description = "晚上最小糖分")
    private String sugar_evening_min;
    @Schema(description = "之前最大糖分")
    private String sugar_before_max;
    @Schema(description = "之前最小糖分")
    private String sugar_before_min;
    @Schema(description = "之後最大糖分")
    private String sugar_after_max;
    @Schema(description = "之後最小糖分")
    private String sugar_after_min;
    @Schema(description = "收縮最大")
    private String systolic_max;
    @Schema(description = "收縮最小")
    private String systolic_min;
    @Schema(description = "舒張最大")
    private String diastolic_max;
    @Schema(description = "舒張最小")
    private String diastolic_min;
    @Schema(description = "脈搏最大")
    private String pulse_max;
    @Schema(description = "脈搏最小")
    private String pulse_min;
    @Schema(description = "重量最大")
    private String weight_max;
    @Schema(description = "重量最小")
    private String weight_min;
    @Schema(description = "bmi最大")
    private String bmi_max;
    @Schema(description = "bmi最小")
    private String bmi_min;
    @Schema(description = "體內脂肪最大")
    private String body_fat_max;
    @Schema(description = "體內脂肪最小")
    private String body_fat_min;
    @Schema(description = "創建時間")
    private String created_at;
    @Schema(description = "更新時間")
    private String updated_at;

}
