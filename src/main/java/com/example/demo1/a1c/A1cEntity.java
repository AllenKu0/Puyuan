package com.example.demo1.a1c;

import com.example.demo1.appuser.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class A1cEntity {

    @Id
    @GeneratedValue
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
    private String recorded_at;

    /**
     * 更新此筆資料的時間
     * - 由後端系統時間決定
     */
    private String updated_at;

    /** 創建糖化血紅素時間 */
    private String created_at;

    public A1cEntity(AppUser appUser, Double a1c, String recorded_at) {
        this.appUser = appUser;
        this.a1c = a1c;
        this.recorded_at = recorded_at;
    }
}
