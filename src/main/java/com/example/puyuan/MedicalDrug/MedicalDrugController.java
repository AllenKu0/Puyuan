package com.example.puyuan.MedicalDrug;

import com.example.puyuan.MedicalDrug.DrugInformation.DrugUsedRequest;
import com.example.puyuan.MedicalDrug.MedicalInformation.MedicalInfoRequest;
import com.example.puyuan.base.StatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class MedicalDrugController {

    private final MedicalDrugService service;

    @Operation(description = "上傳藥物資訊")
    @PostMapping("/drug-used")
    public ResponseEntity<StatusResponse> uploadDrugUsed(
        @RequestBody @Validated DrugUsedRequest request
    ) {
        return ResponseEntity.ok(service.uploadDrugUsed(request));
    }

    @Operation(description = "獲取所有藥物資訊")
    @GetMapping("/drug-used")
    public ResponseEntity<?> fetchDrugUseds(
        @RequestParam(value = "type") boolean type
    ) {
        return ResponseEntity.ok(service.fetchAllDrugUsed(type));
    }

    @Operation(description = "刪除多筆藥物資訊")
    @DeleteMapping(path = "/drug-used")
    public ResponseEntity<StatusResponse> deleteDrugUseds(
        @RequestParam List<Long> ids
    ) {
        return ResponseEntity.ok(service.deleteDrugUseds(ids));
    }

    @Operation(description = "設定就醫資訊")
    @PatchMapping("/medical")
    public ResponseEntity<StatusResponse> updateMedicalInfo(
        @RequestBody @Validated MedicalInfoRequest request
    ) {
        return ResponseEntity.ok(service.updateMedicalInfo(request));
    }

    @Operation(description = "獲取就醫資訊")
    @GetMapping("/medical")
    public ResponseEntity<?> fetchMedicalInfo() {
        return ResponseEntity.ok(service.fetchMedicalInfo());
    }
}
