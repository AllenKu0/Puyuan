package com.example.puyuan.Auth;

import com.example.puyuan.AppUser.AppUserEntity;
import com.example.puyuan.AppUser.AppUserRepository;
import com.example.puyuan.AppUser.AppUserRole;
import com.example.puyuan.Auth.request.*;
import com.example.puyuan.Base.StatusResponse;
import com.example.puyuan.Security.JwtService;
import com.example.puyuan.UserSet.Default.DefaultEntity;
import com.example.puyuan.UserSet.Default.DefaultRepository;
import com.example.puyuan.UserSet.Setting.SettingEntity;
import com.example.puyuan.UserSet.Setting.SettingRepository;
import com.example.puyuan.UserSet.UserInformation.UserSetEntity;
import com.example.puyuan.UserSet.UserInformation.UserSetRepository;
import com.example.puyuan.UserSet.UserSetService;
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
    private final DefaultRepository defaultRepository;
    private final SettingRepository settingRepository;
    private final UserSetRepository userSetRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public StatusResponse register(RegisterRequest request){
        var user = AppUserEntity.builder()
//                .account(request.getAccount())
                .password(passwordEncoder.encode(request.getPassword()))
//                .phone(request.getPhone())
                .email(request.getEmail())
                .userRole(AppUserRole.USER)
                .build();

        userRepository.save(user);

        var userSet = UserSetEntity.builder()
                .appUser(user)
                .email(user.getEmail())
                .inviteCode(generateInviteCode())
                .build();

        var userDefault = DefaultEntity.builder()
                .appUser(user)
                .user_id(user.getId().intValue())
                .build();

        var userSetting = SettingEntity.builder()
                .appUser(user)
                .user_id(user.getId().intValue())
                .build();


        userSetRepository.save(userSet);
        defaultRepository.save(userDefault);
        settingRepository.save(userSetting);

        return StatusResponse.SUCCESS();
    }

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
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
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        if(request.getCode().equals(user.getCode())) {
            // 帳號信箱驗證成功
            user.setVerified(true);
            userRepository.save(user);
            return StatusResponse.SUCCESS();
        }
        throw new RuntimeException("檢查驗證碼操作失敗");
    }

    /**
     * 重設密碼
     */
    public StatusResponse resetPassword(ResetPasswordRequest request) {
        var user = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 確認使用者知道舊密碼才能設定新密碼
        if (passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            // 更新密碼並寫入資料庫
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);
            return StatusResponse.SUCCESS();
        }
        throw new RuntimeException("重設密碼操作失敗");
    }

    /**
     * 忘記密碼: 傳送新密碼到使用者信箱
     */
    public Map<String, String> forgot(ForgotRequest request) {
        var response = new LinkedHashMap<String, String>();

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        if(user.getVerified() != null && user.getVerified()) { // 使用者已經通過驗證碼驗證
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

    public String generateInviteCode() {
        var codeSet = "0123456789";
        var random = new Random();
        var result = new StringBuilder();
        for(int i = 0; i < 6; i++) {
            result.append(codeSet.charAt(random.nextInt(codeSet.length())));
        }
        return result.toString();
    }
}