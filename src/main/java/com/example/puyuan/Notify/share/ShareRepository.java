package com.example.puyuan.Notify.share;

import com.example.puyuan.AppUser.AppUserEntity;
import com.example.puyuan.MyUtils.Validator.Phone;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareRepository extends JpaRepository<ShareEntity , Long> {

    List<ShareEntity> findAllByDataTypeAndAppUser(Integer dateType, AppUserEntity appUser);
}
