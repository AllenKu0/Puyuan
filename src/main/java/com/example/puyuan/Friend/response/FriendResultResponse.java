package com.example.puyuan.Friend.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendResultResponse {
    private Long id;
    //自己id
    private Long userId;
    //好友id
    private Long relationId;
    //朋友類型
    private Integer type;
    //1:接受、2:拒絕
    private Integer status;
    private Boolean read;
    @CreatedDate
    private LocalDateTime created_at;
    @LastModifiedDate
    private LocalDateTime updated_at;
    private FriendInfoResponse relation;
}
