package com.example.puyuan.UserSet.Default;

import com.example.puyuan.AppUser.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DefaultRepository extends JpaRepository<DefaultEntity, Long> {
    Optional<DefaultEntity> findAllByAppUser(AppUserEntity appUser);
    Optional<DefaultEntity> findById(Long userId);
}
