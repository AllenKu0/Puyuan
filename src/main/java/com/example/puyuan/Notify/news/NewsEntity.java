package com.example.puyuan.Notify.news;

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

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//@EntityListeners(AuditingEntityListener.class)
//public class NewsEntity {
//
//    @Id
//    private Long id;
//
//    private int member_id;
//
//    private int group;
//
//    private String title;
//
//    private String message;
//
//    private LocalDateTime pushed_at;
//
//    @CreatedDate
//    private LocalDateTime created_at;
//
//    @LastModifiedDate
//    private LocalDateTime updated_at;
//
//    @CreatedBy
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonBackReference
//    private AppUserEntity user;
//}
