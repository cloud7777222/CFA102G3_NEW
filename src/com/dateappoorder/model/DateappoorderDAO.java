package com.dateappoorder.model;

/*
 Hibernate is providing a factory.getCurrentSession() method for retrieving the current session. A
 new session is opened for the first time of calling this method, and closed when the transaction is
 finished, no matter commit or rollback. But what does it mean by the ��current session��? We need to
 tell Hibernate that it should be the session bound with the current thread.

 <hibernate-configuration>
 <session-factory>
 ...
 <property name="current_session_context_class">thread</property>
 ...
 </session-factory>
 </hibernate-configuration>

 */


import org.hibernate.*;
import org.hibernate.query.Query; //Hibernate 5.2 �}�l ���N�� org.hibernate.Query ����
import hibernate.util.HibernateUtil;
import java.util.*;


public class DateappoorderDAO implements DateappoorderDAO_interface {
	/* �`�N:
       A. �ثe�u�O��B����Hibernate���򥻥\��
       B. �ثe�|�ݤ��XHibernate���¤O�Ҧb
    */
	private static final String GET_ALL_STMT = "from DateappoorderVO order by dateOrderNo";

	@Override
	public void insert(DateappoorderVO dateappoorderVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(dateappoorderVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(DateappoorderVO dateappoorderVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(dateappoorderVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

//	@Override
//	public void delete(Integer dateOrderNo) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			DateappoorderVO dateappoordervo = (DateappoorderVO) session.get(DateappoorderVO.class, dateOrderNo);
//			session.delete(dateappoordervo);
//			session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}
//	}

	@Override
	public DateappoorderVO findByPrimaryKey(Integer dateOrderNo) {
		DateappoorderVO dateappoorderVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			dateappoorderVO = (DateappoorderVO) session.get(DateappoorderVO.class, dateOrderNo);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return dateappoorderVO;
	}

	@Override
	public List<DateappoorderVO> getAll() {
		List<DateappoorderVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<DateappoorderVO> query = session.createQuery(GET_ALL_STMT, DateappoorderVO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	public static void main(String[] args) {

		DateappoorderDAO dao = new DateappoorderDAO();

		// insert
		DateappoorderVO dateappoorderVO1 = new DateappoorderVO();
		dateappoorderVO1.setMemberNoA(new Integer(2));
		dateappoorderVO1.setMemberNoB(new Integer(3));
		dateappoorderVO1.setDateOrderDate(java.sql.Timestamp.valueOf("2011-01-01 11:11:22"));
		dateappoorderVO1.setDateAppoDate(java.sql.Timestamp.valueOf("2019-01-01 11:11:22"));
		dateappoorderVO1.setDateOrderState(1);
		dateappoorderVO1.setDateStarRateA(1);
		dateappoorderVO1.setDateStarRateB(1);
		dateappoorderVO1.setDateCE(1);
		dao.insert(dateappoorderVO1);

		// update
		DateappoorderVO dateappoorderVO2 = new DateappoorderVO();
		dateappoorderVO2.setDateOrderNo(2);
		dateappoorderVO2.setMemberNoA(new Integer(2));
		dateappoorderVO2.setMemberNoB(new Integer(3));
		dateappoorderVO2.setDateOrderDate(java.sql.Timestamp.valueOf("2020-01-01 11:11:22"));
		dateappoorderVO2.setDateAppoDate(java.sql.Timestamp.valueOf("2022-01-01 19:11:44"));
		dateappoorderVO2.setDateOrderState(1);
		dateappoorderVO2.setDateStarRateA(1);
		dateappoorderVO2.setDateStarRateB(1);
		dateappoorderVO2.setDateCE(1);
		dao.update(dateappoorderVO2);

		// delete
//		dao.delete(7014);

		// search
		DateappoorderVO dateappoorderVO3 = dao.findByPrimaryKey(4);
		System.out.print(dateappoorderVO3.getDateOrderNo() + ",");
		System.out.print(dateappoorderVO3.getMemberNoA() + ",");
		System.out.print(dateappoorderVO3.getMemberNoB() + ",");
		System.out.print(dateappoorderVO3.getDateOrderDate() + ",");
		System.out.print(dateappoorderVO3.getDateAppoDate() + ",");
		System.out.print(dateappoorderVO3.getDateOrderState() + ",");
		System.out.println(dateappoorderVO3.getDateStarRateA());
		System.out.println(dateappoorderVO3.getDateStarRateB());
		System.out.println(dateappoorderVO3.getDateCE());
		System.out.println("---------------------");

		// search
//		List<DateappoorderVO> list = dao.getAll();
//		for (DateappoorderVO aDateappoorder : list) {
//			System.out.print(aDateappoorder.getDateappoorderno() + ",");
//			System.out.print(aDateappoorder.getEname() + ",");
//			System.out.print(aDateappoorder.getJob() + ",");
//			System.out.print(aDateappoorder.getHiredate() + ",");
//			System.out.print(aDateappoorder.getSal() + ",");
//			System.out.print(aDateappoorder.getComm() + ",");
//			System.out.print(aDateappoorder.getDeptno());
//			System.out.println();
//		}
	}
}
