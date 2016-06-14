package lll.domain;

public class Users {
	private int userid;
	private String password;
	private int authority;

	public Users(){}

	public Users(int userid, String password, int authority) {
		super();
		this.userid = userid;
		this.password = password;
		this.authority = authority;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}
	@Override
	public String toString() {
		return "Users [userid=" + userid + ", password=" + password + ", authority=" + authority + "]";
	}
}
