package com.example.puyuan.Notify.share.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "ShareRequest",description = "分享請求")
public class ShareRequest {
    @Schema(description = "種類, 0：血壓；1：體重,2：血糖；3：飲食")
    private Integer type;
    @Schema(description = "紀錄ID")
    private Long id;
    @Schema(description = "種類, 1：親友；2：糖友")
    private Integer relation_type;
}
