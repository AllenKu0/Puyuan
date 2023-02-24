package com.example.demo1.UserSet.PersonalGetData;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "PersonalSettingsController",
        description = "個人資料查詢接口")
public class PersonalDataController {
    private final PersonalDataService service;

    @Operation(summary = "查看個人資訊")
    @GetMapping("/user")
    public ResponseEntity<?> getUserSet(){
        return ResponseEntity.ok(service.getUserSet());
    }
}
