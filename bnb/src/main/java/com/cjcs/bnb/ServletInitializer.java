package com.cjcs.bnb;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BnbApplication.class);
	}

}
/*
SpringBootServletInitializer 클래스는 WAR(웹 애플리케이션 아카이브) 형태로 배포할 때 필요합니다.
이 클래스를 상속받은 경우 서블릿 컨테이너가 애플리케이션을 로드할 때 Spring Boot 애플리케이션을 올바르게 초기화할 수 있습니다.
일반적으로 Spring Boot 애플리케이션을 개발할 때는 내장형 서블릿 컨테이너(예: Tomcat, Jetty)를 사용하여 실행하므로
SpringBootServletInitializer 클래스를 사용하지 않아도 됩니다.
그러나 WAR 파일로 애플리케이션을 배포하려는 경우에는 이 클래스를 추가해야 합니다.
 */
