package com.example.demo1.UserSet.Default.PersonalDefault;

import com.example.demo1.base.StatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "DefaultSettingsController",
        description = "個人預設值接口")
public class PersonalDefaultController {
    private final PersonalDefaultService service;

    @Operation(summary = "個人預設值")
    @RequestMapping("/user/default")
    public ResponseEntity<StatusResponse> defaultSetting(
            @RequestBody Map<String, Object> request
            ){
        return ResponseEntity.ok(service.defaultSetting(request));
    }
}
