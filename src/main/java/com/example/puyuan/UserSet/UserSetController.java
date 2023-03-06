package com.example.puyuan.UserSet;

import com.example.puyuan.UserSet.Default.DefaultRequest;
import com.example.puyuan.UserSet.Setting.SettingRequest;
import com.example.puyuan.Base.StatusResponse;
import com.example.puyuan.UserSet.UserInformation.UserInformationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "UserSetController",
        description = "個人資料設定與查詢接口")
public class UserSetController {
    private final UserSetService service;

    @Operation(summary = "個人資訊查詢")
    @GetMapping("/user")
    public ResponseEntity<?> getUserSet(){
        return ResponseEntity.ok(service.getUserSet());
    }

    @Operation(summary = "個人資訊更新")
    @PatchMapping("/user")
    public ResponseEntity<StatusResponse> userSet(
//            @RequestBody Map<String, Object> request
            @RequestBody UserInformationRequest request
    ){
        return ResponseEntity.ok(service.userSet(request));
    }

    @Operation(summary = "個人預設值更新")
    @RequestMapping("/user/default")
    public ResponseEntity<StatusResponse> defaultSetting(
            @RequestBody DefaultRequest request
    ){
        return ResponseEntity.ok(service.defaultSetting(request));
    }

    @Operation(summary = "個人設定更新")
    @PatchMapping("/user/setting")
    public ResponseEntity<StatusResponse> personalSettings(
            @RequestBody SettingRequest request
    ){
        return ResponseEntity.ok(service.personalSettings(request));
    }


}
