package GreenLight;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.sql.*;

//그린라이트 보내는 서블릿
public class SendGreenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// 이 구문은 입력 방식에 따라 달라진다.
		String setCode = request.getParameter("setCode"); // 상대방 코드(내가 누구에서
															// 보냈는지)
		// String code = request.getParameter("code"); //자신을 아는 코드 세션으로 가져오게
		// 변형해야함.

		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("LOGIN_ID");

		sendGreen(code, setCode); // 그린바구니 테이블에서 자신의 코드와 내가 보낸 상대방의 코드를 입력하는 함수.
		getGreen(setCode, code); // 그린바구니에 자신이 받은 내역을 저장.

		response.sendRedirect("Home.jsp?BODY_PATH=Green/GreenLight.jsp");
	}

	private void sendGreen(String code, String setCode) throws ServletException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/wdbpool");
			if (conn == null)
				throw new Exception("DB연결 실패");
			stmt = conn.createStatement();

			ResultSet rs = stmt
					.executeQuery("select * from green where code='" + code + "' and setcode='" + setCode + "';");
			if (!rs.next()) {

				String command = String.format("insert into green(code,setCode) value('%s','%s');", code, setCode);
				int rowNum = stmt.executeUpdate(command);
				if (rowNum < 1)
					throw new Exception("데이터를 DB에 입력할 수 없습니다.");
				return;
			} else
				return;
		} catch (Exception e) {
			throw new ServletException(e);
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

	private void getGreen(String setCode, String code) throws ServletException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/wdbpool");
			if (conn == null)
				throw new Exception("DB연결 실패");
			stmt = conn.createStatement();

			ResultSet rs = stmt
					.executeQuery("select * from green where code='" + setCode + "' and getcode='" + code + "';");
			if (!rs.next()) {

				String command = String.format("insert into green(code,getCode) value('%s','%s');", setCode, code);
				int rowNum = stmt.executeUpdate(command);
				if (rowNum < 1)
					throw new Exception("데이터를 DB에 입력할 수 없습니다.");
				return;
			} else {
				return;
			}
		} catch (Exception e) {
			throw new ServletException(e);
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
