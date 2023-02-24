package com.example.demo1.demo;

import com.example.demo1.UserSet.Default.DefaultEntity;
import com.example.demo1.UserSet.Default.DefaultRepository;
import com.example.demo1.UserSet.Setting.SettingEntity;
import com.example.demo1.UserSet.Setting.SettingRepository;
import com.example.demo1.UserSet.UserInformation.UserSetRepository;
import com.example.demo1.UserSet.UserInformation.UserSetEntity;
import com.example.demo1.appuser.AppUser;
import com.example.demo1.appuser.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MyController {

    private final AppUserRepository appUserRepository;
    private final UserSetRepository userSetRepository;

    private final DefaultRepository defaultRepository;
    private final SettingRepository settingRepository;

    @GetMapping("/hello/DBTest")
    public ResponseEntity<Map<String, Object>> BDTest() {
        List<AppUser> appUsers = appUserRepository.findAll();
        List<UserSetEntity> userSets = userSetRepository.findAll();
        List<DefaultEntity> defaults = defaultRepository.findAll();
        List<SettingEntity> setting = settingRepository.findAll();

        List<Object> mergedData = new ArrayList<>();
        mergedData.addAll(appUsers);
        mergedData.addAll(userSets);
        mergedData.addAll(defaults);
        mergedData.addAll(setting);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", mergedData);
        return ResponseEntity.ok().body(responseBody);
    }
    @GetMapping("/hello")
    public String sayHello(){
        return "hello world!!!";
    }
}
