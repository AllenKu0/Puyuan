package com.example.puyuan.Friend;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_friend")
@EntityListeners(AuditingEntityListener.class)
public class FriendEntity {
    @Id
    @GeneratedValue
    @Column(length = 50)
    private Long id;
    //自己id
    private Long userId;
    //好友id
    private Long relationId;
    //朋友類型
    private Integer friend_type;
    //1:接受、2:拒絕
    private Integer status;
    private Boolean read;
    @CreatedDate
    private LocalDateTime created_at;
    @LastModifiedDate
    private LocalDateTime updated_at;
}
