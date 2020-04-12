package aboutme;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public TodayServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ZoneId id = ZoneId.of("Asia/Seoul");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/M/d H:mm");
		String now = LocalDateTime.now(id).format(formatter);
		System.out.println();
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>aboutme</title>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("<a href=\"index.html\">메인화면</a><br/>");
		out.println("<h1 style=\"text-align:center\">현재시간 : " + now +"</h1>");
		
		out.println("</body>");
		out.println("</html>");
	}

}
