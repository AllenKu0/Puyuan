package com.example.demo1.UserSet.UserInformation.PersonalInformation;

import com.example.demo1.UserSet.UserInformation.UserSetEntity;
import com.example.demo1.UserSet.UserInformation.UserSetRepository;
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
public class UserInformationService {

    private final UserSetRepository setRepository;

    public StatusResponse userSet(Map<String ,Object> request) {
        var userDetails = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        var userSet = UserSetEntity.builder().appUser(userDetails).build();
        var userSet = setRepository.findByEmail(userDetails.getEmail())
                .orElse(
                    UserSetEntity.builder()
                    .appUser(userDetails)
                    .name(userDetails.getAccount())
                    .phone(userDetails.getPhone())
                    .email(userDetails.getEmail())
                    .build()
                );

        request.forEach((key, value)->{
            Field field = ReflectionUtils.findField(UserSetEntity.class, key);
            assert field != null; //確保filed不會造成空指針異常
            field.setAccessible(true);
            ReflectionUtils.setField(field, userSet, value);
        });

        setRepository.save(userSet);
        return StatusResponse.SUCCESS();
    }


}
