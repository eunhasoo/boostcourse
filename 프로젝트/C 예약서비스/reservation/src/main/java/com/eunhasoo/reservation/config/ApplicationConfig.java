package com.eunhasoo.reservation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/*
 * DAO와 Service의 패키지에 있는 bean들을 스캔하고
 * DBConfig 파일과 연결하는, Service와 DAO와 관련한 설정파일 클래스
 */
@Configuration
@ComponentScan(basePackages = { "com.eunhasoo.reservation.dao", "com.eunhasoo.reservation.service" })
@Import({ DBConfig.class })
public class ApplicationConfig {

}
