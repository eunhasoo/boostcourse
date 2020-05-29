package com.eunhasoo.daoexam.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
 * 데이터 베이스를 설정하는 클래스
 */
@Configuration
@EnableTransactionManagement // 트랜잭션 관련 어노테이션
public class DBConfig {

	private String driverClassName = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/connectdb?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul";
	private String username = "connectuser";
	private String password = "connect123!@#";
	
	// DataSource 객체를 Bean으로 등록
	@Bean
	public DataSource dataSource() {
		// 커넥션을 관리하기 위해 필요한 정보들을 알고 있어야 함
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
}
