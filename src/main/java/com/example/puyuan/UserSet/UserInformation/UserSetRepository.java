package com.example.puyuan.UserSet.UserInformation;

import com.example.puyuan.AppUser.AppUserEntity;
import com.example.puyuan.Friend.FriendRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserSetRepository extends JpaRepository<UserSetEntity, Long> {
    Optional<UserSetEntity> findByAppUser(AppUserEntity appUser);

    Optional<List<UserSetEntity>> findByIdIn(List<Long> id);
    Optional<List<UserSetEntity>> findByAppUser_IdIn(List<Long> ids);

    Optional<UserSetEntity> findByEmail(String email);

    Optional<UserSetEntity> findByInviteCode(String inviteCode);


}
