package com.test.action.useraction;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.service.serviceImpl.BoardServiceImpl;
import com.test.service.serviceImpl.UserServiceImpl;

/**
 * 登录控制器
 * 
 * @author Administrator
 *
 */
public class LoginAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8019991759721779759L;
	/* 值栈：页面可以直接获取 */
	private String id;// form表单name；进行封装
	private String password;// form表单name；进行封装
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
		String str="error";
		if(id==null||password==null)
		return str;
		str = userServiceImpl.login(id, password);
		ActionContext context = ActionContext.getContext();
		session = context.getSession();
		if (!str.equals("e r r o r")) {
			boardServiceImpl.deleteOld();
			session.put("boards", boardServiceImpl.changePage(0,""));
			session.put("id", id);
			session.put("name", str);
			int totalPages[] = new int[boardServiceImpl.getPageCounts("")];
			for (int i = 0; i < totalPages.length; i++)
				totalPages[i] = i + 1;
			session.put("totalPages", totalPages);
			session.put("nowPage", 1);
			session.put("search","");
			str = "success";
		} else {
			addFieldError("loginError", "帳號密碼錯誤");
			str = "fail";
		}

		return str;
	}

	/**
	 * @return the username
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}