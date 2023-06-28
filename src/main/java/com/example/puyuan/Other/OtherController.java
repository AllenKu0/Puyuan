package com.example.puyuan.Other;

import com.example.puyuan.Base.StatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Tag(name = "其他模塊")
public class OtherController {

    private final OtherService service;

    @PutMapping("/user/badge")
    @Operation(description = "更新Badge")
    public ResponseEntity<StatusResponse> updateBadge(
//        @PathVariable("num")
//        @Schema(description = "前端App圖標上的新通知數量")
//        @NotBlank int num
    ) {
        return ResponseEntity.ok(service.updateBadge());
    }
}
