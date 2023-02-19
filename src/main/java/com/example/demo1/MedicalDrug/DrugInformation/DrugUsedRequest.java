package com.example.demo1.MedicalDrug.DrugInformation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "上傳藥物資訊之請求體")
public class DrugUsedRequest {
    @Schema(description = "使用藥物類型 0:糖尿病藥物, 1:高血壓藥物")
    private Boolean type;

    @Schema(description = "藥物名稱")
    @NotBlank
    private String name;

    @Schema(description = "使用者紀錄時間")
    @NotBlank
    private String recorded_at;
}
