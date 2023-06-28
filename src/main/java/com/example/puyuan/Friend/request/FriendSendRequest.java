package com.example.puyuan.Friend.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(name = "FriendSendRequest",description = "控糖團好友請求")
public class FriendSendRequest {
    @Schema(description = "關係:0-2 醫師團/親友團/糖友團")
    private Integer type;
    @Schema(description = "邀請碼")
    private String invite_code;
}
