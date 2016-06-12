package GreenLight;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

// �׸�����Ʈ ��� ����, �׷��� ���������ε� ��� ����
public class CanselGreenLight extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// �� ������ �Է� ��Ŀ� ���� �޶�����.
		String setCode = request.getParameter("setCode"); // ���� �ڵ�(���� ��������
															// ���´���)
		String check = request.getParameter("check"); // ���� �׸�����Ʈ�� ��� �Ǵ� ����
														// �׸�����Ʈ ���� ������

		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("LOGIN_ID");

		if (check.equals("����")) { // ���� �׸�����Ʈ ����
			canselSetGreen(code, setCode); // ���� �׸�����Ʈ setcode�κ� ����
			canselGetGreen(setCode, code); // ���� �׸�����Ʈ getcode�κ� ����
			response.sendRedirect("Home.jsp?BODY_PATH=getgreenshow");
		} else { // ���� �׸�����Ʈ ���
			canselSetGreen(setCode, code); // ���� �׸�����Ʈ setcode ����
			canselGetGreen(code, setCode); // ���� �׸�����Ʈ getcode ����
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
				throw new Exception("DB���� ����");
			stmt = conn.createStatement();
			// String command = String.format(); //���� ��ġ�� ����
			int rowNum = stmt
					.executeUpdate("delete from green where setcode='" + setCode + "'and code='" + code + "';");
			// setCode�� code�� �Ѵ� ������ �ִ� ���� ã�Ƽ� �����Ѵ�. ��, �ڽ��� ���� �׸�����Ʈ�� ���� ����.
			if (rowNum < 1)
				throw new Exception("�����͸� DB�� �Է��� �� �����ϴ�.");
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
				throw new Exception("DB���� ����");
			stmt = conn.createStatement();
			// String command = String.format(); //���� ��ġ�� ����
			int rowNum = stmt
					.executeUpdate("delete from green where code='" + setCode + "'and getcode='" + code + "';");
			// ���� �׸��ٱ��Ͽ� �ڽ���code�� getCode�� ����Ǿ� ������ �װ� ã�Ƽ� ������.
			if (rowNum < 1)
				throw new Exception("�����͸� DB�� �Է��� �� �����ϴ�.");
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
