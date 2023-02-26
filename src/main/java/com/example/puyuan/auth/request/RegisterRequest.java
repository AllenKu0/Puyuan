package com.example.puyuan.auth.request;

import com.example.puyuan.my_utils.validator.Password;
import com.example.puyuan.my_utils.validator.Phone;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Schema(name = "RegisterRequest", description = "註冊請求")
public class RegisterRequest {
    @Schema(description = "使用者的帳號")
    @NotBlank
    private String account;
    @Schema(description = "使用者的手機")
    @NotBlank
    @Phone
    private String phone;
    @Schema(description = "使用者的信箱")
    @NotBlank
    @Email
    private String email;
    @Schema(description = "使用者的密碼")
    @NotBlank
    @Password
    private String password;
}
