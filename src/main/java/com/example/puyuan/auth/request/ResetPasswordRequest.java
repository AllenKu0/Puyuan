package com.example.puyuan.auth.request;

import com.example.puyuan.my_utils.validator.Password;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "ResetPasswordRequest", description = "重設密碼請求")
public class ResetPasswordRequest {
    @Schema(description = "使用者舊密碼")
    @NotBlank
    private String oldPassword;

    @Schema(description = "使用者的新密碼")
    @NotBlank
    @Password
    private String newPassword;
}
