package com.example.puyuan.UserSet.Setting;

import com.example.puyuan.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettingRepository extends JpaRepository<SettingEntity, Long> {
    Optional<SettingEntity> findAllByAppUser(AppUser appUser);
    Optional<SettingEntity> findById(Long id);
}
