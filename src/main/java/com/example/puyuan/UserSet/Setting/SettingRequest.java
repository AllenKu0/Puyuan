package com.example.puyuan.UserSet.Setting;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "SettingRequest", description = "個人設定更新")
public class SettingRequest {
    @Schema(description = "錄音後")
    private String after_recording;
    @Schema(description = "一天沒錄音")
    private String no_recording_for_a_day;
    @Schema(description = "超過最大值或低於最小值")
    private String over_max_or_under_min;
    @Schema(description = "吃飯後")
    private String after_meal;
    @Schema(description = "糖單位")
    private String unit_of_sugar;
    @Schema(description = "重量單位")
    private String unit_of_weight;
    @Schema(description = "高度單位")
    private String unit_of_height;

}
