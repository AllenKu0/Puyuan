package com.example.demo1.auth;

import com.example.demo1.appuser.AppUser;
import com.example.demo1.appuser.AppUserRepository;
import com.example.demo1.appuser.AppUserRole;
import com.example.demo1.auth.request.RegisterRequest;
import com.example.demo1.auth.request.VerificationRequest;
import com.example.demo1.base.StatusResponse;
import com.example.demo1.security.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
@AllArgsConstructor
public class AuthenticationService {
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

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
//        var jwtToken = jwtService.generateToken(user);

        return response.status(StatusResponse.RC.SUCCESS.getCode()).build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * 發送驗證碼
     * @param request
     * @return
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
     * @param request
     * @return
     */
    public StatusResponse checkCode(AuthenticationRequest request) {
        var response = StatusResponse.builder();
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        if(request.getCode().equals(user.getCode())) {
            // 帳號信箱驗證成功
            response.status(StatusResponse.RC.SUCCESS.getCode());
            user.setEnabled(true);
            userRepository.save(user);
        } else {
            response.status(StatusResponse.RC.FAILED.getCode());
        }
        return response.build();
    }

    /**
     * 重設密碼
     * @param request
     * @return
     */
    public StatusResponse resetPassword(AuthenticationRequest request) {
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        // 更新密碼並寫入資料庫
        user.setPassword(request.getPassword());
        userRepository.save(user);
        return StatusResponse.builder()
                .status(StatusResponse.RC.SUCCESS.getCode())
                .build();
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
