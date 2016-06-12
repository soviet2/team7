package GreenBoard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import web.MemberBean;

public class SendGreenShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		ArrayList<String> setCode = new ArrayList<String>();// setcode 정보 받은 배열
		ArrayList<MemberBean> info = new ArrayList<MemberBean>(); // 자바빈 객체를 배열로
																	// 선언

		// 세션에서 로그인 id가져옴
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("LOGIN_ID");

		setCode = readGreenDB(id); // id의 setcode 값을 가져온다.

		for (int i = 0; i < setCode.size(); i++) { // 각 setCode값을 넣어 개인정보를
													// MemberBean객체리스트에 저장
			MemberBean member = new MemberBean(); // 자바빈 객채 선언
			member.GreadDB(setCode.get(i));
			info.add(member);
		}

		if (setCode != null) {
			request.setAttribute("SETGREEN_LIST", info);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Green/SetGreenLight.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Green/NoSetGreen.jsp");
			dispatcher.forward(request, response);
		}

	}

	private ArrayList<String> readGreenDB(String id) // 로그인한 id의 setcode를 가져오는
														// 메소드
			throws ServletException {

		ArrayList<String> getCode = new ArrayList<String>();

		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/wdbpool");
			if (conn == null)
				throw new Exception("DB연결 실패");
			stmt = conn.createStatement();
			// 해당 id의 setcode를 가져온다.
			ResultSet rs = stmt.executeQuery("select * from green where code = '" + id + "';");

			for (int cnt = 0; rs.next(); cnt++) {
				if (rs.getString("setcode") != null) // null값은 거른다.
					getCode.add(rs.getString("setcode"));
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

		return getCode;
	}
}
