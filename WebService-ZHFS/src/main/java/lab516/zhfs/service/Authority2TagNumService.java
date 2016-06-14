package lab516.zhfs.service;

import java.util.List;

import javax.jws.WebService;

import lab516.zhfs.entity.Authority_TagNum;
import lab516.zhfs.entity.ManageUser;
import lab516.zhfs.entity.TagUser;

@WebService
public interface Authority2TagNumService {
	public void addAuthority2TagNum(Authority_TagNum authority2tagNum);
	public void removeAuthority(String name,String authority);
	public List<TagUser> findAuthority2TagNumByManageName(String name);
	public List<ManageUser> findAuthority2TagNumByTagUserName(String name);
}
