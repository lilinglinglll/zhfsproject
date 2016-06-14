package lab516.zhfs.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import lab516.zhfs.entity.ManageUser;

@WebService//(targetNamespace="http://service.zhfs.lab516/")//(serviceName="ManageUserServie")
public interface ManageUserService {

	@WebResult(name = "addManageUserResult")
	public void addManageUser(@WebParam(name = "manageUser")ManageUser manageUser);
	@WebResult(name = "removeManageUserByNameResult")
	public void removeManageUserByName(@WebParam(name = "name")String name);
	//如果返回值类型为list,WebResult指定的是集合中对象的名字
	@WebResult(name = "manageUser")
    public List<ManageUser> findManageUserByName(@WebParam(name = "name")String name);
	@WebResult(name = "manageUser")
    public List<ManageUser> listManageUser();
	@WebResult(name = "getNumOfManageUserResult")
    public Long getNumOfManageUser();
    @WebResult(name = "checkManageUserResult")
    public String checkMnameAndPswd(@WebParam(name = "name")String name,@WebParam(name = "password")String password);
    
    @WebResult(name = "findAuthorityByNameResult")
    public String findAuthorityByName(@WebParam(name = "name")String name);
}
