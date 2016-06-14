package lab516.zhfs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.digester.annotations.FromAnnotationRuleProviderFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.sun.jmx.snmp.UserAcl;

import lab516.zhfs.dao.TagDataDao;
import lab516.zhfs.entity.LastTime;
import lab516.zhfs.entity.TagTimeData;

@Component("tagdataDao")
public class TagDataDaoImpl implements TagDataDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
/***********************************常用Query语句*********************************************************
 *    from Category c where c.name > 'c5'
 *    from Category c order by c.name desc
 *    select distinct c from Category c order by c.name desc
 *    from Category c where c.id > :min and c.id < :max
 *    from Category c order by c.name desc
 *    select c.id,  c.name from Category c order by c.name desc
 *    from Topic t where t.category.id = 1
 *    select new com.bjsxt.hibernate.MsgInfo(m.id, m.cont, m.topic.title, m.topic.category.name) from Msg
 *    select t.title, c.name from Topic t join t.category c
 *    from Msg m where m = :MsgToSearch
 *    select count(*) from Msg m
 *    select max(m.id), min(m.id), avg(m.id), sum(m.id) from Msg m
 *    from Msg m where m.id between 3 and 5
 *    from Msg m where m.id in (3,4, 5)
 *    from Msg m where m.cont is not null
 *    select current_date, current_time, current_timestamp, t.id from Topic t
 *    from Topic t where t.id < (select avg(t.id) from Topic t)
 *    String hql = "from TagData td where td.dataId in (25,26,27,28)";
 *    String hql = "from TagData td where td.dataId =25";
 *  ***************************************************************************************************  
 *    Query q = session.createQuery("from Topic t where t.createDate < :date");
 *		q.setParameter("date", new Date());
 *    q.setMaxResult(4);
 *    q.setFirstResult(2);
 *    Query q = session.createQuery("select c.id,  c.name from Category c order by c.name desc");
 *		List<Object[]> categories = (List<Object[]>)q.list();
 *		for(Object[] o : categories) {
 *			System.out.println(o[0] + "-" + o[1]);
 *		}
 ******************************************************************************************************
 */
	
/*******************************************获取每个标签的最新信息**************************************************
 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TagTimeData> searchLastInsertTimeData(String authority) {
		Session session = sessionFactory.openSession();
		
		String sql="select authority_tagNum.tagNum,start,end,startDate,endDate,cpid,rssi,wakeupNum,Name "
				+ "from time,authority_tagNum,TagInformation "
				+ "where authority_tagNum.authority =?  and authority_tagNum.TagNum=time.tagNum "
				+ "and authority_tagNum.TagNum=TagInformation.TagNum "
				+ "and id in(select max(id) from time group by tagNum)";
	    Query q = session.createSQLQuery(sql);
	    q.setString(1, "authority");
	    	
//	    Query query = session.createQuery(hql);   "from User"
//	    Query query = session.createSQLQuery("select * from Tree t where pid in (select id from Tree) ").addEntity(Tree.class); //返回对象
		List<Object[]> list = q.list();
		
		
		List<LastTime> result = new ArrayList<LastTime>();
		
//		for(LastTime t : list){
//			if(t.getCpid().equals("2")){
//				switch (t.getWakeupNum()){
//				case "01":
//					t.setRoomNum("001");
//					break;
//				case "02":
//					t.setRoomNum("002");
//					break;
//				case "03":
//					t.setRoomNum("003");
//					break;
//				case "04":
//					t.setRoomNum("004");
//					break;
//				}
//			}else if(t.getCpid().equals("1")){
//				switch (t.getWakeupNum()){
//				case "01":
//					t.setRoomNum("414");
//					break;
//				}
//			}
//		}
//	    
	    
	    return  (List<TagTimeData>)q.list();	
	}
	
/*********************************************获取某个人某个日期的所有数据********************************************
 * 
 * 由于对Date类型不了解，所以将时间定义为String类型
 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TagTimeData> searchTagDataByTagUserNameAndDate(String name, String date) {
		
		Session session = sessionFactory.openSession();
	    String hql1 = "from TagData td where td.tagNum = :tagNum and td.startDate = :date";
	    String hql2 = "select tu.tagNum from TagUser tu where tu.name = :name";
	    Query q2 = session.createQuery(hql2);
	    q2.setParameter("name", name);
	    Query q1 = session.createQuery(hql1);
	    q1.setParameter("tagNum", q2.uniqueResult().toString());
	    q1.setParameter("date", date);	    
	    return  (List<TagTimeData>)q1.list();
	}
}
