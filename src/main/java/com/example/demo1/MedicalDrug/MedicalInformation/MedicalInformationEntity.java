package com.example.demo1.MedicalDrug.MedicalInformation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

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
    private boolean oad;

    /** 胰島素 */
    private boolean insulin;

    /** 高血壓藥 */
    private boolean anti_hypertensives;

    /** 0~4 無/糖尿病前期/第一型/第二型/妊娠 */
    private int diabetes_type;

    @LastModifiedDate
    private Timestamp updated_at;
    @CreatedDate
    private Timestamp created_at;

}
