package com.example.demo1.Measurement.BloodSugar;

import com.example.demo1.appuser.AppUser;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

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
    private Timestamp recorded_at;

    @LastModifiedDate
    private Timestamp updated_at;

    /**
     * 創建日期時間
     */
    @CreatedDate
    private Timestamp created_at;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser appUser;
}
