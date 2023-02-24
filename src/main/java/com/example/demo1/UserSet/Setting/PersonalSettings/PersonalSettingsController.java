package com.example.demo1.UserSet.Setting.PersonalSettings;

import com.example.demo1.base.StatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "PersonalSettingController",
        description = "個人設定接口")
public class PersonalSettingsController {
    private final PersonalSettingsService service;
    @Operation(summary = "個人設定")
    @PatchMapping("/user/setting")
    public ResponseEntity<StatusResponse> personalSettings(
            @RequestBody Map<String, Object> request
            ){
        return ResponseEntity.ok(service.personalSettings(request));
    }
}
