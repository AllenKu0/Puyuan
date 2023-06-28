package com.example.puyuan.Diary;

import com.example.puyuan.AppUser.AppUserEntity;
import com.example.puyuan.Measurement.BloodPressure.BloodPressureEntity;
import com.example.puyuan.Measurement.Weight.WeightEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<DiaryDietEntity,Long> {
    @Query("SELECT e FROM DiaryDietEntity e WHERE e.recorded_at >= :startDateTime AND e.recorded_at < :endDateTime AND e.appUser = :appUser " +
            "AND e.recorded_at = (SELECT MAX(b.recorded_at) FROM DiaryDietEntity b WHERE b.recorded_at >= :startDateTime AND b.recorded_at < :endDateTime AND b.appUser = :appUser)")
    List<DiaryDietEntity> findLatestByRecordedAtBetweenAndAppUser(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime,@Param("appUser") AppUserEntity appUser);

    boolean existsByIdAndAppUser(Long aLong,AppUserEntity appUser);

    @Transactional
    @Modifying
    @Query("DELETE  FROM DiaryDietEntity d WHERE d.id IN :ids AND d.appUser = :appUser")
    void deleteByIdAndAppUser(@Param("ids") List<Long> ids,@Param("appUser") AppUserEntity appUser);

    @Query("SELECT bp FROM DiaryDietEntity bp WHERE bp.appUser = :appUser ORDER BY bp.recorded_at DESC")
    List<DiaryDietEntity> findFirstByAppUserOrderByRecordedAtDesc(@Param("appUser") AppUserEntity appUser);

    @Query(value = "SELECT dd FROM DiaryDietEntity dd WHERE dd.appUser = :appUser ORDER BY dd.recorded_at DESC LIMIT 1")
    DiaryDietEntity findLatestByAppUserOrderByRecorded(@Param("appUser") AppUserEntity appUser);
}
