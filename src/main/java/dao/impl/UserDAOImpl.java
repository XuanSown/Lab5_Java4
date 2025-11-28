package dao.impl;

import dao.UserDao;
import entity.User;
import jakarta.persistence.EntityManager;
import util.XJPA;

public class UserDAOImpl implements UserDao {

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		EntityManager em = XJPA.getEntityManager();
		try {
			return em.find(User.class, id);
		} finally {
			// TODO: handle finally clause
			em.close();
		}
	}

	@Override
	public void create(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

}
