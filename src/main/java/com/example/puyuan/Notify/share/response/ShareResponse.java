package com.example.puyuan.Notify.share.response;

import com.example.puyuan.Diary.response.Location;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShareResponse {
    private Long id;
    private Integer user_id;
    private double sugar;
    private Integer timeperiod;
    private double weight;
    private double body_fat;
    private double bmi;
    private Integer systolic;
    private Integer diastolic;
    private Integer pulse;
    private Integer meal;
    private List<String> tag;
    private List<String> image;
    private ShareLocationResponse location;
    private Integer relation_type;
    private Integer relation_id;
    private String message;
    private Integer type;
    private String url;
    private LocalDateTime recorded_at;
    private LocalDateTime created_at;
    private ShareUserResponse user;
}
