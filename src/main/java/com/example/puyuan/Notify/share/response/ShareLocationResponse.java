package com.example.puyuan.Notify.share.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShareLocationResponse {

    private String lat;
    private String lng;
}
