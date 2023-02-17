package com.example.demo1.PersonalSettings;

import com.example.demo1.UserSet.UserSetRepository;
import com.example.demo1.UserSet.UserSetting;
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
public class PersonalSetService {
    private final UserSetRepository setRepository;

    public StatusResponse userSet(Map<String ,Object> request) {
        var response = StatusResponse.builder();
        var userEmail = ((AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
        var userSet = setRepository.findByEmail(userEmail).orElseThrow();
        request.forEach((key, value)->{
            Field field = ReflectionUtils.findField(UserSetting.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, userSet, value);
        });
        setRepository.save(userSet);
        return response.status(StatusResponse.RC.SUCCESS.getCode()).build();
    }

//    public UserSet userSetting(Long id,UserSet request) {
//        var user = setRepository.findById(id).orElseThrow();
//        user.setAddress(request.getAddress());
//        user.setName(request.getName());
//        setRepository.save(user);
//        return user;
//    }

    public UserSetting getUserSet() {
        var userDetails = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userEmail = userDetails.getEmail();
        return setRepository.findByEmail(userEmail).orElseThrow();
    }
}
