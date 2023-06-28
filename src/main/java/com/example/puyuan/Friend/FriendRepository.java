package com.example.puyuan.Friend;

import com.example.puyuan.MyUtils.Validator.Phone;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Lombok;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<FriendEntity, Long> {

    Optional<List<FriendEntity>> findByUserIdAndStatus(Long user_id, Integer status);

    Optional<List<FriendEntity>> findByStatusAndUserId(Integer status,Long user_id);

    Optional<FriendEntity> findByRelationIdAndStatus(Long relation_id,Integer status);

    Optional<FriendEntity> findByUserIdAndRelationId(Long user_id,Long relation_id);

    Optional<FriendEntity> findByUserIdAndRelationIdAndStatus(Long user_id,Long relation_id,Integer status);

    Optional<FriendEntity> findByIdAndStatus(Long id,Integer status);


}
