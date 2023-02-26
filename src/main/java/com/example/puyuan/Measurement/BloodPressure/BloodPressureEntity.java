package com.example.puyuan.Measurement.BloodPressure;

import com.example.puyuan.appuser.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "blood_pressure")
public class BloodPressureEntity {
    @Id
    @GeneratedValue
    @Column(length = 50)
    private Long id;

    /** 使用者的收縮壓 */
    @Column(length = 3)
    private Float systolic;

    /** 使用者的舒張壓 */
    @Column(length = 3)
    private Float diastolic;

    /** 使用者的心跳 */
    @Column(length = 3)
    private Float pulse;

    /** 紀錄時間 */
    private LocalDateTime recorded_at;

    @LastModifiedDate
    private LocalDateTime updated_at;

    @CreatedDate
    private LocalDateTime created_at;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser appUser;
}
