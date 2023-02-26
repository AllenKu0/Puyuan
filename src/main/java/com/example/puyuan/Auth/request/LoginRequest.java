package com.example.puyuan.Auth.request;

import com.example.puyuan.MyUtils.Validator.Password;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "LoginRequest", description = "登入請求")
public class LoginRequest {
    @Schema(description = "使用者的信箱")
    @NotBlank
    @Email
    private String account;

    @Schema(description = "使用者的密碼")
    @NotBlank
    @Password
    private String password;
}
