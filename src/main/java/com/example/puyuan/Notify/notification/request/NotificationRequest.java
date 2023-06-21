package com.example.puyuan.Notify.notification.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(name = "NotificationRequest",description = "通知請求")
public class NotificationRequest {
    @Schema(description = "訊息")
    private String message;
}
