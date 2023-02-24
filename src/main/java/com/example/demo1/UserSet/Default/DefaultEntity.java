package com.example.demo1.UserSet.Default;

import com.example.demo1.appuser.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_default")
@EntityListeners(AuditingEntityListener.class)
public class DefaultEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    @JsonBackReference
    private AppUser appUser;

//    @Schema(description = "最大糖分")
    private Integer sugar_delta_max;
//    @Schema(description = "最小糖分")
    private Integer sugar_delta_min;
//    @Schema(description = "早上最大糖分")
    private Integer sugar_morning_max;
//    @Schema(description = "早上最小糖分")
    private Integer sugar_morning_min;
//    @Schema(description = "晚上最大糖分")
    private Integer sugar_evening_max;
//    @Schema(description = "晚上最小糖分")
    private Integer sugar_evening_min;
//    @Schema(description = "之前最大糖分")
    private Integer sugar_before_max;
//    @Schema(description = "之前最小糖分")
    private Integer sugar_before_min;
//    @Schema(description = "之後最大糖分")
    private Integer sugar_after_max;
//    @Schema(description = "之後最小糖分")
    private Integer sugar_after_min;
//    @Schema(description = "收縮最大")
    private Integer systolic_max;
//    @Schema(description = "收縮最小")
    private Integer systolic_min;
//    @Schema(description = "舒張最大")
    private Integer diastolic_max;
//    @Schema(description = "舒張最小")
    private Integer diastolic_min;
//    @Schema(description = "脈搏最大")
    private Integer pulse_max;
//    @Schema(description = "脈搏最小")
    private Integer pulse_min;
//    @Schema(description = "重量最大")
    private Integer weight_max;
//    @Schema(description = "重量最小")
    private Integer weight_min;
//    @Schema(description = "bmi最大")
    private Integer bmi_max;
//    @Schema(description = "bmi最小")
    private Integer bmi_min;
//    @Schema(description = "體內脂肪最大")
    private Integer body_fat_max;
//    @Schema(description = "體內脂肪最小")
    private Integer body_fat_min;
//    @Schema(description = "創建時間")
    private Integer created_at;
//    @Schema(description = "更新時間")
    private Integer updated_at;
}
