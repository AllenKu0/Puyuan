package com.example.puyuan.Diary;

import com.example.puyuan.AppUser.AppUserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class DiaryDietEntity {
    @Id
    @GeneratedValue
    @Column(length = 50)
    private Long id;

    /**描述**/
    @Column(length = 100)
    private int description;
    /**時段(早餐、午餐、晚餐)**/
    private int meal;
    /**標籤**/
    @ElementCollection
    @Column(length = 100)
    private List<String> tag;
    /**照片數量**/
    private Integer image;
    /**緯度**/
    private String lat;
    /**經度**/
    private String lng;
    /**紀錄時間**/
    private LocalDateTime recorded_at;

    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime updated_at;
    @CreatedDate
    @JsonIgnore
    private LocalDateTime created_at;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private AppUserEntity appUser;
}
