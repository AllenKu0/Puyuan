package com.example.demo1.auth.request;

import com.example.demo1.my_utils.validator.Password;
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