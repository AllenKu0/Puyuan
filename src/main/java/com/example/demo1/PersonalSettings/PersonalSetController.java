package com.example.demo1.PersonalSettings;

import com.example.demo1.UserSet.UserSetting;
import com.example.demo1.base.StatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "PersonalSettingsController",
        description = "個人資訊接口")
@Slf4j
public class PersonalSetController {
    private final PersonalSetService service;

    @Operation(summary = "個人資訊設定")
    @PatchMapping("/user")
    public ResponseEntity<StatusResponse> userSet(
            @RequestBody Map<String, Object> request
    ){
        log.info("!!!! {}", request.getOrDefault("birthday", "空~~~"));
        return ResponseEntity.ok(service.userSet(request));
    }

//    @Operation(summary = "個人資訊設定")
//    @PatchMapping("/userset/{id}")
//    public ResponseEntity<UserSet> userSetting(
//            @PathVariable Long id,
//            @RequestBody UserSet request
//    ){
//        return ResponseEntity.ok(service.userSetting(id,request));
//    }

    @Operation(summary = "查看個人資訊")
    @GetMapping("/user")
    public ResponseEntity<UserSetting> getUserSet(){
        return ResponseEntity.ok(service.getUserSet());
    }
}
