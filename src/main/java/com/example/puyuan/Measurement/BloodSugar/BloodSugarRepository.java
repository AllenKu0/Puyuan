package com.example.puyuan.Measurement.BloodSugar;

import com.example.puyuan.AppUser.AppUserEntity;
import com.example.puyuan.Diary.DiaryDietEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BloodSugarRepository extends JpaRepository<BloodSugarEntity, Long> {
    @Query("SELECT e FROM BloodSugarEntity e WHERE e.recorded_at >= :startDateTime AND e.recorded_at < :endDateTime AND e.appUser = :appUser " +
            "AND e.recorded_at = (SELECT MAX(b.recorded_at) FROM BloodSugarEntity b WHERE b.recorded_at >= :startDateTime AND b.recorded_at < :endDateTime AND b.appUser = :appUser)")
    List<BloodSugarEntity> findLatestByRecordedAtBetweenAndAppUser(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime, @Param("appUser")AppUserEntity appUser);

    boolean existsByIdAndAppUser(Long aLong,AppUserEntity appUser);

    @Transactional
    @Modifying
    @Query("DELETE  FROM BloodSugarEntity d WHERE d.id IN :ids AND d.appUser = :appUser")
    void deleteByIdAndAppUser(@Param("ids") List<Long> ids,@Param("appUser") AppUserEntity appUser);

    @Query("SELECT bp FROM BloodSugarEntity bp WHERE bp.appUser = :appUser ORDER BY bp.recorded_at DESC")
    List<BloodSugarEntity> findFirstByAppUserOrderByRecordedAtDesc(@Param("appUser") AppUserEntity appUser);
}
