package com.example.demo1.MedicalDrug.MedicalInformation;

import com.example.demo1.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalInformationRepository extends JpaRepository<MedicalInformationEntity, Long> {
    Optional<MedicalInformationEntity> findByAppUser(AppUser appUser);
}
