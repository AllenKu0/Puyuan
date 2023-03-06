package com.example.puyuan.UserSet.Default;

import com.example.puyuan.AppUser.AppUserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "default")
@EntityListeners(AuditingEntityListener.class)
public class DefaultEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    @JsonBackReference
    private AppUserEntity appUser;

//  "最大糖分"
    private Integer sugar_delta_max;
//  "最小糖分"
    private Integer sugar_delta_min;
//  "早上最大糖分"
    private Integer sugar_morning_max;
//  "早上最小糖分"
    private Integer sugar_morning_min;
//  "晚上最大糖分"
    private Integer sugar_evening_max;
//  "晚上最小糖分"
    private Integer sugar_evening_min;
//  "之前最大糖分"
    private Integer sugar_before_max;
//  "之前最小糖分"
    private Integer sugar_before_min;
//  "之後最大糖分"
    private Integer sugar_after_max;
//  "之後最小糖分"
    private Integer sugar_after_min;
//  "收縮最大"
    private Integer systolic_max;
//  "收縮最小"
    private Integer systolic_min;
//  "舒張最大"
    private Integer diastolic_max;
//  "舒張最小"
    private Integer diastolic_min;
//  "脈搏最大"
    private Integer pulse_max;
//  "脈搏最小"
    private Integer pulse_min;
//  "重量最大"
    private Integer weight_max;
//  "重量最小"
    private Integer weight_min;
//  "bmi最大"
    private Integer bmi_max;
//  "bmi最小"
    private Integer bmi_min;
//  "體內脂肪最大"
    private Integer body_fat_max;
//  "體內脂肪最小"
    private Integer body_fat_min;
//  "創建時間"
    @CreatedDate
    private LocalDateTime created_at;
//  "更新時間"
    @LastModifiedDate
    private LocalDateTime updated_at;
}
