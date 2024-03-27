package com.cjcs.bnb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 인터셉터를 이용해서 모든 경로에서 로그인여부 체크하려고 함!
// 그리고 @Scheduled 사용할수있게 하려고 함.
// 환경설정 클래스
@Configuration
@EnableScheduling
public class WebConfig implements WebMvcConfigurer { // 저 인터페이스를 구현하는거임.. 스프링레거시에서는 config 관련은 다 xml파일에다 했는데.. 지금은 자바config에..

	@Autowired
	SessionInterceptor interceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(interceptor)
				// .addPathPatterns("/**") // **로 표기하면 /아래 이중 삼중경로까지 모두 포함
				.excludePathPatterns("/*", "/booknbook", "/js/**", "/css/**", "/images/**", "/uploads/**",  // "/"가 아니라 "/*"로 해야 먹히는데??
					"/member/**", "/books/**", "/bookstore/**",
					"/search/**", "/map/**", "/maps.googleapis.com/maps/api/**", "/map",
					"/get_store_details/**", "/report/**", "/report/report/**")  // 로그인 체크 배제할경로들
				.addPathPatterns("/member/logout", "/member/changePw", "/member/unregister");  // 로그인 체크 할 경로들

	}
}

