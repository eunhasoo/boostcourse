package com.eunhasoo.jdbcexam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eunhasoo.jdbcexam.dto.Role;

public class RoleDao {
	private static String dbUrl = "jdbc:mysql://localhost:3306/connectdb";
	private static String dbUser = "connectuser";
	private static String dbPasswd = "connect123!@#";

	// roleId를 사용해서 Role 객체 하나를 가져오는 메소드
	public Role getRole(Integer roleId) {
		Role role = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// MySQL이 제공해주는 클래스(드라이버)를 메모리에 로드
			Class.forName("com.mysql.jdbc.Driver");
			// Connection 객체 가져오기
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
			// 쿼리문 실행을 위한 Statement을 정의하고 실행
			String sql = "SELECT role_id, description FROM role WHERE role_id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, roleId);
			resultSet = preparedStatement.executeQuery();
			// 결과값 가져오기
			if (resultSet.next()) { // 쿼리문의 결과값이 있다면
				// SQL문에서 나열한 순서대로 컬럼을 하나씩 꺼낸다
				int id = resultSet.getInt("role_id");
				String description = resultSet.getString(2);
				// 꺼낸 값을 Role 객체에 담는다
				role = new Role(id, description);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 연결 닫기
			if (resultSet != null) { // null값을 참조할 경우 에러가 발생하기 때문에 if문으로 미리 예방한다
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return role;
	}

	// Role 객체로 role 하나를 추가하는 메소드
	public int addRole(Role role) {
		int insertCount = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "INSERT INTO role (role_id, description) VALUES (?, ?)";
		// try-with-resources 문법: 괄호안에 선언한 객체들을 자동으로 닫아준다
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
			PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, role.getRoleId());
			preparedStatement.setString(2, role.getDescription());
			insertCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return insertCount;
	}
	
	// Role 테이블의 모든 컬럼을 role_id 내림차순으로 가져오는 메소드
	public List<Role> getRoles() {
		List<Role> roleList = new ArrayList<>();
		
		String sql = "SELECT description, role_id FROM role ORDER BY role_id desc";
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
			PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					String description = resultSet.getString(1);
					int id = resultSet.getInt(2);
					roleList.add(new Role(id, description));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return roleList;
	}
	
	// role 테이블에서 roleId를 이용해서 해당 칼럼을 지우는 메소드
	public int deleteRoleById(Integer roleId) {
		int deleteCount = 0;
		
		String sql = "DELETE FROM role WHERE role_id = ?";
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
			PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, roleId);
			deleteCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return deleteCount;
	}
	
	// role 객체의 id와 일치하는 컬럼의 description 값을 변경하는 메소드
	public int updateRoleDescription(Role role) {
		int updateCount = 0;
		
		String sql = "UPDATE role SET description = ? WHERE role_id = ?";
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
			PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, role.getDescription());
			preparedStatement.setInt(2, role.getRoleId());
			updateCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return updateCount;
	}
}
