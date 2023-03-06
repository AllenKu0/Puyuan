package com.example.puyuan.UserSet.UserInformation;

import com.example.puyuan.AppUser.AppUserEntity;
import com.example.puyuan.MyUtils.Validator.Phone;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_set")
@EntityListeners(AuditingEntityListener.class)
public class UserSetEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    @JsonBackReference
    private AppUserEntity appUser;

    //    @Schema(description = "FCM")
    private String fcm_id;
    //    @Schema(description = "暱稱")
    private String name;
    private String status;
    private String groups;
    //"生日"
    private String birthday;
    //"身高"
    private double height;
    //"性別"
    private Boolean gender;
    //"地址"
    private String address;
    //"體重"
    private String weight;
    //"電話"
    @Phone
    private String phone;
    //"電子郵件"
    @Email
    private String email;
    //"邀請代碼"
    private String invite_code;
    private String unread_recode_one;
    private String unread_recode_two;
    private String unread_recode_three;
    //"徽章"
    private String badge;
    //"創建時間"
    @CreatedDate
    private LocalDateTime created_at;
    //"更新時間"
    @LastModifiedDate
    private LocalDateTime updated_at;


}
