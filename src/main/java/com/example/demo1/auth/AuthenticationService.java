package com.example.demo1.auth;

import com.example.demo1.UserSet.UserSetRepository;
import com.example.demo1.UserSet.UserSetting;
import com.example.demo1.appuser.AppUser;
import com.example.demo1.appuser.AppUserRepository;
import com.example.demo1.appuser.AppUserRole;
import com.example.demo1.auth.request.*;
import com.example.demo1.base.StatusResponse;
import com.example.demo1.security.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@AllArgsConstructor
public class AuthenticationService {
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserSetRepository setRepository;

    public StatusResponse register(RegisterRequest request){
        var response = StatusResponse.builder();
        var user = AppUser.builder()
                .account(request.getAccount())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .email(request.getEmail())
                .userRole(AppUserRole.USER)
                .build();
        userRepository.save(user);

        /* 個人資料儲存 */
        var userSet = UserSetting.builder()
                .name(request.getAccount())
                .phone(request.getPhone())
                .email(request.getEmail())
                .build();

        setRepository.save(userSet);


        return response.status(StatusResponse.RC.SUCCESS.getCode()).build();
    }

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getAccount(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getAccount()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * 發送驗證碼
     */
    public StatusResponse sendCode(VerificationRequest request) {
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        // 產生驗證碼並儲存，然後傳回給使用者
        var gCode = generateCode();
        user.setCode(gCode);
        userRepository.save(user);
        return StatusResponse.builder()
                .status(StatusResponse.RC.SUCCESS.getCode())
                .code(gCode)
                .build();
    }

    /**
     * 檢查驗證碼
     */
    public StatusResponse checkCode(CheckCodeRequest request) {
        var response = StatusResponse.builder();
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        if(request.getCode().equals(user.getCode())) {
            // 帳號信箱驗證成功
            response.status(StatusResponse.RC.SUCCESS.getCode());
            user.setEnabled(true);
            userRepository.save(user);
            return response.build();
        }
        throw new RuntimeException("檢查驗證碼操作失敗");
    }

    /**
     * 重設密碼
     */
    public StatusResponse resetPassword(ResetPasswordRequest request) {
        var user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 確認使用者知道舊密碼才能設定新密碼
        if (passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            // 更新密碼並寫入資料庫
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);
            return StatusResponse.builder()
                    .status(StatusResponse.RC.SUCCESS.getCode())
                    .build();
        }
        throw new RuntimeException("重設密碼操作失敗");
    }

    /**
     * 忘記密碼: 傳送新密碼到使用者信箱
     */
    public TreeMap<String, String> forgot(ForgotRequest request) {
        var response = new TreeMap<String, String>();

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        if(user.getEnabled() != null && user.getEnabled()) { // 使用者已經通過驗證碼驗證
            String newPassword = UUID.randomUUID().toString().substring(0, 7); // 取前8位當作新密碼
            user.setPassword(newPassword);
            userRepository.save(user);

            response.put("status", "0");
            response.put("newPassword", newPassword);
            return response;
        }
        throw new RuntimeException("忘記密碼操作失敗");
    }

    /**
     * 產生驗證碼
     * @return 回傳長度為6的數字驗證碼
     */
    private String generateCode() {
        var codeSet = "0123456789";
        var random = new Random();
        var result = new StringBuilder();
        for(int i = 0; i < 6; i++) {
            result.append(codeSet.charAt(random.nextInt(codeSet.length())));
        }
        return result.toString();
    }
}