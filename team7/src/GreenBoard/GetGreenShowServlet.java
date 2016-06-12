package GreenBoard;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.util.ArrayList;

import web.MemberBean; //회원 정보 담는 자바빈

//받은 그린라이트 보여주는 서블릿
public class GetGreenShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		ArrayList<String> getCode = new ArrayList<String>();// getcode 정보 받은 배열
		ArrayList<MemberBean> info = new ArrayList<MemberBean>(); // 자바빈 객체를 배열로
																	// 선언

		// 세션에서 로그인 id가져옴
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("LOGIN_ID");
		// String id ="Test";

		getCode = readGreenDB(id); // id의 getcode 값을 가져온다.

		for (int i = 0; i < getCode.size(); i++) { // 각 getCode값을 넣어 개인정보를
													// MemberBean객체리스트에 저장
			MemberBean member = new MemberBean(); // 자바빈 객채 선언
			member.GreadDB(getCode.get(i));
			info.add(member);
		}

		if (getCode != null) {
			request.setAttribute("GETGREEN_LIST", info);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Green/GetGreenLight.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Green/NoGetGreen.jsp");
			dispatcher.forward(request, response);
		}

	}

	private ArrayList<String> readGreenDB(String id) // 로그인한 id의 getcode를 가져오는
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
			// 해당 id의 getcode를 가져온다.
			ResultSet rs = stmt.executeQuery("select * from green where code = '" + id + "';");

			for (int cnt = 0; rs.next(); cnt++) {
				if (rs.getString("getcode") != null) // null값은 거른다.
					getCode.add(rs.getString("getcode"));
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
