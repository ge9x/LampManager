package datahelper;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import util.Criterion;
import util.ResultMessage;

/**
 * 用Hibernate实现对MySQL的直接操作<br>
 * Created on 2017/11/23
 * @author 巽
 *
 */
@SuppressWarnings({ "deprecation", "unchecked" })
public class HibernateDataHelper<T> implements DataHelper<T>{
	private SessionFactory sessionFactory;
	private Session session;
	private Class<T> type;
	
	public HibernateDataHelper(){
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	private void initSession(){
		session = sessionFactory.openSession();
		session.beginTransaction();
	}
	
	private void commitAndClose(){
		session.getTransaction().commit();
		session.close();
	}
	
	@Override
	public ResultMessage save(T po) {
		initSession();
		session.save(po);
		commitAndClose();
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage delete(T po) {
		initSession();
		session.delete(po);
		commitAndClose();
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(T po) {
		initSession();
		session.update(po);
		commitAndClose();
		return ResultMessage.SUCCESS;
	}

	@Override
	public T exactlyQuery(String field, String value) {
		return fullyQuery(field, value).get(0);
	}

	@Override
	public List<T> fullyQuery(String field, String value) {
		initSession();
		Criteria criteria = session.createCriteria(type);
		criteria.add(Restrictions.eq(field, value));
		List<T> ret = criteria.list();
		commitAndClose();
		return ret;
	}

	@Override
	public List<T> fuzzyQuery(String field, String value) {
		initSession();
		Criteria criteria = session.createCriteria(type);
		criteria.add(Restrictions.like(field, value));
		List<T> ret = criteria.list();
		commitAndClose();
		return ret;
	}

	@Override
	public List<T> multiQuery(List<Criterion> criteria) {
		initSession();
		Criteria crit = session.createCriteria(type);
		for(Criterion criterion : criteria){
			switch(criterion.getQueryMode()){
			case FULL:
				crit.add(Restrictions.eq(criterion.getField(), criterion.getValue()));
				break;
			case FUZZY:
				crit.add(Restrictions.like(criterion.getField(), criterion.getValue()));
				break;
			case RANGE:
				crit.add(Restrictions.between(criterion.getField(), criterion.getValue(), criterion.getAnotherValue()));
				break;
			}
		}
		List<T> ret = crit.list();
		commitAndClose();
		return ret;
	}

}
