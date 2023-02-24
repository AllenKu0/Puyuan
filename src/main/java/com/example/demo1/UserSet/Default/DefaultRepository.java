package com.example.demo1.UserSet.Default;

import com.example.demo1.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DefaultRepository extends JpaRepository<DefaultEntity, Long> {
    Optional<DefaultEntity> findAllByAppUser(AppUser appUser);
    Optional<DefaultEntity> findById(Long userId);
}
