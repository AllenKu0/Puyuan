package com.example.puyuan.Care;

import com.example.puyuan.AppUser.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CareRepository extends JpaRepository<CareEntity, Long> {

    Optional<List<CareEntity>> findAllByAppUser(AppUserEntity appUser);
}
