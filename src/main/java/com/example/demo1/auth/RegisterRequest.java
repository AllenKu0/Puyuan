package com.example.demo1.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "RegisterRequest", description = "註冊請求輸入")
public class RegisterRequest {
    @Schema(description = "使用者的帳號")
    private String account;
    @Schema(description = "使用者的手機")
    private String phone;
    @Schema(description = "使用者的信箱")
    private String email;
    @Schema(description = "使用者的密碼")
    private String password;
}
