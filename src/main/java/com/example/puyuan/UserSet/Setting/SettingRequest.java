package com.example.puyuan.UserSet.Setting;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "SettingRequest", description = "個人設定更新")
public class SettingRequest {
    @Schema(description = "錄音後")
    private Boolean after_recording;
    @Schema(description = "一天沒錄音")
    private Boolean no_recording_for_a_day;
    @Schema(description = "超過最大值或低於最小值")
    private Boolean over_max_or_under_min;
    @Schema(description = "吃飯後")
    private Boolean after_meal;
    @Schema(description = "糖單位")
    private Boolean unit_of_sugar;
    @Schema(description = "重量單位")
    private Boolean unit_of_weight;
    @Schema(description = "高度單位")
    private Boolean unit_of_height;

}
