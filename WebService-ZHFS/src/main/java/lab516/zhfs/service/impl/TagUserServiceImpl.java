package lab516.zhfs.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lab516.zhfs.dao.TagUserDao;
import lab516.zhfs.entity.TagUser;
import lab516.zhfs.service.TagUserService;

@WebService(
		endpointInterface="lab516.zhfs.service.TagUserService",
		name = "TagUserService",
		portName = "TagUserServicePort",
		targetNamespace="http://service.zhfs.lab516/",
		serviceName="TagUserServiceWS"
	    
	)
@Component("taguserService")
@Transactional
public class TagUserServiceImpl implements TagUserService {

	private TagUserDao taguserDao;
	
	public TagUserDao getTaguserDao() {
		return taguserDao;
	}

	@Resource(name = "taguserDao")
	public void setTaguserDao(TagUserDao taguserDao) {
		this.taguserDao = taguserDao;
	}

	@Override
	public void addTagUser(TagUser tagUser) {
		taguserDao.saveTagUser(tagUser);
	}

//	@Override
//	public void removeTagUserByName(String name) {
//		taguserDao.deleteTagUserByName(name);
//	}

	@Override
	@Transactional(readOnly = true)
	public List<TagUser> findTagUserByName(String name) {
		return taguserDao.searchTagUserByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TagUser> listTagUser() {
		return taguserDao.getAllTagUser();
	}

	@Override
	@Transactional(readOnly = true)
	public Long getNumOfTagUser() {
		return taguserDao.showNumOfTagUser();
	}
	
	@Override
	public List<Object[]> findTagnumNameSexTagUserByAuthority(@WebParam(name = "authority")String authority) {
		return taguserDao.getTagnumNameSexTagUserByAuthority(authority);
	}
}
