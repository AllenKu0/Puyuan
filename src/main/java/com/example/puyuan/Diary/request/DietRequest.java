package com.example.puyuan.Diary.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(name = "日記請求")
@Builder
public class DietRequest {

    @Schema(description = "描述")
    private int description;

    @Schema(description = "時段(早餐、午餐、晚餐)")
    private int meal;

    @Schema(description = "標籤")
    @JsonProperty("tag[]")
    private List<String> tag;

    @Schema(description = "照片數量")
    private int image;

    @Schema(description = "緯度")
    private double lat;

    @Schema(description = "經度")
    private double ing;

    @JsonIgnore
    @Schema(description = "紀錄時間")
    private LocalDateTime recorded_at;
}
