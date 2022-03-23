package com.jnh.mj.interceptor;

import com.jnh.mj.common.SessionConst;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 사용자가 요청한 주소
        String requestURI = request.getRequestURI();
        // 세션 가져옴
        HttpSession session = request.getSession();
        // 세션에 로그인 정보가 있는지 확인
        if(session.getAttribute(SessionConst.LOGIN_EMAIL) == null) {
            // 로그인하지 않은 상태
            // 로그인 하지 않은 경우 바로 로그인 페이지로 보내고 로그인을 하면 요청한 화면을 보여줌.
            session.setAttribute("redirectURL", requestURI);
            response.sendRedirect("/user/login");
            return false;

        } else {
            // 로그인 상태
            return true;
        }
    }
}
