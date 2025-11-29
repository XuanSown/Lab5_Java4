package dao.impl;

import dao.LogDAO;
import entity.Log;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.XJPA;

public class LogDAOImpl implements LogDAO {

	@Override
	public void create(Log log) {
		// TODO Auto-generated method stub
		EntityManager em = XJPA.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.persist(log);
			trans.commit();
		} catch (Exception e) {
			// TODO: handle exception
			trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

}
