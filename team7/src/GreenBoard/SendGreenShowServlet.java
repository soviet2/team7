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

		ArrayList<String> setCode = new ArrayList<String>();// setcode ���� ���� �迭
		ArrayList<MemberBean> info = new ArrayList<MemberBean>(); // �ڹٺ� ��ü�� �迭��
																	// ����

		// ���ǿ��� �α��� id������
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("LOGIN_ID");

		setCode = readGreenDB(id); // id�� setcode ���� �����´�.

		for (int i = 0; i < setCode.size(); i++) { // �� setCode���� �־� ����������
													// MemberBean��ü����Ʈ�� ����
			MemberBean member = new MemberBean(); // �ڹٺ� ��ä ����
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

	private ArrayList<String> readGreenDB(String id) // �α����� id�� setcode�� ��������
														// �޼ҵ�
			throws ServletException {

		ArrayList<String> getCode = new ArrayList<String>();

		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/wdbpool");
			if (conn == null)
				throw new Exception("DB���� ����");
			stmt = conn.createStatement();
			// �ش� id�� setcode�� �����´�.
			ResultSet rs = stmt.executeQuery("select * from green where code = '" + id + "';");

			for (int cnt = 0; rs.next(); cnt++) {
				if (rs.getString("setcode") != null) // null���� �Ÿ���.
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
