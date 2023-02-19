package com.example.demo1.Measurement.Weight;

import com.example.demo1.appuser.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Table(name = "weight")
public class WeightEntity {
    @Id
    @GeneratedValue
    @Column(length = 50)
    private Long id;

    /** 使用者的體重 */
    @Column(length = 3)
    private Float weight;

    /** 使用者的體脂 */
    @Column(length = 3)
    private Float body_fat;

    /** 使用者的BMI */
    @Column(length = 3)
    private Float bmi;

    private Timestamp recorded_at;

    @LastModifiedDate
    private Timestamp updated_at;

    @CreatedDate
    private Timestamp created_at;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser appUser;
}
