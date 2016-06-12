package Maching;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

import web.MemberBean;

//매칭현황을 보는 서블릿
public class MachingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		ArrayList<String> getCode = new ArrayList<String>();
		ArrayList<String> setCode = new ArrayList<String>();
		boolean Mach = false;
		String code = ""; // 값 저장할 코드, 즉 상대방 id값

		MemberBean info = new MemberBean(); // 자바빈 객채 선언

		// 세션에서 로그인 id(본인계정)을 가져온다
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("LOGIN_ID");
		// String id ="Test";//테스트용, 자신id를 뜻함

		setCode = readGreenDB(id, true); // 자신id의 setcode값을 배열로 저장
		getCode = readGreenDB(id, false); // 자신id의 getcode값을 배열로 저장

		// 서로 같은 지 비교하여 넘긴다.

		for (int i = 0; i < setCode.size(); i++) {
			for (int j = 0; j < getCode.size(); j++) {
				if (setCode.get(i).equals(getCode.get(j))) {
					Mach = true;
					code = setCode.get(i);
					info.GreadDB(code); // 멤버 자바빈에 해당 코드를 넣어 개인 정보 가져옴
					break;
				}
			}
		}

		// 가져온 그린 라이트 목록에서 자신이 보낸 setcode와 getcode가 같으면 매칭페이지로, 아니면 안된 페이지로
		if (Mach) {
			request.setAttribute("ID", code);
			request.setAttribute("NAME", info.getName());
			request.setAttribute("AGE", info.getAge());
			request.setAttribute("STRNUM", info.getStdNum());
			request.setAttribute("GENDER", info.getGender());
			request.setAttribute("PHONE", info.getPhone());
			request.setAttribute("EMAIL", info.getEmail());

			// response.sendRedirect("Home.jsp?BODY_PATH=Green/GreenLight.jsp");

			RequestDispatcher dispatcher = request.getRequestDispatcher("Green/Maching.jsp");
			dispatcher.forward(request, response);

		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Green/NoMaching.jsp");
			dispatcher.forward(request, response);
		}

	}

	private ArrayList<String> readGreenDB(String id, boolean check) throws ServletException {

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
			//
			ResultSet rs = stmt.executeQuery("select * from green where code = '" + id + "';");

			for (int cnt = 0; rs.next(); cnt++) {
				if (check) {
					if (rs.getString("setcode") != null) // null값 저장 방지용
						getCode.add(rs.getString("setcode"));
				} else {
					if (rs.getString("getcode") != null)
						getCode.add(rs.getString("getcode"));
				}

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
