package com.example.puyuan.Notify.notification;

import com.example.puyuan.AppUser.AppUserEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity,Long> {

    @Query("SELECT bp FROM NotificationEntity bp WHERE bp.appUser = :appUser ORDER BY bp.created_at DESC")
    List<NotificationEntity> findFirstByAppUserOrderByCreatedAtDesc(@Param("appUser") AppUserEntity appUser);
}
