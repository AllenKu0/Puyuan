package com.example.demo1.UserSet.Setting;

import com.example.demo1.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettingRepository extends JpaRepository<SettingEntity, Long> {
    Optional<SettingEntity> findAllByAppUser(AppUser appUser);
    Optional<SettingEntity> findById(Long id);
}
