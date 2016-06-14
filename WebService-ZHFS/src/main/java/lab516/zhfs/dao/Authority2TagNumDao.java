package lab516.zhfs.dao;

import java.util.List;

import lab516.zhfs.entity.Authority_TagNum;
import lab516.zhfs.entity.ManageUser;
import lab516.zhfs.entity.TagUser;

public interface Authority2TagNumDao {
    public void saveAuthority2TagNum(Authority_TagNum authority2tagNum);
    public void deleteAuthority2TagNumByName(String name);
    public void updateAuthority2TagNum(Authority_TagNum authority2tagNum);
    public List<ManageUser> searchAuthority2TagNumByTagUserName(String name);
    public List<TagUser> searchAuthority2TagNumByManageUserName(String name);
    public List<Authority_TagNum> getAllAuthority2TagNum();
}
