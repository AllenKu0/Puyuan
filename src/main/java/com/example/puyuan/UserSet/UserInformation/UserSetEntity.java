package com.example.puyuan.UserSet.UserInformation;

import com.example.puyuan.AppUser.AppUserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
    @Column(length = 50)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_user_id")
    @JsonBackReference
    private AppUserEntity appUser;


    //"暱稱"
    @Column(length = 100)
    @Builder.Default
    private String name = "null";
    @Builder.Default
    private String account = "null";

    //"電子郵件"
    @Column(unique = true, updatable = false)
    private String email = "null";
    //"電話"
//    @Column(unique = true, updatable = false)
    @Builder.Default
    private String phone = "null";
    @Builder.Default
    private String fb_id = "null";
    @Column(length = 50)
    @Builder.Default
    private String status = "null";
    @Builder.Default
    @JsonProperty("group")
    private String groups = "null";
    //"生日"
    @Builder.Default
    private String birthday = "null";
    //"身高"
    private double height;
    //"體重"
    private double weight;
    //"性別"
    @Builder.Default
    private Integer gender = 0;
    //"地址"
    @Column(length = 100)
    @Builder.Default
    private String address = "null";
    @Builder.Default
    private Integer unread_recode_one = 0;
    @Builder.Default
    private Integer unread_recode_two = 0;
    @Builder.Default
    private Integer unread_recode_three = 0;
    @Builder.Default
    private Integer verified = 0;
    @Builder.Default
    private Integer privacy_policy = 0;
    @Builder.Default
    private Integer must_change_password = 0;
    //"FCM"
    @Builder.Default
    private String fcm_id = "null";
    //"邀請代碼"
    @Column(name = "inviteCode", length = 10, updatable = false, unique = true)
    private String inviteCode;

    //"徽章"
    @Builder.Default
    private int badge  = 0;
    @Builder.Default
    private Integer login_times = 1;
    //"創建時間"
    @CreatedDate
    private LocalDateTime created_at;
    //"更新時間"
    @LastModifiedDate
    private LocalDateTime updated_at;
}
