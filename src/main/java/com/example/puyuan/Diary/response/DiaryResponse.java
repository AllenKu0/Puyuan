package com.example.puyuan.Diary.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryResponse {
    private Long id;
    private int user_id;
    //血糖
    @Builder.Default
    private float systolic = 0;
    @Builder.Default
    private float diastolic = 0;
    @Builder.Default
    private float pulse = 0;
    //體重
    @Builder.Default
    private float weight = 0;
    @Builder.Default
    private float body_fat = 0;
    @Builder.Default
    private float bmi = 0;
    //血糖
    @Builder.Default
    private int sugar = 0;
    @Builder.Default
    private int exercise = 0;
    @Builder.Default
    private int drug = 0;
    @Builder.Default
    private int timeperiod = 0;
    //日記
    @Builder.Default
    private int description = 0;
    @Builder.Default
    private int meal = 0;
    @Builder.Default
    private Tag tag = new Tag();
    @Builder.Default
    private List<String> image = new ArrayList<>();
    @Builder.Default
    private Location location = new Location();
    @Builder.Default
    private String reply = "";
    @Builder.Default
    private LocalDateTime recorded_at = LocalDateTime.now();
    @Builder.Default
    private String type = "";
}
