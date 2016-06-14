package lab516.zhfs.dao;

import java.util.List;

import lab516.zhfs.entity.TagUser;

public interface TagUserDao {
	public void saveTagUser(TagUser tagUser);
//	public void deleteTagUserByName(String name);
	public List<TagUser> searchTagUserByName(String name);
    public List<TagUser> getAllTagUser();
    public Long showNumOfTagUser();
	public List<Object[]> getTagnumNameSexTagUserByAuthority(String authority);
}
