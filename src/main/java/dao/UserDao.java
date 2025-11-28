package dao;

import entity.User;

public interface UserDao {
	User findById(String id);

	void create(User user);

	void update(User user);

	void delete(String id);
}
