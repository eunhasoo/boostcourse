package examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/next")
public class NextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NextServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><title>forward</title></head>");
		out.println("<body>");
		
		Integer loop = (Integer) request.getAttribute("dice"); // Object로 전달받기 때문에 Integer로 형변환을 해준다
		for (int i = 0; i < loop; i++) {
			out.println("<h3>Hello</h3>");
		}
		
		out.println("</body>");
		out.println("</html>");
	}

}
