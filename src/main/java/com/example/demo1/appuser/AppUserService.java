package com.example.demo1.appuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
            .orElseThrow(
                () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email))
            );
    }

    /**
     *  註冊用戶
     */
    public String signUpUser(AppUser appUser) {
        // 1. 先檢查帳戶是否存在
        boolean isUserExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        // 2. 如果存在則拋出異常
        if (isUserExists) {
            throw new IllegalStateException("此信箱已被註冊!!");
//            return "此信箱已被註冊";
        }
        String encodedPassword = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        // 將新帳戶寫入資料庫內
        appUserRepository.save(appUser);

        // TODO: 2023/1/20 發送驗證碼到用戶信箱

        return "註冊成功";
    }

//    public int enableAppUser(String email) {
//        return appUserRepository.enableAppUser(email);
//    }
}
