package com.example.puyuan.Friend.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendInfoResponse {
    private Long id;
    private String name;
    private String account;
    private String email;
    private String phone;
    private String status;
    private String group;
    private String birthday;
    private double height;
    private Integer gender;
    private Integer badge;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
