package web;

import java.io.IOException;
import javax.servlet.ServletException;
import java.util.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

@SuppressWarnings("serial")
public class Upload extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("LOGIN_ID");

		String title = request.getParameter("title");

		String content = request.getParameter("content");

		int hit = 0;

		Calendar c = Calendar.getInstance();

		int y = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH) + 1;
		int d = c.get(Calendar.DAY_OF_MONTH);

		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/wdbpool");
			if (conn == null)
				throw new Exception("DB연결 실패");

			stmt = conn.createStatement();
			String command = String.format("insert into board " + "(title, content, userName,wDate, hit) "
					+ "values ('%s','%s','%s','%d/%d/%d', %d);", title, content, id, y, m, d, hit);
			// String command = String.format("insert into Board (title,
			// content, userName,wDate, hit) values
			// ('asd','asd','asd','2009/11/24', 0);"
			// );
			int rownum = stmt.executeUpdate(command);
			if (rownum < 1)
				throw new Exception("데이터 입력 실패");

		} catch (Exception e) {
			throw new ServletException(e);
		}

		finally {
			try {
				stmt.close();
			} catch (Exception ignored) {

			}
			try {
				conn.close();
			}

			catch (Exception ignored) {

			}

		}
		response.sendRedirect("Home.jsp");
	}

}
