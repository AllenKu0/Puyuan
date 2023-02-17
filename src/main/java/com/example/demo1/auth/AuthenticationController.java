package com.example.demo1.auth;

import com.example.demo1.auth.request.*;
import com.example.demo1.base.StatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "身分驗證模塊",
     description = "包含註冊、登入、登出等身分驗證之API")
@Slf4j
public class AuthenticationController {

    private final AuthenticationService service;

    @Operation(summary = "註冊接口")
    @PostMapping("/register")
    public ResponseEntity<StatusResponse> register(
        @RequestBody @Validated RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @Operation(summary = "發送驗證碼")
    @PostMapping("/verification/send")
    public ResponseEntity<StatusResponse> sendCode(
        @RequestBody @Validated VerificationRequest request
    ) {
        return ResponseEntity.ok(service.sendCode(request));
    }

    @Operation(summary = "檢查驗證碼")
    @PostMapping("/verification/check")
    public ResponseEntity<StatusResponse> checkCode(
        @RequestBody @Validated CheckCodeRequest request
    ) {
        return ResponseEntity.ok(service.checkCode(request));
    }

    @Operation(summary = "登入接口")
    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> login(
        @RequestBody @Validated LoginRequest request
    ) {
        return ResponseEntity.ok(service.login(request));
    }

    @Operation(summary = "重設密碼")
    @PostMapping("/password/reset")
    public ResponseEntity<StatusResponse> resetPassword(
        @RequestBody @Validated ResetPasswordRequest request
    ) {
        return ResponseEntity.ok(service.resetPassword(request));
    }

    @Operation(summary = "忘記密碼")
    @PostMapping("/password/forgot")
    public ResponseEntity<?> forgot(
        @RequestBody @Validated ForgotRequest request
    ) {
        return ResponseEntity.ok(service.forgot(request));
    }
}
