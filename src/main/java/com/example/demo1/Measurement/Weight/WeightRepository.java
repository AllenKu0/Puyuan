package com.example.demo1.Measurement.Weight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeightRepository extends JpaRepository<WeightEntity, Long> {
}
