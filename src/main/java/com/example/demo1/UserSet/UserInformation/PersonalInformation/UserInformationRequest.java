package com.example.demo1.UserSet.UserInformation.PersonalInformation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "個人資訊模塊")
public class UserInformationRequest {
    @Schema(description = "FCM")
    private String fcm_id;
    @Schema(description = "暱稱")
    private String name;
    @Schema(description = "生日")
    private String birthday;
    @Schema(description = "身高")
    private double height;
    @Schema(description = "性別")
    private Boolean gender;
    @Schema(description = "地址")
    private String address;
    @Schema(description = "體重")
    private String weight;
    @Schema(description = "電話")
    private String phone;
    @Schema(description = "電子郵件")
    private String email;
}
