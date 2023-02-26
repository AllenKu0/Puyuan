package com.example.puyuan.A1c;

import com.example.puyuan.Base.StatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/a1c")
@Tag(
    name = "A1c糖化血色素"
)
@Slf4j
public class A1cController {

    private final A1cService service;

    @PostMapping
    @Operation(summary = "上傳糖化血色素")
    public ResponseEntity<StatusResponse> upload(
        @RequestBody A1cRequest request
    ) {
        return ResponseEntity.ok(service.upload(request));
    }

    @GetMapping
    @Operation(summary = "獲取糖化血色素")
    public ResponseEntity<?> fetch() {
        return ResponseEntity.ok(service.fetch());
    }

    @DeleteMapping
    @Operation(summary = "刪除多筆糖化血色素資料")
    public ResponseEntity<StatusResponse> deleteA1cs(
        @RequestParam List<Long> ids
    ) {
        return ResponseEntity.ok(service.deleteA1cs(ids));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "刪除單筆糖化血色素資料")
    public ResponseEntity<StatusResponse> deleteA1c(
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.delete(id));
    }
}
