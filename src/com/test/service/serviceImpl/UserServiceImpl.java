package com.test.service.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.test.dao.BoardDao;
import com.test.dao.UserDao;
import com.test.entity.user.User;
import com.test.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDaoImpl;
	private BoardDao boardDaoImpl;

	@Autowired
	public void setUserdaoImpl(UserDao userdaoImpl) {
		this.userDaoImpl = userdaoImpl;
	}

	@Autowired
	public void setBoardDaoImpl(BoardDao boardDaoImpl) {
		this.boardDaoImpl = boardDaoImpl;
	}

	@Override
	public User getUser(String user_id) {
		User user = userDaoImpl.searchUser(user_id);
		return user;
	}

	@Override
	public String alterUser(String user_id, String user_name, String user_mail, String user_phone) {
		String str = "success";
		String p_name = "\\S+";
		String p_mail = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$";
		String p_phone = "09\\d{2}[-]?\\d{6}|09\\d{2}[-]\\d{3}[-]\\d{3}|\\+8869\\d{2}\\d{6}";
		if (!user_name.matches(p_name)) {
			str = "會員名稱不得有空";
			return str;
		}
		if (!user_mail.matches(p_mail)) {
			str = "會員信箱不符合xxx@xxx.xxx(.xxx)";
			return str;
		}
		if (!user_phone.matches(p_phone)) {
			str = "會員手機不符合(+886)09xx(-)xxx(-)xxx";
			return str;
		}
		User u = userDaoImpl.searchUser(user_id);
		userDaoImpl.alterUser(u, user_name, "", user_mail, user_phone, u.getUser_newsCounts(), null);
		boardDaoImpl.alterAnnouncements(user_id, user_name);
		return str;
	}

	@Override
	public String login(String user_id, String user_password) {
		String str = "e r r o r";
		String p = "\\w+";
		if (!user_id.matches(p) || !user_password.matches(p))
			return str;
		User user = userDaoImpl.searchUser(user_id, user_password);
		if (user != null) {
			userDaoImpl.alterUser(user, user.getUser_name(), user.getUser_password(), "", "", user.getUser_newsCounts(),
					new Date());
			str = user.getUser_name();
		}
		return str;

	}

	@Override
	public String joinBoard(String user_id, String user_name, String user_password) {
		String str = "e r r o r";
		String p_id = "[a-zA-Z0-9]{6,}";
		String p_password = "\\w{6,}";
		String p_name = "\\S+";
		if (!user_id.matches(p_id)) {
			str = "帳號須為六位以上英文或數字字元";
			return str;
		}

		if (!user_password.matches(p_password)) {
			str = "密碼須為六位以上非空字元";
			return str;
		}

		if (!user_name.matches(p_name)) {
			str = "會員名不能有空白字元";
			return str;
		}
		if (userDaoImpl.searchUser(user_id) == null) {
			userDaoImpl.addUser(user_id, user_name, user_password);
			str = "success";
		} else {
			str = "此帳號已存在";
		}
		return str;
	}

	@Override
	public String alterUser(String id, String user_oldPassword, String user_password, String user_password2, int a) {
		String str = "success";
		User user = userDaoImpl.searchUser(id, user_oldPassword);
		if (user == null) {
			str = "原密碼錯誤";
			return str;
		}
		if (user_password.equals(user_oldPassword)) {
			str = "新密碼不得與原密碼相同";
			return str;
		} else {
			if (!user_password.equals(user_password2)) {
				str = "新密碼和確認新密碼不相同";
				return str;
			}
			String p_password = "\\w{6,}";
			if (!user_password.matches(p_password)) {
				str = "新密碼要六位以上非空字元";
				return str;
			}
			userDaoImpl.alterUser(user, "", user_password, "", "", user.getUser_newsCounts(), new Date());
		}
		return str;
	}
}
