package com.jnh.mj.controller;

import com.jnh.mj.dto.UserSaveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {

    // 회원가입 폼
    @GetMapping("save")
    public String saveForm() {
        return "user/save";
    }

    // 회원가입
    @GetMapping("save")
    public String save(@ModelAttribute UserSaveDTO userSaveDTO) {

        return null;
    }

    // 로그인 폼
    @GetMapping("login")
    public String loginForm() { return  "user/login"; }

    // 로그인

    // 카카오 로그인 API

}
