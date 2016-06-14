package lab516.zhfs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "authority_tagNum")
public class Authority_TagNum {
	private int authTagId;
	private String authority;
	private String tagNum;
	@Id
	@GeneratedValue
	@Column(name = "authTagId")
	public int getAuthTagId() {
		return authTagId;
	}
	public void setAuthTagId(int authTagId) {
		this.authTagId = authTagId;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	@Column(name = "TagNum")
	public String getTagNum() {
		return tagNum;
	}
	public void setTagNum(String tagNum) {
		this.tagNum = tagNum;
	}
}
