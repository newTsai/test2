package com.test.dao;

import java.util.Date;

import com.test.entity.user.User;

public interface UserDao {
	public User searchUser(String user_id);

	public User searchUser(String user_id, String user_password);

	public boolean addUser(String user_id, String user_name, String user_password);

	public boolean alterUser(User u, String user_name, String user_password, String user_mail, String user_phone,
			int user_newscounts, Date lastLoginTime);
}
