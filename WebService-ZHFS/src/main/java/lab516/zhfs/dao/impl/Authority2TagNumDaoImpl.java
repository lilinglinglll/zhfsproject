package lab516.zhfs.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import lab516.zhfs.dao.Authority2TagNumDao;
import lab516.zhfs.entity.Authority_TagNum;
import lab516.zhfs.entity.ManageUser;
import lab516.zhfs.entity.TagUser;

@Component("authority2tagnumDao")
public class Authority2TagNumDaoImpl implements Authority2TagNumDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

/*
 * 	************************************添加操作***********************************
 */
	
	@Override
	public void saveAuthority2TagNum(Authority_TagNum authority2tagNum) {
		Session session = sessionFactory.getCurrentSession();
	    session.save(authority2tagNum);

	}
/*
 * **************************************删除操作**********************************
 */

	@Override
	public void deleteAuthority2TagNumByName(String name) {
//		TagUser tu = new TagUser();		
//		Session session = sessionFactory.openSession();
//		String hql = "select tu.id from TagUser tu where tu.name = :name";
//		Query q = session.createQuery(hql);
//		q.setParameter("name", name);
//		for(Integer o:(List<Integer>)q.list()){
//			tu.setTagId(o);
//			hibernateTemplate.delete(tu);
//		}
	}
/*
 * **************************************修改操作************************************
 * @see lab516.zhfs.dao.Authority2TagNumDao#updateAuthority2TagNum(lab516.zhfs.entity.Authority2TagNum)
 */
	@Override
	public void updateAuthority2TagNum(Authority_TagNum authority2tagNum) {
		// TODO Auto-generated method stub

	}
/*
 * *******************************查询操作ByTagUserName************************************
 * 方法实现思路：
 *   1.select a2t.authority from Authority2TagNum a2t where a2t.name = :name
 *   2.from ManageUser mu where mu.authority = :authority
 *   
 *   先找到能查看传入TagUserName的所有的权限值(可能有多个),然后查询具有每个对应权限值得所有用户
 * 
 */

	
	@Override
	public List<ManageUser> searchAuthority2TagNumByTagUserName(String name) {	

		//	实现方法错误，暂时放着:需要3个阶段的查询	
//		Session session = sessionFactory.openSession();
//	    String hql1 = "select a2t.authority from Authority2TagNum a2t where a2t.name = :name";
//	    String hql2 = "from Authority2TagNum a2t where a2t.authority = :authority";
//	    Query q1 = session.createQuery(hql1);
//	    q1.setParameter("name", name);	  
//
//	    //以上获取到了多个权限值
//	    List<ManageUser> list = new ArrayList<ManageUser>();
//	    for(Object authority : q1.list()) {
//	    	Query q2 = session.createQuery(hql2);
//	    	q2.setParameter("authority", authority);
//	    	list.addAll(q2.list());//合并list
//		}
//		return list;
		return null;
	}
/*
 * *******************************查询操作ByManageUserName********************************
 * 
 * 方法实现思路：与前一个方法相同
 */

	@Override
	public List<TagUser> searchAuthority2TagNumByManageUserName(String name) {

		//		同上
//		Session session = sessionFactory.openSession();
//	    String hql1 = "select mu.authority from ManageUser mu where mu.name = :name";
//	    String hql2 = "from Authority2TagNum a2t where a2t.authority = :authority";
//	    Query q1 = session.createQuery(hql1);
//	    q1.setParameter("name", name);	  
//
//	    //以上获取到了多个权限值
//	    List<TagUser> list = new ArrayList<TagUser>();
//	    for(Object authority : q1.list()) {
//	    	Query q2 = session.createQuery(hql2);
//	    	q2.setParameter("authority", authority);
//	    	list.addAll(q2.list());//合并list
//		}
//		return list;
		return null;
	}
/*
 * ********************************查询操作ALL******************************************
 * 
 * @see lab516.zhfs.dao.Authority2TagNumDao#getAllAuthority2TagNum()
 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Authority_TagNum> getAllAuthority2TagNum() {
		Session session = sessionFactory.getCurrentSession();
	    String hql = "from Authority2TagNum a2t";
	    Query q = session.createQuery(hql);
	//    q.setParameter("name", name);	    
	    return  (List<Authority_TagNum>)q.list();
	}
}
