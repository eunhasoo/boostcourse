package examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/scope02")
public class ApplicationScope02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ApplicationScope02() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ServletContext application = getServletContext();

		try { // ApplicationScope01 서블릿이 실행되기 전에 실행될 경우 NullPointerException이 발생할 수 있으므로 예외 지정
			int value = (int) application.getAttribute("value");
			value++;
			application.setAttribute("value", value);
			out.println("<h1>value : " + value + "</h1>");
		} catch (NullPointerException ex) {
			out.println("value가 설정되지 않습니다.");
		}
	}

}
