package com.example.puyuan.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "CheckCodeRequest", description = "檢查驗證碼請求")
public class CheckCodeRequest {
    @Schema(description = "使用者的信箱")
    @NotBlank
    @Email
    private String email;

    @Schema(description = "驗證碼")
    @NotBlank(message = "驗證碼不得為空")
    @Pattern(regexp = "^\\d{6}$", message = "驗證碼格式不符合")
    private String code;
}
