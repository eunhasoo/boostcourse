package com.eunhasoo.todo.api;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eunhasoo.todo.dao.TodoDao;
import com.eunhasoo.todo.dto.TodoDto;

@WebServlet("/todoadd")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TodoAddServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String name = request.getParameter("name");
		int sequence = Integer.parseInt(request.getParameter("sequence"));
		TodoDao dao = new TodoDao();
		dao.addTodo(new TodoDto(title, name, sequence));
		response.sendRedirect("main");
	}

}
