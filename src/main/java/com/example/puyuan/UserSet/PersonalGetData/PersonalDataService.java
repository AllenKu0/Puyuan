package com.example.puyuan.UserSet.PersonalGetData;

import com.example.puyuan.AppUser.AppUserEntity;
import com.example.puyuan.AppUser.AppUserRepository;
import com.example.puyuan.Base.StatusResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
@Service
@AllArgsConstructor
public class PersonalDataService {
    private final AppUserRepository appUserRepository;

    public Map<String, Object> getUserSet() {
        var userDetails = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var userData = appUserRepository.findById(userDetails.getId()).orElseThrow();
        var response = new LinkedHashMap<String, Object>();
        response.put("status", StatusResponse.RC.SUCCESS.getCode());
        response.put("user", userData.getUserSets());
        response.put("default",userData.getUserDefaults());
        response.put("setting",userData.getUserSettings());
        return response;
    }
}
