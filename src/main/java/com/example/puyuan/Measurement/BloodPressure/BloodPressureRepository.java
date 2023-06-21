package com.example.puyuan.Measurement.BloodPressure;

import com.example.puyuan.AppUser.AppUserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BloodPressureRepository extends JpaRepository<BloodPressureEntity, Long> {
    @Query("SELECT e FROM BloodPressureEntity e WHERE e.recorded_at >= :startDateTime AND e.recorded_at < :endDateTime AND e.appUser = :appUser " +
            "AND e.recorded_at = (SELECT MAX(b.recorded_at) FROM BloodPressureEntity b WHERE b.recorded_at >= :startDateTime AND b.recorded_at < :endDateTime AND b.appUser = :appUser)")
    List<BloodPressureEntity> findLatestByRecordedAtBetweenAndAppUser(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime, @Param("appUser")AppUserEntity appUser);

    boolean existsByIdAndAppUser(Long aLong,AppUserEntity appUser);

    @Transactional
    @Modifying
    @Query("DELETE  FROM BloodPressureEntity d WHERE d.id IN :ids AND d.appUser = :appUser")
    void deleteByIdAndAppUser(@Param("ids") List<Long> ids,@Param("appUser") AppUserEntity appUser);

    @Query("SELECT bp FROM BloodPressureEntity bp WHERE bp.appUser = :appUser ORDER BY bp.recorded_at DESC")
    List<BloodPressureEntity> findFirstByAppUserOrderByRecordedAtDesc(@Param("appUser") AppUserEntity appUser);
}
