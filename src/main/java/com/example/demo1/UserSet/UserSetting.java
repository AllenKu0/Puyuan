package com.example.demo1.UserSet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_user_set")
public class UserSetting {
    @Id
    @GeneratedValue
    private Long id;

    //    @Schema(description = "FCM")
    private String fcm_id;
    //    @Schema(description = "暱稱")
    private String name;

    private String status;
    private String groups;

    //    @Schema(description = "生日")
    private String birthday;
    //    @Schema(description = "身高")
    private double height;
    //    @Schema(description = "性別")
    private Boolean gender;
    //    @Schema(description = "地址")
    private String address;
    //    @Schema(description = "體重")
    private String weight;
    //    @Schema(description = "電話")
    private String phone;
    //    @Schema(description = "電子郵件")
    private String email;

    //    @Schema(description = "邀請代碼")
    private String invite_code;
    private String unread_recode_one;
    private String unread_recode_two;
    private String unread_recode_three;
    //    @Schema(description = "徽章")
    private String badge;
    //    @Schema(description = "創建時間")
    private String created_at;
    //    @Schema(description = "更新時間")
    private String updated_at;


}
