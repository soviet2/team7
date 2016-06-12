package GreenLight;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

// 그린라이트 취소 서블릿, 그러나 거절용으로도 사용 가능
public class CanselGreenLight extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// 이 구문은 입력 방식에 따라 달라진다.
		String setCode = request.getParameter("setCode"); // 상대방 코드(내가 누구에서
															// 보냈는지)
		String check = request.getParameter("check"); // 보낸 그린라이트의 취소 또는 받은
														// 그린라이트 거절 구별용

		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("LOGIN_ID");

		if (check.equals("거절")) { // 받은 그린라이트 거절
			canselSetGreen(code, setCode); // 받은 그린라이트 setcode부분 삭제
			canselGetGreen(setCode, code); // 받은 그린라이트 getcode부분 삭제
			response.sendRedirect("Home.jsp?BODY_PATH=getgreenshow");
		} else { // 보낸 그린라이트 취소
			canselSetGreen(setCode, code); // 보낸 그린라이트 setcode 삭제
			canselGetGreen(code, setCode); // 보낸 그린라이트 getcode 삭제
			response.sendRedirect("Home.jsp?BODY_PATH=setgreenshow");
		}
	}

	private void canselSetGreen(String code, String setCode) throws ServletException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/wdbpool");
			if (conn == null)
				throw new Exception("DB연결 실패");
			stmt = conn.createStatement();
			// String command = String.format(); //문장 합치는 구문
			int rowNum = stmt
					.executeUpdate("delete from green where setcode='" + setCode + "'and code='" + code + "';");
			// setCode와 code값 둘다 가지고 있는 것을 찾아서 삭제한다. 즉, 자신이 보낸 그린라이트를 삭제 가능.
			if (rowNum < 1)
				throw new Exception("데이터를 DB에 입력할 수 없습니다.");
			return;
		} catch (Exception e) {
			// throw new ServletException(e);
		} finally {
			try {
				stmt.close();
			} catch (Exception ignored) {
			}
			try {
				conn.close();
			} catch (Exception ignored) {
			}
		}
	}

	private void canselGetGreen(String setCode, String code) throws ServletException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/wdbpool");
			if (conn == null)
				throw new Exception("DB연결 실패");
			stmt = conn.createStatement();
			// String command = String.format(); //문장 합치는 구문
			int rowNum = stmt
					.executeUpdate("delete from green where code='" + setCode + "'and getcode='" + code + "';");
			// 상대방 그린바구니에 자신의code가 getCode에 저장되어 있으니 그걸 찾아서 삭제함.
			if (rowNum < 1)
				throw new Exception("데이터를 DB에 입력할 수 없습니다.");
			return;
		} catch (Exception e) {
			// throw new ServletException(e);
		} finally {
			try {
				stmt.close();
			} catch (Exception ignored) {
			}
			try {
				conn.close();
			} catch (Exception ignored) {
			}
		}

	}
}
