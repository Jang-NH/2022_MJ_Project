package com.jnh.mj.controller;

import com.jnh.mj.dto.UserDetailDTO;
import com.jnh.mj.dto.UserLoginDTO;
import com.jnh.mj.dto.UserSaveDTO;
import com.jnh.mj.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    @GetMapping("kakaologin")
    public String kakaoLogin(@RequestParam(value = "code", required = false) String code, Model model, HttpSession session) throws Exception {
        String access_Token = us.getKaKaoAccessToken(code);
        String userInfo = us.getUserInfo(access_Token);
        System.out.println("###access_Token#### : " + access_Token);
        System.out.println("###userInfo#### : " + userInfo);
        System.out.println("#########" + code);
        // 해당 이메일로 가입 한 회원이 없다면 회원가입 화면으로 이동 시킴
        if(userInfo.equals("no")){
            model.addAttribute("msg","해당 이메일로 회원가입을 먼저 해주세요");
            model.addAttribute("member", new UserSaveDTO());
            return "/member/save";
        } else {
//            로그인 회원 이메일과 아이디를 세션에 저장
            session.setAttribute(LOGIN_EMAIL, userInfo);
            Long memberId = us.findByUserId(userInfo);
            session.setAttribute(LOGIN_ID, memberId);

            String redirectURL = (String) session.getAttribute("redirectURL");

            if (redirectURL != null){
                return "redirect:" + redirectURL;
            }else{
                return "redirect:/board/";
            }
        }
    }

    // 로그아웃
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    // 마이페이지 이동

    // 마이페이지 회원 정보 수정 폼

    // 마이페이지 회원 정보 수정 (프로필 사진 저장)

    // 관리자 페이지

    // 회원 목록

}