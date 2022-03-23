package com.jnh.mj.service;

import com.jnh.mj.dto.MapBoardSaveDTO;
import com.jnh.mj.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository br;

    @Override
    public Long save(MapBoardSaveDTO mapBoardSaveDTO) throws IllegalStateException, IOException {

        return null;
    }
}
