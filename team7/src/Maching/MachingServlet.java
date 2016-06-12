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

//��Ī��Ȳ�� ���� ����
public class MachingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		ArrayList<String> getCode = new ArrayList<String>();
		ArrayList<String> setCode = new ArrayList<String>();
		boolean Mach = false;
		String code = ""; // �� ������ �ڵ�, �� ���� id��

		MemberBean info = new MemberBean(); // �ڹٺ� ��ä ����

		// ���ǿ��� �α��� id(���ΰ���)�� �����´�
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("LOGIN_ID");
		// String id ="Test";//�׽�Ʈ��, �ڽ�id�� ����

		setCode = readGreenDB(id, true); // �ڽ�id�� setcode���� �迭�� ����
		getCode = readGreenDB(id, false); // �ڽ�id�� getcode���� �迭�� ����

		// ���� ���� �� ���Ͽ� �ѱ��.

		for (int i = 0; i < setCode.size(); i++) {
			for (int j = 0; j < getCode.size(); j++) {
				if (setCode.get(i).equals(getCode.get(j))) {
					Mach = true;
					code = setCode.get(i);
					info.GreadDB(code); // ��� �ڹٺ� �ش� �ڵ带 �־� ���� ���� ������
					break;
				}
			}
		}

		// ������ �׸� ����Ʈ ��Ͽ��� �ڽ��� ���� setcode�� getcode�� ������ ��Ī��������, �ƴϸ� �ȵ� ��������
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
				throw new Exception("DB���� ����");
			stmt = conn.createStatement();
			//
			ResultSet rs = stmt.executeQuery("select * from green where code = '" + id + "';");

			for (int cnt = 0; rs.next(); cnt++) {
				if (check) {
					if (rs.getString("setcode") != null) // null�� ���� ������
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
