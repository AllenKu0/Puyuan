package com.example.puyuan.UserSet.UserInformation;

import com.example.puyuan.AppUser.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserSetRepository extends JpaRepository<UserSetEntity, Long> {
    Optional<UserSetEntity> findByAppUser(AppUserEntity appUser);
}
