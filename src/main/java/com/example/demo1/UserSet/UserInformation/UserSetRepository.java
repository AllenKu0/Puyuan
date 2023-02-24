package com.example.demo1.UserSet.UserInformation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSetRepository extends JpaRepository<UserSetEntity, Long> {
    Optional<UserSetEntity> findByEmail(String userEmail);
}
