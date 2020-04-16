package examples;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/front")
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int diceValue = (int) (Math.random() * 6) + 1;
		request.setAttribute("dice", diceValue);
		// path는 한 애플리케이션 내부에서 이동 가능한 경로만 지정할 수 있음
		
		RequestDispatcher requestDispatcher;
		//requestDispatcher = request.getRequestDispatcher("/next"); // 서블릿으로 forward 하기
		requestDispatcher = getServletContext().getRequestDispatcher("/nextJsp.jsp"); // jsp로 forward 하기
		
		requestDispatcher.forward(request, response); // request와 response 정보를 forward한다
		
	}

}
