package com.test.action.boardaction;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.service.serviceImpl.BoardServiceImpl;
import com.test.service.serviceImpl.UserServiceImpl;

public class AddNewsAction extends ActionSupport{

	private static final long serialVersionUID = 2756989031760066639L;
	private Map session;
	private UserServiceImpl userServiceImpl;
	private BoardServiceImpl boardServiceImpl;
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
		String str = "error";
		if ((String) session.get("id") == null || ((String) session.get("id")).equals(""))
		return str;
		return "success";
	
	}
	
	
}
