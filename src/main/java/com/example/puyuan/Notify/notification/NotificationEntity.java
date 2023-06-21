package com.example.puyuan.Notify.notification;

import com.example.puyuan.AppUser.AppUserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

import javax.swing.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notification")
@EntityListeners(AuditingEntityListener.class)
public class NotificationEntity {
    @Id
    @GeneratedValue
    @Column(length = 50)
    private Long id;
    private Integer member_id;
    private Integer reply_id;
    @Column(length = 100)
    private String message;
    @CreatedDate
    private LocalDateTime created_at;
    @LastModifiedDate
    private LocalDateTime updated_at;
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private AppUserEntity appUser;



}
