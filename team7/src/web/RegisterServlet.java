package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 2758850072279729981L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("ID");
		String password = request.getParameter("PASSWORD");
		String name = request.getParameter("NAME");
		String stdNum_str = request.getParameter("stdNUM");
		int stdNum = 0;
		if (stdNum_str != null && !stdNum_str.isEmpty())
			stdNum = Integer.parseInt(stdNum_str.trim());
		String gender = request.getParameter("GENDER");
		String age_str = request.getParameter("AGE");
		int age = 0;
		if (age_str != null && !age_str.isEmpty())
			age = Integer.parseInt(age_str.trim());
		String phone = request.getParameter("PHONE");
		String email = request.getParameter("EMAIL");
		String state = request.getParameter("STATE");

		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/webdb?useUnicode=true&characterEncoding=UTF-8", "root", "student");
			if (conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			stmt = conn.createStatement();
			String command = String.format(
					"insert into memberinfo " + "(id, password, name, stdNum, gender, age, phone, email, state) "
							+ "values ('%s', '%s', '%s', %s, '%s', %s, '%s', '%s', '%s');",
					id, password, name, stdNum, gender, age, phone, email, state);
			int rowNum = stmt.executeUpdate(command);
			if (rowNum < 1)
				throw new Exception("데이터를 DB에 입력할 수 없습니다.");
		} catch (Exception e) {
			e.printStackTrace();
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
		response.sendRedirect("RegisterResult.jsp");
	}

}
