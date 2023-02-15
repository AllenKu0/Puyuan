package com.example.demo1.auth;

import com.example.demo1.auth.request.RegisterRequest;
import com.example.demo1.auth.request.VerificationRequest;
import com.example.demo1.base.StatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "AuthenticationController",
     description = "身分驗證接口")
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
        @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.checkCode(request));
    }

    @Operation(summary = "登入接口")
    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> login(
        @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.login(request));
    }

    @Operation(summary = "重設密碼")
    @PostMapping("/password/reset")
    public ResponseEntity<StatusResponse> resetPassword(
        @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.resetPassword(request));
    }

    @Operation(summary = "忘記密碼")
    @PostMapping("/password/forgot")
    public ResponseEntity<StatusResponse> forgot() {
        // TODO: 2023/2/8 忘記密碼
        return null;
    }


}
