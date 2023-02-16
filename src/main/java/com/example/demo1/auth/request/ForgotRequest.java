package com.example.demo1.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "ForgotRequest", description = "忘記密碼請求")
public class ForgotRequest {

    @Schema(description = "使用者的信箱")
    @NotBlank
    @Email
    public String email;
}
