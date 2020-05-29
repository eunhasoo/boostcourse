package examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/param")
public class ParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ParamServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 컨텐츠 타입 지정
		response.setContentType("text/html;charset=utf-8");
		
		// 응답을 출력할 Writer 연결
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Parameter Servlet</title></head>");
		out.println("<body>");
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		// 파라미터가 존재하지 않으면 초기값은 null
		out.println("제 이름은 " + name + "(이)고, <br/>");
		out.println("나이는 " + age + "세 입니다.<br/>");
		
		out.println("</body>");
		out.println("</html>");
	}

}
