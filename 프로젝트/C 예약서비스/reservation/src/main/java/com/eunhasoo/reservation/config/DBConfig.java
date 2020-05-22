package com.eunhasoo.reservation.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration // 설정 클래스 임을 명시
@EnableTransactionManagement // 트랜잭션 관련 설정을 자동으로 처리해줌
public class DBConfig implements TransactionManagementConfigurer {

	// 데이터 소스 빈 등록 (데이터베이스 이용)
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DBInfo.driverClassName);
		dataSource.setUrl(DBInfo.url);
		dataSource.setUsername(DBInfo.username);
		dataSource.setPassword(DBInfo.password);
		return dataSource;
	}

	// 트랜잭션을 처리할 트랜잭션 매니저를 반환하는 메소드 오버라이딩
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManager();
	}

	// PlatformTransactionManager를 반환하는 메소드 오버라이딩
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

}
