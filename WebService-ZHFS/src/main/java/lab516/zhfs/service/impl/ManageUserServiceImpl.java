package lab516.zhfs.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lab516.zhfs.dao.ManageUserDao;
import lab516.zhfs.entity.ManageUser;
import lab516.zhfs.service.ManageUserService;

@WebService(
		endpointInterface="lab516.zhfs.service.ManageUserService",
		name = "ManageUserService",
		portName = "ManageUserServicePort",
		targetNamespace="http://service.zhfs.lab516/",
		serviceName="ManageUserServiceWS"
	    
	)
@Component("manageuserService")
@Transactional
public class ManageUserServiceImpl implements ManageUserService {

	private ManageUserDao manageuserDao;
	
	
	public ManageUserDao getManageuserDao() {
		return manageuserDao;
	}

	@Resource(name = "manageuserDao")
	public void setManageuserDao(ManageUserDao manageuserDao) {
		this.manageuserDao = manageuserDao;
	}

	@Override
	public void addManageUser(ManageUser manageUser) {
		manageuserDao.saveManageUser(manageUser);
	}

	@Override
	public void removeManageUserByName(String name) {
	    manageuserDao.deleteManageUserByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ManageUser> findManageUserByName(String name) {
		return manageuserDao.searchManageUserByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ManageUser> listManageUser() {
		return manageuserDao.getAllManageUser();
	}

	@Override
	@Transactional(readOnly = true)
	public Long getNumOfManageUser() {
		return manageuserDao.showNumOfManageUser();
	}

	@Override
	@WebMethod
	public String checkMnameAndPswd(@WebParam(name = "name")String name,@WebParam(name = "password") String password) {
		return manageuserDao.ifMatchedUnameAndPawd(name, password);
	}

	@Override
	@WebMethod
	public String findAuthorityByName(@WebParam(name = "name")String name) {
		return manageuserDao.getAuthorityByName(name);
	}

}
