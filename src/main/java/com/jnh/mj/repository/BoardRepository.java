package com.jnh.mj.repository;

import com.jnh.mj.entity.MapBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository <MapBoardEntity, Long> {
}
