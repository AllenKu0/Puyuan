package com.example.puyuan.Diary.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    /**緯度**/
    private double lat;
    /**經度**/
    private double ing;
}
