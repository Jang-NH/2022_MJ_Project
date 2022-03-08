package com.jnh.mj.controller;

import com.jnh.mj.dto.UserDetailDTO;
import com.jnh.mj.dto.UserLoginDTO;
import com.jnh.mj.dto.UserSaveDTO;
import com.jnh.mj.dto.UserUpdateDTO;
import com.jnh.mj.entity.UserEntity;
import com.jnh.mj.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static com.jnh.mj.common.SessionConst.*;

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

        // 유효성 검증 조건문
        if(bindingResult.hasErrors()) {
            return "user/save";
        }
        // 이메일 중복체크 오류 트라이-캐치문
        try {
            us.save(userSaveDTO);
        } catch (IllegalStateException e) {
            bindingResult.reject("emailCheck", e.getMessage());
            return "user/save";
        }
        return "redirect:/user/login";
    }

    // 이메일 중복 체크
    @PostMapping("emailDuplicate")
    public @ResponseBody String emailDuplicate(@RequestParam("userEmail") String userEmail) {
        String result = us.emailDuplicate(userEmail);
        return result;
    }

    // 닉네임 중복 체크
    @PostMapping("nickDuplicate")
    public @ResponseBody String nickDuplicate(@RequestParam("userNick") String userNick) {
        String result = us.nickDuplicate(userNick);
        return result;
    }

    // 로그인 폼
    @GetMapping("login")
    public String loginForm(Model model) {
        return "user/login";
    }

    // 로그인 처리
    @PostMapping("login")
    public String login(@Validated @ModelAttribute("user") UserLoginDTO userLoginDTO, HttpSession session, Model model) {
        if (us.findByUserEmail(userLoginDTO)) {
            session.setAttribute(LOGIN_EMAIL, userLoginDTO.getUserEmail());

            Long userId = us.findByUserId(userLoginDTO.getUserEmail());
            session.setAttribute(LOGIN_ID, userId);

            String userNick = us.findById(userId).getUserNickname();
            session.setAttribute(LOGIN_NICKNAME, userNick);

            String redirectURL = (String) session.getAttribute("redirectURL");

            if (redirectURL != null) {
                return "redirect:" + redirectURL;
            } else {
                return "index";
            }
        } else {
            model.addAttribute("msg", "로그인 실패");
            return "user/login";
        }
    }

    // 카카오 로그인 API
    @PostMapping("kakaologin")
    public String kakaoLogin(@RequestParam(value = "code", required = false) String code, Model model, HttpSession session) throws Exception {
        String access_Token = us.getKaKaoAccessToken(code);
        HashMap<String, Object> userInfo = us.getUserInfo(access_Token);
        System.out.println("access_Token : " + access_Token);
        System.out.println("userEmail : " + userInfo.get("userEmail"));
        System.out.println("userNick : " + userInfo.get("userNick"));
        System.out.println("userProfile : " + userInfo.get("userProfile"));

        // 회원의 이메일이 존재할 때 세션에 해당 이메일과 닉네임, 토큰을 등록
        if (userInfo.get("userEmail") != null) {
            session.setAttribute(LOGIN_EMAIL, userInfo.get("userEmail"));
            session.setAttribute(LOGIN_NICKNAME, userInfo.get("userNick"));
            session.setAttribute("access_Token", access_Token);
        }

        return "index";
    }

    // 로그아웃
    @GetMapping("logout")
    public String logout(HttpSession session) {
        String access_Token = (String)session.getAttribute("access_Token");

        if (access_Token != null && !"".equals(access_Token)) {
            session.removeAttribute("access_Token");
            session.removeAttribute("userId");
        } else {
            session.removeAttribute(LOGIN_ID);
            session.removeAttribute(LOGIN_EMAIL);
            session.removeAttribute(LOGIN_NICKNAME);
        }
        return "index";
    }

    // 마이페이지 이동
    @GetMapping("mypage")
    public String findById(HttpSession session, @RequestParam(value = "code", required = false) String code, Model model) {

        if (session.getAttribute(LOGIN_ID)!= null) {
            Long userId = (Long) session.getAttribute(LOGIN_ID);

            UserDetailDTO userDetailDTO = us.findById(userId);
            model.addAttribute("user", userDetailDTO);
        } else {
            String access_Token = us.getKaKaoAccessToken(code);
            HashMap<String, Object> userInfo = us.getUserInfo(access_Token);
            // 카카오 로그인 회원 정보 출력

        }

        return "user/mypage";
    }

    // 마이페이지 회원 정보 수정 폼
    @GetMapping("{userId}")
    public String updateForm(HttpSession session, Model model) {

        Long userId = (Long) session.getAttribute(LOGIN_ID);

        UserDetailDTO userDetailDTO = us.findById(userId);
        model.addAttribute("user", userDetailDTO);

        return "user/update";
    }

    // 마이페이지 회원 정보 수정 (프로필 사진 저장)
    @PutMapping("{userId}")
    public String update(@PathVariable("userId") Long userId, @ModelAttribute UserUpdateDTO userUpdateDTO) throws IllegalStateException, IOException {
        us.update(userUpdateDTO);
        return "redirect:/user/" + userId ;
    }

    // 관리자 페이지
    @GetMapping("admin")
    public String admin(HttpSession session) {
        return "user/admin";
    }

    // 관리자 페이지 회원 목록
    @GetMapping("")
    public String findAll(Model model) {
        List<UserDetailDTO> userList = us.findAll();
        model.addAttribute("userList", userList);
        return "user/findAll";
    }

    // 관리자가 회원 삭제
    @DeleteMapping("{userId}")
    public String delete(@PathVariable("userId") Long userId) {
        us.delete(userId);
        return "redirect:/user/findAll";
    }

}