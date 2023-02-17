package com.example.demo1.demo;

import com.example.demo1.UserSet.UserSetRepository;
import com.example.demo1.UserSet.UserSetting;
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

    @GetMapping("/hello/DBTest")
    public ResponseEntity<Map<String, Object>> BDTest() {
        List<AppUser> appUsers = appUserRepository.findAll();
        List<UserSetting> userSets = userSetRepository.findAll();

        List<Object> mergedData = new ArrayList<>();
        mergedData.addAll(appUsers);
        mergedData.addAll(userSets);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", mergedData);
        return ResponseEntity.ok().body(responseBody);
    }
    @GetMapping("/hello")
    public String sayHello(){
        return "hello world!!!";
    }
}
