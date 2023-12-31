package com.speical.project.user.repository;


import com.speical.project.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByLoginId(String loginId);
    Optional<UserEntity> findByNickname(String nickname);

}
