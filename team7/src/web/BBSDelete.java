package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@SuppressWarnings("serial")
public class BBSDelete extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String c = request.getParameter("num");

		int num = Integer.parseInt(c);

		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/wdbpool");
			if (conn == null)
				throw new Exception("DB연결 실패");

			stmt = conn.createStatement();
			String command = String.format("delete from Board where number = %d;", num);
			// String command = String.format("insert into Board (title,
			// content, userName,wDate, hit) values
			// ('asd','asd','asd','2009/11/24', 0);"
			// );
			int rownum = stmt.executeUpdate(command);
			if (rownum < 1)
				throw new Exception("데이터 삭제 실패");

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