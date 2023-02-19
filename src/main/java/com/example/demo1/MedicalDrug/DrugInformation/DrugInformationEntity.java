package com.example.demo1.MedicalDrug.DrugInformation;

import com.example.demo1.appuser.AppUser;
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

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "drug_information")
@EntityListeners(AuditingEntityListener.class)
public class DrugInformationEntity {

    @Id
    @GeneratedValue
    @Column(length = 50)
    private Long id;

    /** 藥物名稱 */
    @Column(length = 100)
    private String drugname;

    /** 藥物類型 0:糖尿病藥物, 1:高血壓藥物 */
    @Column(name = "drug_type")
    private Boolean type;

    /** 使用者紀錄時間 */
    private Timestamp recorded_at;

    @LastModifiedDate
    private Timestamp updated_at;

    @CreatedDate
    private Timestamp created_at;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private AppUser user;
}
