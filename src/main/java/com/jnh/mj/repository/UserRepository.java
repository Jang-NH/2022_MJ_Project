package com.jnh.mj.repository;

import com.jnh.mj.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserEntity, Long> {
}
