package com.test.action.useraction;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.service.serviceImpl.UserServiceImpl;

/**
 * 登录控制器
 * 
 * @author Administrator
 *
 */
public class LogoutAction extends ActionSupport {

	private static final long serialVersionUID = 5214591311572292653L;
	private Map session;
	private UserServiceImpl userServiceImpl;

	@Autowired
	public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	@Override
	public String execute() throws Exception {
		ActionContext context = ActionContext.getContext();
		session = context.getSession();
		session.clear();
		return "success";
	}
}