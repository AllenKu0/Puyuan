package com.example.puyuan.Measurement.BloodPressure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "血壓測量結果之請求")
@Builder
public class BloodPressureRequest {

    @Schema(description = "使用者的收縮壓")
    private float systolic;

    @Schema(description = "使用者的舒張壓")
    private float diastolic;

    @Schema(description = "使用者的心跳")
    private float pulse;

    @JsonIgnore
    @Schema(description = "使用者紀錄時間")
    private LocalDateTime recorded_at;
}
