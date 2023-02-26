package com.example.puyuan.MedicalDrug.DrugInformation;

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
    private LocalDateTime recorded_at;

    @LastModifiedDate
    private LocalDateTime updated_at;

    @CreatedDate
    private LocalDateTime created_at;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private AppUser user;
}
