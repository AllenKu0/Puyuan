package com.example.puyuan.MedicalDrug.DrugInformation;

import com.example.puyuan.AppUser.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrugInformationRepository extends JpaRepository<DrugInformationEntity, Long> {

    Optional<List<DrugInformationEntity>> findAllByTypeAndUser(Boolean type, AppUserEntity user);

    /**
     * 刪除多筆藥物資訊
     * @param ids 指定要刪除的藥物資訊id
     * @param user 創建此資料的使用者
     */
    @Transactional
    @Modifying
    @Query(
        value = "DELETE FROM DrugInformationEntity d WHERE d.id IN :ids AND d.user = :user"
    )
    int deleteByIdsAndUser(List<Long> ids, AppUserEntity user);
}
