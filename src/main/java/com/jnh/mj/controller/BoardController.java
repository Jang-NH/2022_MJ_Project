package com.jnh.mj.controller;

import com.jnh.mj.dto.MapBoardSaveDTO;
import com.jnh.mj.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService bs;

    // 글 작성 폼
    @GetMapping("save")
    public String saveForm(Model model) {
        model.addAttribute("board", new MapBoardSaveDTO());
        return "board/save";
    }

    // 구글맵 마커 저장 글작성
    @PostMapping("save")
    public String save(@Validated @ModelAttribute MapBoardSaveDTO mapBoardSaveDTO) throws IllegalStateException, IOException {
        bs.save(mapBoardSaveDTO);
        return "redirect:/board/";
    }

    // 글목록 (무한스크롤)
    @GetMapping("")
    public String paging() {
        return "/board/findAll";
    }

    // 글 수정 폼

    // 글 수정

    // 글 삭제

    // 찜

    // 무한스크롤

    // 해시태그

}
