package examples;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logic")
public class LogicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogicServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로직은 서블릿에서 수행하고, 출력은 jsp에서 담당하기
		
		int v1 = (int) (Math.random() * 100) + 1;
		int v2 = (int) (Math.random() * 100) + 1;
		int result = v1 + v2;
		
		request.setAttribute("v1", v1);
		request.setAttribute("v2", v2);
		request.setAttribute("result", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/print.jsp");
		dispatcher.forward(request, response); // print.jsp로 forward 한다
	}

}
