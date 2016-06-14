package lab516.zhfs.dao;

import java.util.List;

import lab516.zhfs.entity.ManageUser;

public interface ManageUserDao {
	public void saveManageUser(ManageUser manageUser);
	public void deleteManageUserByName(String name);
	public List<ManageUser> searchManageUserByName(String name);
    public List<ManageUser> getAllManageUser();
    public Long showNumOfManageUser();
    public String ifMatchedUnameAndPawd(String name,String password);
    public String getAuthorityByName(String name);
}
