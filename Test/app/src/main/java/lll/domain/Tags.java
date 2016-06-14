package lll.domain;

public class Tags {
	private String TagNum;
	private String Name;
	private String Age;
	private String Sex;
	private String MaritalStatus;
	public Tags(){}
	public Tags(String tagNum, String name, String age, String sex, String maritalStatus) {
		super();
		TagNum = tagNum;
		Name = name;
		Age = age;
		Sex = sex;
		MaritalStatus = maritalStatus;
	}
	public String getTagNum() {
		return TagNum;
	}
	public void setTagNum(String tagNum) {
		TagNum = tagNum;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getMaritalStatus() {
		return MaritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		MaritalStatus = maritalStatus;
	}
	@Override
	public String toString() {
		return "Tags [TagNum=" + TagNum + ", Name=" + Name + ", Age=" + Age + ", Sex=" + Sex + ", MaritalStatus="
				+ MaritalStatus + "]";
	}
}
