package com.example.demo1.UserSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSetRepository extends JpaRepository<UserSetting, Long> {
//    Optional<List<UserSet>> findAll(String All);
//    Optional<UserSet> findById(String id);
    Optional<UserSetting> findByEmail(String userEmail);
}
