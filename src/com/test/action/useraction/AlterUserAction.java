package com.test.action.useraction;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.entity.board.Board;
import com.test.entity.user.User;
import com.test.service.serviceImpl.BoardServiceImpl;
import com.test.service.serviceImpl.UserServiceImpl;

public class AlterUserAction extends ActionSupport {

	private static final long serialVersionUID = 8395724589961706053L;
	private Map session;
	private UserServiceImpl userServiceImpl;
	private BoardServiceImpl boardServiceImpl;
	private User user;

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
		if (id == null || id.equals(""))
			return "error";
		setUser(userServiceImpl.getUser(id));
		return "success";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}