package lab516.zhfs.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lab516.zhfs.dao.Authority2TagNumDao;
import lab516.zhfs.entity.Authority_TagNum;
import lab516.zhfs.entity.ManageUser;
import lab516.zhfs.entity.TagUser;
import lab516.zhfs.service.Authority2TagNumService;

@WebService
@Component("authority2tagnumService")
@Transactional
public class Authority2TagNumServiceImpl implements Authority2TagNumService {

	private Authority2TagNumDao authority2tagnumDao;
	
	public Authority2TagNumDao getAuthority2tagnumDao() {
		return authority2tagnumDao;
	}

	@Resource(name = "authority2tagnumDao")
	public void setAuthority2tagnumDao(Authority2TagNumDao authority2tagnumDao) {
		this.authority2tagnumDao = authority2tagnumDao;
	}

	@Override
	public void addAuthority2TagNum(Authority_TagNum authority2tagNum) {
		authority2tagnumDao.saveAuthority2TagNum(authority2tagNum);

	}

	@Override
	@Transactional(readOnly = true)
	public List<ManageUser> findAuthority2TagNumByTagUserName(String name) {
		return authority2tagnumDao.searchAuthority2TagNumByTagUserName(name);
	}

	@Override
	public void removeAuthority(String name, String authority) {
		authority2tagnumDao.deleteAuthority2TagNumByName(name);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<TagUser> findAuthority2TagNumByManageName(String name) {
		return authority2tagnumDao.searchAuthority2TagNumByManageUserName(name);
	}

}
