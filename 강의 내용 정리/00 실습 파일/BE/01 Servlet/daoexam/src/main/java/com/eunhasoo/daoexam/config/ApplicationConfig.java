package com.eunhasoo.daoexam.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ DBConfig.class}) // 나누어진 설정 파일들을 연계시키는 어노테이션
@ComponentScan(basePackages = { "com.eunhasoo.daoexam.dao" }) // 해당 패키지에 어노테이션이 존재하는 클래스 있는지 탐색
public class ApplicationConfig {

}
