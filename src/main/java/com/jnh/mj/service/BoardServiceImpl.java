package com.jnh.mj.service;

import com.jnh.mj.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl {
    private final BoardRepository br;
}
