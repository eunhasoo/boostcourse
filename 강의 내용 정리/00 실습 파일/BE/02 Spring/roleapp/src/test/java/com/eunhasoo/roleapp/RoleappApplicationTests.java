package com.eunhasoo.roleapp;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleappApplicationTests {

	@Autowired
	DataSource dataSource;
	
	@Test
	public void connectionTest() throws SQLException {
		Connection conn = dataSource.getConnection();
		Assert.assertNotNull(conn);
	}
}
