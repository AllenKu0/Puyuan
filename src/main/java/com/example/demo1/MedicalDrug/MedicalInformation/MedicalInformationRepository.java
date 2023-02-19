package com.example.demo1.MedicalDrug.MedicalInformation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalInformationRepository extends JpaRepository<MedicalInformationEntity, Long> {
}
