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
 * 	************************************��Ӳ���***********************************
 */
	
	@Override
	public void saveAuthority2TagNum(Authority_TagNum authority2tagNum) {
		Session session = sessionFactory.getCurrentSession();
	    session.save(authority2tagNum);

	}
/*
 * **************************************ɾ������**********************************
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
 * **************************************�޸Ĳ���************************************
 * @see lab516.zhfs.dao.Authority2TagNumDao#updateAuthority2TagNum(lab516.zhfs.entity.Authority2TagNum)
 */
	@Override
	public void updateAuthority2TagNum(Authority_TagNum authority2tagNum) {
		// TODO Auto-generated method stub

	}
/*
 * *******************************��ѯ����ByTagUserName************************************
 * ����ʵ��˼·��
 *   1.select a2t.authority from Authority2TagNum a2t where a2t.name = :name
 *   2.from ManageUser mu where mu.authority = :authority
 *   
 *   ���ҵ��ܲ鿴����TagUserName�����е�Ȩ��ֵ(�����ж��),Ȼ���ѯ����ÿ����ӦȨ��ֵ�������û�
 * 
 */

	
	@Override
	public List<ManageUser> searchAuthority2TagNumByTagUserName(String name) {	

		//	ʵ�ַ���������ʱ����:��Ҫ3���׶εĲ�ѯ	
//		Session session = sessionFactory.openSession();
//	    String hql1 = "select a2t.authority from Authority2TagNum a2t where a2t.name = :name";
//	    String hql2 = "from Authority2TagNum a2t where a2t.authority = :authority";
//	    Query q1 = session.createQuery(hql1);
//	    q1.setParameter("name", name);	  
//
//	    //���ϻ�ȡ���˶��Ȩ��ֵ
//	    List<ManageUser> list = new ArrayList<ManageUser>();
//	    for(Object authority : q1.list()) {
//	    	Query q2 = session.createQuery(hql2);
//	    	q2.setParameter("authority", authority);
//	    	list.addAll(q2.list());//�ϲ�list
//		}
//		return list;
		return null;
	}
/*
 * *******************************��ѯ����ByManageUserName********************************
 * 
 * ����ʵ��˼·����ǰһ��������ͬ
 */

	@Override
	public List<TagUser> searchAuthority2TagNumByManageUserName(String name) {

		//		ͬ��
//		Session session = sessionFactory.openSession();
//	    String hql1 = "select mu.authority from ManageUser mu where mu.name = :name";
//	    String hql2 = "from Authority2TagNum a2t where a2t.authority = :authority";
//	    Query q1 = session.createQuery(hql1);
//	    q1.setParameter("name", name);	  
//
//	    //���ϻ�ȡ���˶��Ȩ��ֵ
//	    List<TagUser> list = new ArrayList<TagUser>();
//	    for(Object authority : q1.list()) {
//	    	Query q2 = session.createQuery(hql2);
//	    	q2.setParameter("authority", authority);
//	    	list.addAll(q2.list());//�ϲ�list
//		}
//		return list;
		return null;
	}
/*
 * ********************************��ѯ����ALL******************************************
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
