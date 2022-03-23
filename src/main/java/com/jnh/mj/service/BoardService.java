package com.jnh.mj.service;

import com.jnh.mj.dto.MapBoardSaveDTO;

import java.io.IOException;

public interface BoardService {
    void save(MapBoardSaveDTO mapBoardSaveDTO) throws IllegalStateException, IOException;
}
