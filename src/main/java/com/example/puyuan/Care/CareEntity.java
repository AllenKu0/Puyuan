package com.example.puyuan.Care;

import com.example.puyuan.AppUser.AppUserEntity;
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
@Table(name = "user_care")
@EntityListeners(AuditingEntityListener.class)
public class CareEntity {
    @Id
    @GeneratedValue
    @Column(length = 50)
    private Long id;

    private Integer member_id;
    private Integer reply_id;

    /** 關懷訊息 */
    @Column(length = 100)
    private String message;

    @CreatedBy
    @ManyToOne(targetEntity = AppUserEntity.class)
    @JsonBackReference
    private AppUserEntity appUser;

    @LastModifiedDate
    private LocalDateTime updated_at;

    @CreatedDate
    private LocalDateTime created_at;
}
