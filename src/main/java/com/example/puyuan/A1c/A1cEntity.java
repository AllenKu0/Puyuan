package com.example.puyuan.A1c;

import com.example.puyuan.AppUser.AppUserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class A1cEntity {

    @Id
    @GeneratedValue
    @Column(length = 50)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    @JsonBackReference
    private AppUserEntity appUser;

    /** 糖化血紅素 */
    private Double a1c;

    /** 紀錄糖化血紅素時間
     *  - 前端可手動決定(設定)的時間
     */
    private LocalDateTime recorded_at;

    /**
     * 更新此筆資料的時間
     * - 由後端系統時間決定
     */
    @LastModifiedDate
    private LocalDateTime updated_at;

    /** 創建糖化血紅素日期時間 */
    @CreatedDate
    private LocalDateTime created_at;

    public A1cEntity(AppUserEntity appUser, Double a1c, LocalDateTime recorded_at) {
        this.appUser = appUser;
        this.a1c = a1c;
        this.recorded_at = recorded_at;
    }
}
