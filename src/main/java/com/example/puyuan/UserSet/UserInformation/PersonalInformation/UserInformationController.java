package com.example.puyuan.UserSet.UserInformation.PersonalInformation;

import com.example.puyuan.base.StatusResponse;
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
        description = "個人資訊更新接口")
@Slf4j
public class UserInformationController {
    private final UserInformationService service;

    @Operation(summary = "個人資訊更新")
    @PatchMapping("/user")
    public ResponseEntity<StatusResponse> userSet(
            @RequestBody Map<String, Object> request
    ){
//        log.info("!!!! {}", request.getOrDefault("birthday", "空~~~"));
        return ResponseEntity.ok(service.userSet(request));
    }

}
