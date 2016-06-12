package web;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;

import java.sql.*;

@SuppressWarnings("serial")
public class BBSListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String strUpperNumber = request.getParameter("LAST_NUMBER");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("LOGIN_ID");
		request.setAttribute("ID", id);

		int upperNumber;

		if (strUpperNumber == null)
			upperNumber = Integer.MAX_VALUE;
		else
			upperNumber = Integer.parseInt(strUpperNumber.trim());

		BBSList list = readDB(upperNumber);
		BBSList list2 = readDB2(upperNumber);

		request.setAttribute("BBS_LIST", list);
		request.setAttribute("BBS_LIST2", list2);

		RequestDispatcher dispatcher = request.getRequestDispatcher("BBSListView.jsp");

		dispatcher.forward(request, response);
	}

	private BBSList readDB(int upperNumber) throws ServletException {
		BBSList list = new BBSList();
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
					.executeQuery("select * from Board where number < " + upperNumber + " order by number desc;");

			for (int cnt = 0; cnt < 5; cnt++) {
				if (!rs.next())
					break;

				list.setNumber(cnt, rs.getInt("number"));
				list.setTitle(cnt, rs.getString("title"));
				list.setUserName(cnt, rs.getString("userName"));
				list.setwDate(cnt, rs.getDate("wDate"));
				list.sethit(cnt, rs.getInt("hit"));
			}

			if (!rs.next())
				list.setLastPage(true);

			if (!rs.first())
				list.setFirstPage(false);
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

		return list;
	}

	private BBSList readDB2(int upperNumber) throws ServletException {

		BBSList list2 = new BBSList();
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
					.executeQuery("select * from board where number < " + upperNumber + " order by number desc;");

			for (int cnt = 0; cnt < 10; cnt++) {
				if (!rs.next())
					break;
				if (cnt > 4) {
					list2.setNumber(cnt - 5, rs.getInt("number"));
					list2.setTitle(cnt - 5, rs.getString("title"));
					list2.setUserName(cnt - 5, rs.getString("userName"));
					list2.setwDate(cnt - 5, rs.getDate("wDate"));
					list2.sethit(cnt - 5, rs.getInt("hit"));
				}
			}
			if (!rs.next())
				list2.setLastPage(true);

			if (!rs.first())
				list2.setFirstPage(false);
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

		return list2;
	}

}
