package com.example.demo1.a1c;

import com.example.demo1.appuser.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

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
    private AppUser appUser;

    /** 糖化血紅素 */
    private Double a1c;

    /** 紀錄糖化血紅素時間
     *  - 前端可手動決定(設定)的時間
     */
    private Timestamp recorded_at;

    /**
     * 更新此筆資料的時間
     * - 由後端系統時間決定
     */
    @LastModifiedDate
    private Timestamp updated_at;

    /** 創建糖化血紅素日期時間 */
    @CreatedDate
    private Timestamp created_at;

    public A1cEntity(AppUser appUser, Double a1c, Timestamp recorded_at) {
        this.appUser = appUser;
        this.a1c = a1c;
        this.recorded_at = recorded_at;
    }
}
