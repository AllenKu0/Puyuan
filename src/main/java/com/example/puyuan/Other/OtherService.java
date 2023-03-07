package com.example.puyuan.Other;

import com.example.puyuan.AppUser.AppUserEntity;
import com.example.puyuan.Base.StatusResponse;
import com.example.puyuan.UserSet.UserInformation.UserSetRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OtherService {
    private final UserSetRepository userSetRepository;

    /**
     * 更新Badge
     */
    public StatusResponse updateBadge(int num) {
        var appUser = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var data = userSetRepository.findByAppUser(appUser).orElseThrow();
        data.setBadge(num);
        userSetRepository.save(data);
        return StatusResponse.SUCCESS();
    }
}
