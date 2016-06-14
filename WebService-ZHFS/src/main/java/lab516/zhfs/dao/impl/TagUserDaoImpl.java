package lab516.zhfs.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import lab516.zhfs.dao.TagUserDao;
import lab516.zhfs.entity.TagUser;

/********************************************************************************
 * 
 * �˴�������hibernateTemplate,�ڷ���WebServiceʱ�ᱨ��,����ԭ�򻹲����
 * 
 */
@Component("taguserDao")
public class TagUserDaoImpl implements TagUserDao {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/***********************************
	 * ���TagUser**************************************
	 */
	@Override
	public void saveTagUser(TagUser tagUser) {
		Session session = sessionFactory.getCurrentSession();
		session.save(tagUser);
	}

	/***********************************
	 * ɾ��TagUser*************************************
	 * 
	 * 
	 */
	// @SuppressWarnings("unchecked")
	// @Override
	// public void deleteTagUserByName(String name) {
	// TagUser tusr = new TagUser();
	// Session session = sessionFactory.getCurrentSession();
	// String hql = "select tu.taguserId from TagUser tu where tu.name = :name";
	// Query q = session.createQuery(hql);
	// q.setParameter("name", name);
	// for(Integer o:(List<Integer>)q.list()){
	//// Session sesson = sessionFactory.getCurrentSession();//������ͬ��session
	// tusr.setTaguserId(o);
	// session.delete(tusr);
	// }
	// }

	/***********************************
	 * ��ѯ����**************************************
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TagUser> searchTagUserByName(String name) {
		// Session session = sessionFactory.getCurrentSession();
		Session session = sessionFactory.openSession();
		String hql = "from TagUser tu where tu.name = :name";
		Query q = session.createQuery(hql);
		q.setParameter("name", name);
		return (List<TagUser>) q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TagUser> getAllTagUser() {
		Session session = sessionFactory.openSession();
		String hql = "from TagUser tu";
		Query q = session.createQuery(hql);
		// q.setParameter("name", name);
		return (List<TagUser>) q.list();
	}

	@Override
	public Long showNumOfTagUser() {
		Session session = sessionFactory.openSession();
		String hql = "select count(*) from TagUser tu";
		Query q = session.createQuery(hql);
		return (Long) q.uniqueResult();
	}

	/*******************************
	 * ͨ��authority��ѯtag��Ϣ*********************************
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getTagnumNameSexTagUserByAuthority(String authority) {
		Session session = sessionFactory.getCurrentSession();
		
		String spl = "select TagInformation.TagNum,Name,Sex "
				+ "from TagInformation,authority_tagNum "
				+ "where authority_tagNum.TagNum = TagInformation.TagNum and authority= :authority";
		Query q = session.createSQLQuery(spl);
		q.setParameter("authority", authority);
		return q.list();
	}
}
