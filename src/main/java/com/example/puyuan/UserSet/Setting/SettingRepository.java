package com.example.puyuan.UserSet.Setting;

import com.example.puyuan.AppUser.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettingRepository extends JpaRepository<SettingEntity, Long> {
    Optional<SettingEntity> findAllByAppUser(AppUserEntity appUser);
    Optional<SettingEntity> findById(Long id);
}
