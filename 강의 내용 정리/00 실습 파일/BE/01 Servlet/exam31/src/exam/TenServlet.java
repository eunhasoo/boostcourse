package exam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet("/ten")
@WebServlet("/print10")
public class TenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TenServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); // 응답에 대한 정보를 알려줌
		PrintWriter out = response.getWriter(); // PrintWriter 객체를 리턴
		out.print("<h1>1-10까지 출력!!</h1>");
		for (int i = 1; i <= 10; i++) {
			out.print(i + "<br/>"); // <br/>은 줄바꿈 HTML 태그!
		}
		out.close();
	}

}
