package com.example.puyuan.Friend.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "FriendDeleteRequest",description = "刪除邀請請求")
public class FriendDeleteRequest {
    @Schema(description = "刪除的id")
    @JsonProperty("ids[]")
    private Integer[] ids;
}
