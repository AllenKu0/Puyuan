package com.example.demo1.a1c;

import com.example.demo1.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface A1cRepository extends JpaRepository<A1cEntity, Long> {

    /**
     * 可根據多筆A1c Id與指定用戶刪除資料
     * @param ids List<Long> A1c Ids
     * @param appUser 用於指定使用者
     */
    @Transactional
    @Modifying
    @Query(
        value = "DELETE FROM A1cEntity a WHERE a.id IN :a1cIds AND a.appUser = :user"
    )
    void deleteByIdsAndAppUser(@Param("a1cIds") List<Long> ids, @Param("user") AppUser appUser);

    /**
     * 可根據單筆A1cs Id與指定用戶刪除資料
     * @param id: A1c Id
     * @param appUser: 用於指定使用者
     */
    @Transactional
    @Modifying
    @Query(
        value = "DELETE FROM A1cEntity a WHERE a.id = :a1cId AND a.appUser = :user"
    )
    void deleteByIdAndAppUser(@Param("a1cId") Long id, @Param("user") AppUser appUser);
}
