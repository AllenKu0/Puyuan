package com.example.puyuan.MedicalDrug.MedicalInformation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "更新就醫資訊之請求體")
public class MedicalInfoRequest {
    @Schema(description = "0~4 無/糖尿病前期/第一型/第二型/妊娠")
    private int diabetes_type;

    @Schema(description = "糖尿病口服藥")
    private Boolean oad;

    @Schema(description = "胰島素")
    private Boolean insulin;

    @Schema(description = "高血壓藥")
    private Boolean anti_hypertensives;
}
