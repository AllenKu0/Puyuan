package com.example.puyuan.Measurement;

import com.example.puyuan.Measurement.BloodPressure.BloodPressureRequest;
import com.example.puyuan.Measurement.BloodSugar.BloodSugarRequest;
import com.example.puyuan.Measurement.Weight.WeightRequest;
import com.example.puyuan.base.StatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Slf4j
public class MeasurementController {

    private MeasurementService service;

    @Operation(summary = "上傳血壓測量結果")
    @PostMapping("/blood/pressure")
    public ResponseEntity<StatusResponse> uploadBloodPressure(
        @RequestBody @Validated BloodPressureRequest request
    ) {
        return ResponseEntity.ok(service.uploadBloodPressure(request));
    }

    @Operation(summary = "上傳血糖測量結果")
    @PostMapping("/blood/sugar")
    public ResponseEntity<StatusResponse> uploadBloodSugar(
        @RequestBody @Validated BloodSugarRequest request
    ) {
        return ResponseEntity.ok(service.uploadBloodSugar(request));
    }

    @Operation(summary = "上傳體重測量結果")
    @PostMapping("/weight")
    public ResponseEntity<StatusResponse> uploadWeight(
        @RequestBody @Validated WeightRequest request
    ) {
        return ResponseEntity.ok(service.uploadWeight(request));
    }
}
