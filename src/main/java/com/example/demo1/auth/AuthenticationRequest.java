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
@Schema(name = "身分驗證模塊")
public class AuthenticationRequest {
    @Schema(description = "帳號")
    private String account;
    @Schema(description = "信箱")
    private String email;
    @Schema(description = "電話")
    private String phone;
    @Schema(description = "密碼")
    private String password;
//    private String fb_id;
    @Schema(description = "驗證碼")
    private String code;
}
