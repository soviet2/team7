package Light;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

//로그인 했는지 파악하는 서블릿, 로그인 했다면 페이지로 아니면 로그인하게 
//이 서블릿은 그린바구니 링크 누를때 사용하도록, 로그인 부분 다시 변경해야함

public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		request.setCharacterEncoding("utf-8");
		// 세션에서 아이디를 가져온다. 즉, 로그인 파악
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("LOGIN_ID");

		if (id != null) { // 로그인 했으면 GrennLight.jsp 페이지 출력

			session.setAttribute("LOGIN_ID", id);

			RequestDispatcher dispatcher = request.getRequestDispatcher("Green/GreenLight.jsp");
			dispatcher.forward(request, response);
		} else // 아니면 알림창 뜨고 홈페이지로
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
			dispatcher.forward(request, response);
		}

	}

}
