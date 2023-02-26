package com.example.puyuan.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "VerificationRequest", description = "發送驗證碼請求")
public class VerificationRequest {
    @Schema(description = "使用者的信箱")
    @NotBlank
    @Email
    private String email;
}
