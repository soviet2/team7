package web;

public class MemberBean {
	private String id;
	private String password;
	private String name;
	private int stdNum;
	private String gender;
	private int age;
	private String phone;
	private String email;
	/* private String[] major; */
	/* private String[] hobby; */
	private String state;

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
	/*
	 * public String[] getMajor() { return major; }
	 * 
	 * public void setMajor(String[] major) { this.major = major; }
	 * 
	 * public String[] getHobby() { return hobby; }
	 * 
	 * public void setHobby(String[] hobby) { this.hobby = hobby; }
	 */
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
