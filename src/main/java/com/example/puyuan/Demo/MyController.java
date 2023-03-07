package com.example.puyuan.Demo;

import com.example.puyuan.UserSet.Default.DefaultEntity;
import com.example.puyuan.UserSet.Default.DefaultRepository;
import com.example.puyuan.UserSet.Setting.SettingEntity;
import com.example.puyuan.UserSet.Setting.SettingRepository;
import com.example.puyuan.UserSet.UserInformation.UserSetEntity;
import com.example.puyuan.UserSet.UserInformation.UserSetRepository;
import com.example.puyuan.AppUser.AppUserEntity;
import com.example.puyuan.AppUser.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class MyController {

    private final AppUserRepository appUserRepository;
    private final UserSetRepository userSetRepository;

    private final DefaultRepository defaultRepository;
    private final SettingRepository settingRepository;

    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/DBTest")
    public ResponseEntity<Map<String, Object>> BDTest() {
        List<AppUserEntity> appUsers = appUserRepository.findAll();
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

    @PostMapping("/redis")
    public ResponseEntity<String> redisSet() {
        redisTemplate.opsForValue().set("name", "Tom");
        return ResponseEntity.ok("上傳成功");
    }

    @GetMapping("/redis")
    public String redisGet() {
        Object name =redisTemplate.opsForValue().get("name");
        return name.toString();
    }
}
