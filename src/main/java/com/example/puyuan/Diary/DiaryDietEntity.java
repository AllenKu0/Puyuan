package com.example.puyuan.Diary;

import com.example.puyuan.AppUser.AppUserEntity;
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
    private Long id;

    /**描述**/
    private int description;
    /**時段(早餐、午餐、晚餐)**/
    private int meal;
    /**標籤**/
    @ElementCollection
    private List<String> tag;
    /**照片數量**/
    private Integer image;
    /**緯度**/
    private double lat;
    /**經度**/
    private double ing;
    /**紀錄時間**/
    private LocalDateTime recorded_at;

    @LastModifiedDate
    private LocalDateTime updated_at;
    @CreatedDate
    private LocalDateTime created_at;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUserEntity appUser;
}
