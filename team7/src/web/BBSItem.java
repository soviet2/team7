//DB�� ���� �Խñ��� �д� �ڹٺ� Ŭ����

package web;

import java.sql.*;
import javax.servlet.*;
public class BBSItem {
	private int number;
	private String title;
	private String content;
	private String userName;
	private Date wDate;
	private int hit;
	
	public BBSItem() {
	}
	public void setNumber(int number){
		this.number = number;
	}
	public String getTitle(){
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getUserName() {
		return userName;
	}
	public Date getwDate() {
		return wDate;
	}
	public int getHit() {
		return hit;
	}
	
	public void readDB() throws ServletException {
		//�����ͷκ��� �Խñ��� �д� �޼ҵ�
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			conn = DriverManager.getConnection(
					"jdbc:apache:commons:dbcp:/wdbpool");
			if(conn == null)
				throw new Exception("DB���� ����");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select * from board where number = '" + number + "';");
			if (rs.next()) {
				title = rs.getString("title");
				content = rs.getString("content");
				userName = rs.getString("userName");
				wDate = rs.getDate("wDate");
				hit = rs.getInt("hit");
				hit++;
				
				stmt.executeUpdate(
						"update board set hit:='"+hit+"'where number= '" + number + "';");
			}
			
			
		}
		catch (Exception e){
			throw new ServletException(e);
		}
		finally {
			try {
				stmt.close();
			}
			catch(Exception ignored) {
				
			}
			try {
				conn.close();
			}
			catch (Exception ignored){
				
			}
		}
	}
}
