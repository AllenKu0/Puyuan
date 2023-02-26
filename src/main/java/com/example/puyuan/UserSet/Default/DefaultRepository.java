package com.example.puyuan.UserSet.Default;

import com.example.puyuan.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DefaultRepository extends JpaRepository<DefaultEntity, Long> {
    Optional<DefaultEntity> findAllByAppUser(AppUser appUser);
    Optional<DefaultEntity> findById(Long userId);
}
