package com.example.puyuan.MedicalDrug.MedicalInformation;

import com.example.puyuan.appuser.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medical_information")
@EntityListeners(AuditingEntityListener.class)
public class MedicalInformationEntity {

    @Id
    @GeneratedValue
    @Column(length = 50)
    private Long id;

    /** 糖尿病口服藥 */
    private Boolean oad;

    /** 胰島素 */
    private Boolean insulin;

    /** 高血壓藥 */
    private Boolean anti_hypertensives;

    /** 0~4 無/糖尿病前期/第一型/第二型/妊娠 */
    private int diabetes_type;

    @LastModifiedDate
    private LocalDateTime updated_at;
    @CreatedDate
    private LocalDateTime created_at;

    @CreatedBy
    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private AppUser appUser;

}
