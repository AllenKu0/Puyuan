package com.example.puyuan.Diary.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(name = "DietRequest", description = "刪除日記請求")
public class DeleteDiaryRequest {

    @Schema(description = "")
    @JsonProperty(value = "delete_objects")
    private List<DeleteObject> deleteObjects;

    @Data
    public static class DeleteObject {
        @Schema(description = "血糖Ids")
        @JsonProperty(value = "blood_sugars")
        private List<Long> bloodSugars;

        @Schema(description = "血壓Ids")
        @JsonProperty(value = "blood_pressures")
        private List<Long> bloodPressures;

        @Schema(description = "體重Ids")
        @JsonProperty(value = "weights")
        private List<Long> weights;

        @Schema(description = "日常飲食Ids")
        @JsonProperty(value = "diets")
        private List<Long> diets;
    }


}
