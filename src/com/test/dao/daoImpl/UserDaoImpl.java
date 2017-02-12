package com.test.dao.daoImpl;

import java.util.Date;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import com.test.dao.UserDao;
import com.test.entity.user.User;

@Transactional
public class UserDaoImpl implements UserDao {
	private SessionFactory sessionFactory;

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User searchUser(String user_id, String user_password) {
		String sqlCode = "from User as u where u.user_id = ? and u.user_password = ?";
		User u = (User) sessionFactory.getCurrentSession().createQuery(sqlCode).setString(0, user_id)
				.setString(1, user_password).uniqueResult();
		return u;
	}

	@Override
	public User searchUser(String user_id) {
		String sqlCode = "from User as u where u.user_id = ?";
		User u = (User) sessionFactory.getCurrentSession().createQuery(sqlCode).setString(0, user_id).uniqueResult();
		return u;
	}

	@Override
	public boolean addUser(String user_id, String user_name, String user_password) {
		
		User u = new User(user_id, user_name, user_password, new Date(), new Date());
		sessionFactory.getCurrentSession().save(u);
		return true;
	}

	@Override
	public boolean alterUser(User u, String user_name, String user_password, String user_mail, String user_phone,
			int user_newsCounts, Date lastLoginTime) {
		if (user_name != null && !user_name.equals(""))
			u.setUser_name(user_name);
		if (user_password != null && !user_password.equals(""))
			u.setUser_password(user_password);
		if (user_mail != null && !user_mail.equals(""))
			u.setUser_mail(user_mail);
		if (user_phone != null && !user_phone.equals(""))
			u.setUser_phone(user_phone);
		if (lastLoginTime != null)
			u.setLastLoginTime(lastLoginTime);
		u.setUser_newsCounts(user_newsCounts);
		sessionFactory.getCurrentSession().update(u);
		return true;
	}

}
