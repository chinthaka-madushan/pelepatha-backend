package org.wdn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wdn.entity.UserEntity;
import org.wdn.util.UserType;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    List<UserEntity> findByName(String name);
    List<UserEntity> findAllByEmail(String email);
    UserEntity findByUserType(UserType userType);
}
