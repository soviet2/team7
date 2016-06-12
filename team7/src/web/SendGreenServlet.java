package web;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.sql.*;

//�׸�����Ʈ ������ ����
@SuppressWarnings("serial")
public class SendGreenServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// �� ������ �Է� ��Ŀ� ���� �޶�����.
		String setCode = request.getParameter("setCode"); // ���� �ڵ�(���� ��������
															// ���´���)
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("LOGIN_ID");

		sendGreen(code, setCode); // �׸��ٱ��� ���̺��� �ڽ��� �ڵ�� ���� ���� ������ �ڵ带 �Է��ϴ� �Լ�.
		getGreen(setCode, code); // �׸��ٱ��Ͽ� �ڽ��� ���� ������ ����.

		response.sendRedirect("completeGreen.jsp");
	}

	private void sendGreen(String code, String setCode) throws ServletException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/wdbpool");
			if (conn == null)
				throw new Exception("DB���� ����");
			stmt = conn.createStatement();

			ResultSet rs = stmt
					.executeQuery("select * from green where code='" + code + "' and setcode='" + setCode + "';");
			if (!rs.next()) {

				String command = String.format("insert into green(code,setCode) value('%s','%s');", code, setCode);
				int rowNum = stmt.executeUpdate(command);
				if (rowNum < 1)
					throw new Exception("�����͸� DB�� �Է��� �� �����ϴ�.");
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
				throw new Exception("DB���� ����");
			stmt = conn.createStatement();

			ResultSet rs = stmt
					.executeQuery("select * from green where code='" + setCode + "' and setcode='" + code + "';");
			if (!rs.next()) {

				String command = String.format("insert into green(code,getCode) value('%s','%s');", setCode, code);
				int rowNum = stmt.executeUpdate(command);
				if (rowNum < 1)
					throw new Exception("�����͸� DB�� �Է��� �� �����ϴ�.");
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
