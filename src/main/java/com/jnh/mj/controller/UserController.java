package com.jnh.mj.controller;

import com.jnh.mj.dto.UserSaveDTO;
import com.jnh.mj.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {

    private final UserService us;

    // 회원가입 폼
    @GetMapping("save")
    public String saveForm(Model model) {
        model.addAttribute("user", new UserSaveDTO());
        return "user/save";
    }

    // 회원가입 처리
    @PostMapping("save")
    public String save(@Validated @ModelAttribute("user") UserSaveDTO userSaveDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "user/save";
        } else {
            us.save(userSaveDTO);
            return "user/login";
        }
    }

    // 카카오 로그인 API

    // 이메일 중복 체크

    // 닉네임 중복 체크

    // 로그인 폼
    @GetMapping("login")
    public String loginForm(Model model) {
        model.addAttribute("user", new UserSaveDTO());
        return  "user/login";
    }

    // 로그인 처리



    // 로그아웃

    // 마이페이지 이동

    // 마이페이지 회원 정보 수정 폼

    // 마이페이지 회원 정보 수정 (프로필 사진 저장)

    // 관리자 페이지

    // 회원 목록

}