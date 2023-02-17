package com.example.demo1.appuser;

import com.example.demo1.a1c.A1cEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_user")
public class AppUser implements UserDetails {

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
    private Boolean enabled = false; //帳號使否已被啟用

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<A1cEntity> a1cs = new ArrayList<>();

    public AppUser(String account, String phone, String email, String password, AppUserRole userRole) {
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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
