package datahelper;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.hibernate.type.StandardBasicTypes;

import util.Criterion;
import util.ResultMessage;

/**
 * 用Hibernate实现对MySQL的直接操作<br>
 * Created on 2017/11/23
 * 
 * @author 巽
 *
 */
@SuppressWarnings({ "deprecation", "unchecked" })
public class HibernateDataHelper<T> implements DataHelper<T> {
	private SessionFactory sessionFactory;
	private Session session;
	private Class<T> type;

	public HibernateDataHelper(Class<T> type) {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		this.type = type;
	}

	private void initSession() {
		session = sessionFactory.openSession();
		session.beginTransaction();
	}

	private void commitAndClose() {
		Transaction transaction = session.getTransaction();
		if (transaction.getStatus().equals(TransactionStatus.ACTIVE)) { 
			transaction.commit();
		}
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
		if (ret.size() == 0) {
			return null;
		}
		else {
			return ret.get(0);
		}
	}

	@Override
	public ArrayList<T> fullyQuery(String field, Object value) {
		initSession();
		Criteria criteria = session.createCriteria(type);
		if (field != null && value != null) {
			criteria.add(Restrictions.eq(field, value));
		}
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		ArrayList<T> ret = (ArrayList<T>) criteria.list();
		commitAndClose();
		return ret;
	}

	@Override
	public ArrayList<T> fuzzyQuery(String field, String value) {
		initSession();
		Criteria criteria = session.createCriteria(type);
		if (value != null) {
			if (field.toLowerCase().equals("id")) {	// 查询字段是ID，即数字类型
				criteria.add(Restrictions.sqlRestriction("CAST({alias}.id AS CHAR) like ?", value,
						StandardBasicTypes.STRING));
			}
			else {	// （默认）查询字段是varchar类型
				criteria.add(Restrictions.like(field, "%" + value + "%"));
			}
		}
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		ArrayList<T> ret = (ArrayList<T>) criteria.list();
		commitAndClose();
		return ret;
	}

	@Override
	public ArrayList<T> rangeQuery(String field, Object min, Object max) {
		initSession();
		Criteria criteria = session.createCriteria(type);
		criteria.add(Restrictions.between(field, min, max));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		ArrayList<T> ret = (ArrayList<T>) criteria.list();
		commitAndClose();
		return ret;
	}

	@Override
	public ArrayList<T> multiQuery(ArrayList<Criterion> criteria) {
		initSession();
		Criteria crit = session.createCriteria(type);
		for (Criterion criterion : criteria) {
			crit.add(buildCriterion(criterion));
		}
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		ArrayList<T> ret = (ArrayList<T>) crit.list();
		commitAndClose();
		return ret;
	}

	private org.hibernate.criterion.Criterion buildCriterion(Criterion criterion) {
		switch (criterion.getQueryMode()) {
		case FULL:
			return Restrictions.eq(criterion.getField(), criterion.getValue());
		case FUZZY:
			if (criterion.getField().toLowerCase().equals("id")) {
				return Restrictions.sqlRestriction("CAST({alias}.id AS CHAR) like ?", criterion.getValue(),
						StandardBasicTypes.STRING);
			}
			else {
				return Restrictions.like(criterion.getField(), criterion.getValue());
			}
		case RANGE:
			return Restrictions.between(criterion.getField(), criterion.getValue(), criterion.getAnotherValue());
		case OR:
			return buildCriterion(criterion.getCriterion1(), criterion.getCriterion2());
		default:
			System.out.println("错误：出现了未识别的QueryMode！");
			return null;
		}
	}

	private org.hibernate.criterion.Criterion buildCriterion(Criterion criterion1, Criterion criterion2) {
		return Restrictions.or(buildCriterion(criterion1), buildCriterion(criterion2));
	}

	@Override
	public Long count() {
		initSession();
		Long ret = (Long) session.createCriteria(type).setProjection(Projections.rowCount()).uniqueResult();
		commitAndClose();
		return ret;
	}

}
