package com.test.action.useraction;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.entity.board.Board;
import com.test.entity.user.User;
import com.test.service.serviceImpl.BoardServiceImpl;
import com.test.service.serviceImpl.UserServiceImpl;

public class ConfirmAlterUserAction extends ActionSupport {

	private static final long serialVersionUID = -4695249388662944298L;
	private Map session;
	private UserServiceImpl userServiceImpl;
	private BoardServiceImpl boardServiceImpl;
	private String user_oldPassword, user_password, user_password2, user_name, user_mail, user_phone;
	private User user;
	private List<Board> userBoards;

	@Autowired
	public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	@Autowired
	public void setBoardServiceImpl(BoardServiceImpl boardServiceImpl) {
		this.boardServiceImpl = boardServiceImpl;
	}

	@Override
	public String execute() throws Exception {
		ActionContext context = ActionContext.getContext();
		session = context.getSession();
		String id = (String) session.get("id");
		String str = "error";
		if ((String) session.get("id") == null || ((String) session.get("id")).equals(""))
			return str;
		else if (getUser_oldPassword() == null) {
			str = userServiceImpl.alterUser(id, getUser_name(), getUser_mail(), getUser_phone());
			setUser(userServiceImpl.getUser(id));
			if (!str.equals("success")) {
				addFieldError("alterUserError", str);
				str = "fail";
			} else {
				session.put("name", getUser_name());
				session.put("boards", boardServiceImpl.changePage((int) session.get("nowPage") - 1,""));
			}
		} else {
			str = userServiceImpl.alterUser(id, getUser_oldPassword(), getUser_password(), getUser_password2(), 1);
			if (!str.equals("success")) {
				addFieldError("alterUserError", str);
				str = "fail2";

			}
		}
		setUser(userServiceImpl.getUser(id));
		setUserBoards(boardServiceImpl.searchAnnouncements(id));
		return str;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUser_oldPassword() {
		return user_oldPassword;
	}

	public void setUser_oldPassword(String user_oldPassword) {
		this.user_oldPassword = user_oldPassword;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_password2() {
		return user_password2;
	}

	public void setUser_password2(String user_password2) {
		this.user_password2 = user_password2;
	}

	public List<Board> getUserBoards() {
		return userBoards;
	}

	public void setUserBoards(List<Board> userBoards) {
		this.userBoards = userBoards;
	}

}