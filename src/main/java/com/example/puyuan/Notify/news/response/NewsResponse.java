package com.example.puyuan.Notify.news.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponse {
    private Long id;
    private Integer member_id;
    private Integer group;
    private String title;
    private String message;
    private LocalDateTime pushed_at;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
