package com.example.puyuan.Measurement.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "TimeRequest",description = "上一筆記錄資訊請求")
public class TimeRequest {
    @Schema(description = "時段")
    private Integer diet;//時段[‘晨起0’,‘早餐前1’,‘早餐後2’,‘午餐前3’,‘午餐後4’,‘晚餐前5’,‘晚餐後6’,‘睡前7’]
}
