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

import web.MemberBean; //ȸ�� ���� ��� �ڹٺ�

//���� �׸�����Ʈ �����ִ� ����
public class GetGreenShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		ArrayList<String> getCode = new ArrayList<String>();// getcode ���� ���� �迭
		ArrayList<MemberBean> info = new ArrayList<MemberBean>(); // �ڹٺ� ��ü�� �迭��
																	// ����

		// ���ǿ��� �α��� id������
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("LOGIN_ID");
		// String id ="Test";

		getCode = readGreenDB(id); // id�� getcode ���� �����´�.

		for (int i = 0; i < getCode.size(); i++) { // �� getCode���� �־� ����������
													// MemberBean��ü����Ʈ�� ����
			MemberBean member = new MemberBean(); // �ڹٺ� ��ä ����
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

	private ArrayList<String> readGreenDB(String id) // �α����� id�� getcode�� ��������
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
			// �ش� id�� getcode�� �����´�.
			ResultSet rs = stmt.executeQuery("select * from green where code = '" + id + "';");

			for (int cnt = 0; rs.next(); cnt++) {
				if (rs.getString("getcode") != null) // null���� �Ÿ���.
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
