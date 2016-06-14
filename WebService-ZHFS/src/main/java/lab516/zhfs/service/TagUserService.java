package lab516.zhfs.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import lab516.zhfs.entity.TagUser;

@WebService
public interface TagUserService {
	public void addTagUser(TagUser tagUser);
//	public void removeTagUserByName(String name);
    public List<TagUser> findTagUserByName(String name);
    public List<TagUser> listTagUser();
    public Long getNumOfTagUser();
    
    @WebResult(name = "findTagUserByAuthorityResult")
	public List<Object[]> findTagnumNameSexTagUserByAuthority(@WebParam(name = "authority")String authority);
}
