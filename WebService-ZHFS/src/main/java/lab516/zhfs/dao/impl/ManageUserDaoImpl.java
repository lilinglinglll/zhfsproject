package lab516.zhfs.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.mchange.v2.resourcepool.ResourcePool.Manager;
import com.sun.xml.messaging.saaj.packaging.mime.util.QEncoderStream;

import lab516.zhfs.dao.ManageUserDao;
import lab516.zhfs.entity.ManageUser;

@Component("manageuserDao")
public class ManageUserDaoImpl implements ManageUserDao {

	private SessionFactory sessionFactory;
	private String passwd;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void saveManageUser(ManageUser manageUser) {
		Session session = sessionFactory.getCurrentSession();
		session.save(manageUser);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteManageUserByName(String name) {
		ManageUser musr = new ManageUser();
		Session session = sessionFactory.getCurrentSession();
		String hql = "select mu.manageuserId from ManageUser mu where mu.name = :name";
		Query q = session.createQuery(hql);
		q.setParameter("name", name);
		for (Integer o : (List<Integer>) q.list()) {
			musr.setManageuserId(o);
			;
			session.delete(musr);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ManageUser> searchManageUserByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ManageUser mu where mu.name = :name";
		Query q = session.createQuery(hql);
		q.setParameter("name", name);
		return (List<ManageUser>) q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ManageUser> getAllManageUser() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ManageUser mu";
		Query q = session.createQuery(hql);
		return (List<ManageUser>) q.list();
	}

	@Override
	public Long showNumOfManageUser() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(*) from ManageUser mu";
		Query q = session.createQuery(hql);
		return (Long) q.uniqueResult();
	}

	//没有输出authority，以后在
	@Override
	public String ifMatchedUnameAndPawd(String name, String password) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ManageUser where name = :name";
		Query q = session.createQuery(hql);
		q.setParameter("name", name);
		List<ManageUser> list = q.list();
		if (list.isEmpty()) {
			return "404";
		}
		String pwd = list.get(0).getPassword();
		return pwd.equals(password) ? "200" : "100";
	}

	@Override
	public String getAuthorityByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select mu.authority from ManageUser mu where mu.name = :name";
		Query q = session.createQuery(hql);
		q.setParameter("name", name);
		String authority = null;
		List<String> list = q.list();
		for (String str : list) {
			authority = str;
		}
		return authority;
	}
}
