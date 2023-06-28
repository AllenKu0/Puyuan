package com.example.puyuan.Friend.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendListResponse {
    private Long id;
    private String name;
    private Integer relation_type;
}
