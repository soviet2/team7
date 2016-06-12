package web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;

public class MemberBean {
	private String id;  //public
	private String password; //private
	private String name; //private
	private int stdNum; //private
	private String gender; //public
	private int age; //public
	private String phone; //private
	private String email; //private
	
	private String state; //public

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStdNum() {
		return stdNum;
	}

	public void setStdNum(int stdNum) {
		this.stdNum = stdNum;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setMail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public void GreadDB(String code) throws ServletException{ //입력 받은 code는 상대방 id
		Connection conn=null;
		Statement stmt = null;
		
		try{
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:/wdbpool");
			
			if(conn == null)
				throw new Exception("데이터베이스에 연결할 수 없습니다.");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from memberinfo where id='" + code + "';");
			if(rs.next()){
				id = code;//rs.getString("id");
				//password=rs.getString("password");
				name=rs.getString("name");
				stdNum = rs.getInt("stdNum");
				gender = rs.getString("gender");
				age = rs.getInt("age");
				phone = rs.getString("phone");
				email = rs.getString("email");
				state = rs.getString("state");
			}
				
		}
		catch(Exception e){
			throw new ServletException(e);
		}
		finally{
			try{
				stmt.close();
			}
			catch(Exception ignored){
				
			}
			try{
				conn.close();
			}
			catch(Exception ignored){
				
			}
		}
				
		
	}
	
	
	
	
}
