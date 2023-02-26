package com.example.puyuan.AppUser;

import com.example.puyuan.UserSet.Default.DefaultEntity;
import com.example.puyuan.UserSet.Setting.SettingEntity;
import com.example.puyuan.UserSet.UserInformation.UserSetEntity;
import com.example.puyuan.A1c.A1cEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_user")
public class AppUserEntity implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    private String account;
    @Column(unique = true)
    private String phone;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserRole userRole; // 使用者角色(ex: USER或ADMIN等)

    private String code = null; //驗證碼(6位)
    private Boolean verified = false; //帳號使否已被啟用
    private Boolean privacy_policy = false; //是否同意隱私權聲明
    private Boolean must_change_password = false; //是否必須更改密碼

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<A1cEntity> a1cs = new ArrayList<>();

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<UserSetEntity> userSets = new ArrayList<>();

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DefaultEntity> userDefaults = new ArrayList<>();

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SettingEntity> userSettings = new ArrayList<>();



    @LastModifiedDate
    private LocalDateTime updated_at;

    @CreatedDate
    private LocalDateTime created_at;

    public AppUserEntity(String account, String phone, String email, String password, AppUserRole userRole) {
        this.account = account;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    /**
     * 用於提供該用戶授權權限集合
     * @return 用戶被授權的權限集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 用來判斷使用者的帳戶是否過期
     * @return 如果帳戶已過期，返回false，表示使用者不應該被授權，反之則true。
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用來判斷使用者的帳戶是否被鎖定
     * @return 如果帳戶被鎖定，返回false，表示使用者不應該被授權。
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 用來判斷使用者的認證信息是否過期，例如密碼是否過期
     * @return 如果認證信息已過期，返回false，表示使用者不應該被授權。
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用來判斷使用者是否啟用，如果使用者已被禁用
     * @return 返回false，表示使用者不應該被授權。
     */
    @Override
    public boolean isEnabled() {
        return verified;
    }
}
