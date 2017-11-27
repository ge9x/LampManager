package datahelper;

import java.util.ArrayList;

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
	
	public HibernateDataHelper(Class<T> type){
		sessionFactory = new Configuration().configure().buildSessionFactory();
		this.type = type;
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
	public T exactlyQuery(String field, Object value) {
		ArrayList<T> ret = fullyQuery(field, value);
		if(ret.size() == 0){
			return null;
		}
		else{
			return ret.get(0);
		}
	}

	@Override
	public ArrayList<T> fullyQuery(String field, Object value) {
		initSession();
		Criteria criteria = session.createCriteria(type);
		criteria.add(Restrictions.eq(field, value));
		ArrayList<T> ret = (ArrayList<T>) criteria.list();
		commitAndClose();
		return ret;
	}

	@Override
	public ArrayList<T> fuzzyQuery(String field, String value) {
		initSession();
		Criteria criteria = session.createCriteria(type);
		criteria.add(Restrictions.like(field, value));
		ArrayList<T> ret = (ArrayList<T>) criteria.list();
		commitAndClose();
		return ret;
	}

	@Override
	public ArrayList<T> multiQuery(ArrayList<Criterion> criteria) {
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
		ArrayList<T> ret = (ArrayList<T>) crit.list();
		commitAndClose();
		return ret;
	}

}
