package com.example.puyuan.Base;

import com.example.puyuan.AppUser.AppUserEntity;
import io.micrometer.common.lang.NonNullApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * 自動填入實體類中的@CreateBy
 */
@Configuration
@Slf4j
@NonNullApi
public class UserAuditor implements AuditorAware<AppUserEntity> {

    @Override
    public Optional<AppUserEntity> getCurrentAuditor() {
        try {
            AppUserEntity user = (AppUserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
