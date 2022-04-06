package com.jnh.mj.controller;

import com.jnh.mj.dto.MapBoardSaveDTO;
import com.jnh.mj.dto.UserLoginDTO;
import com.jnh.mj.service.BoardService;
import com.jnh.mj.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.jnh.mj.common.SessionConst.*;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService bs;
    private final UserService us;

    // 글 작성 폼
    @GetMapping("save")
    public String saveForm(Model model) {
        model.addAttribute("board", new MapBoardSaveDTO());
        return "board/save";
    }

    // 구글맵 마커 저장 글작성
    @PostMapping("save")
    public String save(@Validated @ModelAttribute MapBoardSaveDTO mapBoardSaveDTO, HttpSession session) throws IllegalStateException, IOException {
        bs.save(mapBoardSaveDTO);
        return "redirect:/board/user/" + session.getAttribute(LOGIN_ID);
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

        return "redirect:/board/{boardId}";
    }

    // 글 삭제
    @DeleteMapping("{boardId}")
    public String delete() {
        return "redirect:/board/";
    }

    // 나의 글 조회
    @GetMapping("/user/{userId}")
    public String myBoard(HttpSession session) {
        return "redirect:/board/user/" + session.getAttribute(LOGIN_ID);
    }

    // 찜

    // 해시태그

}
