package com.jnh.mj.controller;

import com.jnh.mj.dto.MapBoardSaveDTO;
import com.jnh.mj.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("{boardId}")
    public String updateForm() {
        return "/board/update";
    }

    // 글 수정
    @PostMapping("{boardId}")
    public String update() {
        return "redirect:/board/findById";
    }

    // 글 삭제
    @DeleteMapping("{boardId}")
    public String delete() {
        return "redirect:/board/findAll";
    }

    // 찜

    // 해시태그

}
