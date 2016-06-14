package lab516.zhfs.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "manageuser")
public class ManageUser {
	private int manageuserId;
	private String name;
	private String password;
	private String authority;
	
	@Id
	@GeneratedValue
	@Column(name = "manageuserid")
	public int getManageuserId() {
		return manageuserId;
	}
	public void setManageuserId(int manageuserId) {
		this.manageuserId = manageuserId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	@Override
	public String toString() {
		return "ManageUser [manageuserId=" + manageuserId + ", name=" + name + ", password=" + password + ", authority="
				+ authority + "]";
	}
	
}
