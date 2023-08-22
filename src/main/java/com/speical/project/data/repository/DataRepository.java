package com.speical.project.data.repository;


import com.speical.project.data.entity.DataEntity;
import com.speical.project.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DataRepository extends JpaRepository<DataEntity, Long> {

    Optional<DataEntity> findById(Long id);

    Optional<DataEntity> findByUser(UserEntity user);
}
