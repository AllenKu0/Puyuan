package com.example.puyuan.MedicalDrug.MedicalInformation;

import com.example.puyuan.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalInformationRepository extends JpaRepository<MedicalInformationEntity, Long> {
    Optional<MedicalInformationEntity> findByAppUser(AppUser appUser);
}
