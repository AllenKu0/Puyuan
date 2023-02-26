package com.example.puyuan.Measurement.BloodSugar;

import com.example.puyuan.appuser.AppUser;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "blood_sugar")
public class BloodSugarEntity {
    @Id
    @GeneratedValue
    @Column(length = 50)
    private Long id;

    /** 使用者的血糖 */
    private Integer sugar;

    /** 使用者的時段  */
    private Integer timeperiod;


    /** 使用者的紀錄時間 */
    private LocalDateTime recorded_at;

    @LastModifiedDate
    private LocalDateTime updated_at;

    /**
     * 創建日期時間
     */
    @CreatedDate
    private LocalDateTime created_at;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser appUser;
}
