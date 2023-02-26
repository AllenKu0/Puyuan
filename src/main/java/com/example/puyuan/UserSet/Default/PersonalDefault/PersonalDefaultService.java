package com.example.puyuan.UserSet.Default.PersonalDefault;

import com.example.puyuan.UserSet.Default.DefaultEntity;
import com.example.puyuan.UserSet.Default.DefaultRepository;
import com.example.puyuan.AppUser.AppUserEntity;
import com.example.puyuan.Base.StatusResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Service
@AllArgsConstructor
public class PersonalDefaultService {

    private final DefaultRepository repository;
    public StatusResponse defaultSetting(Map<String, Object> request) {
        var appUser = ((AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        var userDefault = repository.findAllByAppUser(appUser)
                .orElse(
                        DefaultEntity.builder()
                                .appUser(appUser)
                                .build()
                );

//        BeanUtils.copyProperties(request, userDefault);

        request.forEach((key, value)->{
            Field field = ReflectionUtils.findField(DefaultEntity.class, key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, userDefault, value);
        });
        repository.save(userDefault);
        return StatusResponse.SUCCESS();
    }
}
