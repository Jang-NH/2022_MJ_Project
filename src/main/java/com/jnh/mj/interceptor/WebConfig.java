package com.jnh.mj.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 로그인 여부에 따른 접속 가능 페이지 구분
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginCheckInterceptor()) // 만든 LoginCheckInterceptor 클래스 내용을 넘김
                .order(1) // 해당 인터셉터가 적용되는 순서
                .addPathPatterns("/**") // 해당 프로젝트의 모든 주소에 대해 인터셉터를 적용 (무조건 * 두개)
                .excludePathPatterns("/","/user/save","/user/login","/user/kakaologin","/user/sendEmail","/user/logout","css/**","/image/**","/profile/**","/user/emailDuplication"
                ); // 그 중에 이 주소는 제외 (로그인 하지 않아도 접속 가능)
    }
}
