package com.example.puyuan.Friend.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendUserSetResponse {
    private Long id;
    private String name;
    private String account;
}
