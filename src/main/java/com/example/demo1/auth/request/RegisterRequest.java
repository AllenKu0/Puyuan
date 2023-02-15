package com.example.demo1.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "RegisterRequest", description = "註冊請求")
public class RegisterRequest {
    @Schema(description = "使用者的帳號")
    @NotBlank
    private String account;
    @Schema(description = "使用者的手機")
    @NotBlank
    private String phone;
    @Schema(description = "使用者的信箱")
    @Email
    private String email;
    @Schema(description = "使用者的密碼")
    @NotBlank
    private String password;
}
