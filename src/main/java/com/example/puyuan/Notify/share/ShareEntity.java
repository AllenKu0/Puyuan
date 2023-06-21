package com.example.puyuan.Notify.share;

import com.example.puyuan.AppUser.AppUserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "share")
@EntityListeners(AuditingEntityListener.class)
public class ShareEntity {
    @Id
    @GeneratedValue
    @Column(length = 50)
    private Long id;
    @Column(length = 50)
    private Long fid;
    @Column(length = 100)
    private Integer dataType;
    @Column(length = 100)
    private Integer relation_type;
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private AppUserEntity appUser;


}
