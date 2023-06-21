package com.example.puyuan.Measurement.Weight;

import com.example.puyuan.AppUser.AppUserEntity;
import com.example.puyuan.Diary.DiaryDietEntity;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WeightRepository extends JpaRepository<WeightEntity, Long> {
    @Query("SELECT e FROM WeightEntity e WHERE e.recorded_at >= :startDateTime AND e.recorded_at < :endDateTime AND e.appUser = :appUser " +
            "AND e.recorded_at = (SELECT MAX(b.recorded_at) FROM WeightEntity b WHERE b.recorded_at >= :startDateTime AND b.recorded_at < :endDateTime AND b.appUser = :appUser)")
    List<WeightEntity> findLatestByRecordedAtBetweenAndAppUser(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime, @Param("appUser")AppUserEntity appUser);

    boolean existsByIdAndAppUser(Long aLong,AppUserEntity appUser);

    @Transactional
    @Modifying
    @Query("DELETE  FROM WeightEntity d WHERE d.id IN :ids AND d.appUser = :appUser")
    void deleteByIdAndAppUser(@Param("ids") List<Long> ids,@Param("appUser") AppUserEntity appUser);

    @Query("SELECT bp FROM WeightEntity bp WHERE bp.appUser = :appUser ORDER BY bp.recorded_at DESC")
    List<WeightEntity> findFirstByAppUserOrderByRecordedAtDesc(@Param("appUser") AppUserEntity appUser);
}
