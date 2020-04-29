package com.eunhasoo.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.eunhasoo.todo.dto.TodoDto;

public class TodoDao {

	private final String dbUrl = "jdbc:mysql://localhost:3306/connectdb";
	private final String dbUser = "connectuser";
	private final String dbPasswd = "connect123!@#";

	public int addTodo(TodoDto todoDto) {
		int addResult = 0;
		String sql = "INSERT INTO todo(title, name, sequence) VALUES(?, ?, ?)";
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, todoDto.getTitle());
			preparedStatement.setString(2, todoDto.getName());
			preparedStatement.setInt(3, todoDto.getSequence());
			addResult = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addResult;
	}

	public List<TodoDto> getTodos() {
		List<TodoDto> todos = new ArrayList<>();
		String sql = "SELECT id, title, name, sequence, type, regdate FROM todo ORDER BY regdate DESC";
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Long id = resultSet.getLong("id");
					String title = resultSet.getString("title");
					String name = resultSet.getString("name");
					int sequence = resultSet.getInt("sequence");
					String type = resultSet.getString("type");
					String regDate = resultSet.getString("regdate");
					todos.add(new TodoDto(id, name, regDate, sequence, title, type));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return todos;
	}

	public int updateTodo(TodoDto todoDto) {
		int updateResult = 0;
		String sql = "UPDATE todo SET type = ? WHERE id = ?";
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			String now = todoDto.getType().toUpperCase();
			String next;
			switch (now) {
			case "TODO":
				next = "DOING";
				break;
			case "DOING":
				next = "DONE";
				break;
			default:
				next = now;
				break;
			}
			preparedStatement.setString(1, next);
			preparedStatement.setLong(2, todoDto.getId());
			updateResult = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateResult;
	}
}
