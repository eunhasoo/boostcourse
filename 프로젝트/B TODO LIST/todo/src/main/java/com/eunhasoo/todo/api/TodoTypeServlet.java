package com.eunhasoo.todo.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eunhasoo.todo.dao.TodoDao;
import com.eunhasoo.todo.dto.TodoDto;

@WebServlet("/todotype")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TodoTypeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		String type = request.getParameter("type");
		TodoDto todoDto = new TodoDto(id, type);
		System.out.println(id +" "+ type);
		TodoDao todoDao = new TodoDao();
		int result = todoDao.updateTodo(todoDto);
		System.out.println(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
