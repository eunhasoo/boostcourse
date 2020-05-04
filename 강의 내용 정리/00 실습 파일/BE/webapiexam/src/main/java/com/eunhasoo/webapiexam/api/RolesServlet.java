package com.eunhasoo.webapiexam.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eunhasoo.jdbcexam.dao.RoleDao;
import com.eunhasoo.jdbcexam.dto.Role;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/roles")
public class RolesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RolesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 응답을 위한 인코딩과 응답 타입을 지정한다
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json"); // ie 10부터 지원
		
		// DAO를 이용해 Role 목록을 가져온다
		RoleDao roleDao = new RoleDao();
		List<Role> list = roleDao.getRoles();
		
		// List 객체를 JSON 문자열로 변환한다 
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(list); // 파라미터로 받은 객체를 String으로 바꿔준다
		
		PrintWriter out = response.getWriter();
		out.println(json); // JSON 문자열을 출력
		out.close();
	}

}
