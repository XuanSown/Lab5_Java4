package dao;

import entity.User;

public interface UserDao {
	User findById(String id);

	User findByEmail(String email);

	void create(User user);

	void update(User user);

	void delete(String id);
}
