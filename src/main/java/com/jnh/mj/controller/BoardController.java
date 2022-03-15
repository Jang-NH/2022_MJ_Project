package com.jnh.mj.controller;

import com.jnh.mj.dto.BoardSaveDTO;
import com.jnh.mj.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService bs;

    // 글 작성 폼
    @GetMapping("save")
    public String saveForm(Model model) {
        model.addAttribute("board", new BoardSaveDTO());
        return "board/save";
    }

    // 구글맵 마커 저장 글작성

    // 글목록

    // 글 수정 폼

    // 글 수정

    // 글 삭제

    // 찜

    // 무한스크롤

    // 해시태그

}
