package com.example.demo1.UserSet.Setting.PersonalSettings;

import com.example.demo1.UserSet.Setting.SettingEntity;
import com.example.demo1.UserSet.Setting.SettingRepository;
import com.example.demo1.appuser.AppUser;
import com.example.demo1.base.StatusResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
@Service
@AllArgsConstructor
public class PersonalSettingsService {

    private final SettingRepository repository;
    public StatusResponse personalSettings(Map<String, Object> request) {
//        var response = StatusResponse.builder();
        var userApp = ((AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        var userSetting = repository.findAllByAppUser(userApp)
                .orElse(
                        SettingEntity.builder()
                                .appUser(userApp)
                                .build()
                );

        request.forEach((key, value)->{
            Field field = ReflectionUtils.findField(SettingEntity.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, userSetting, value);
        });
        repository.save(userSetting);
        return StatusResponse.SUCCESS();
    }
}
