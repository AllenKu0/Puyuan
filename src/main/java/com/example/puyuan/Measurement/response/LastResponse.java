package com.example.puyuan.Measurement.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LastResponse {
    private LocalDateTime blood_pressure;
    private LocalDateTime weight;
    private LocalDateTime blood_sugar;
    private LocalDateTime diet;
}
