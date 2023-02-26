package com.example.puyuan.Care;

import com.example.puyuan.Base.StatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/care")
@RequiredArgsConstructor
@Tag(name = "關懷資訊")
public class CareController {

    private final CareService service;

    @Operation(description = "獲取所有關懷諮詢")
    @GetMapping
    public ResponseEntity<?> fetchCares() {
        return ResponseEntity.ok(service.fetchCares());
    }

    @Operation(description = "上傳關懷諮詢")
    @PostMapping
    public ResponseEntity<StatusResponse> uploadCare(
        @RequestBody @Validated CareRequest request
    ) {
        return ResponseEntity.ok(service.saveCare(request));
    }

}
