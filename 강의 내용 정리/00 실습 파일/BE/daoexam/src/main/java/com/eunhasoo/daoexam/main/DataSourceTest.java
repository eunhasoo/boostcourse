package com.eunhasoo.daoexam.main;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.eunhasoo.daoexam.config.ApplicationConfig;

public class DataSourceTest {

	/*
	 * DataSource를 Test 해보는 클래스
	 */
	
	public static void main(String[] args) {
		// DI 컨테이너를 생성
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		DataSource dataSource = applicationContext.getBean(DataSource.class); // DataSource 빈 가져오기
		Connection connection = null;
		try {
			connection = dataSource.getConnection(); // 커넥션 얻어오기
			if (connection != null)
				System.out.println("접속 성공!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

}
