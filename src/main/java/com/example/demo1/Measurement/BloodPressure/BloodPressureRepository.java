package com.example.demo1.Measurement.BloodPressure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodPressureRepository extends JpaRepository<BloodPressureEntity, Long> {
}
