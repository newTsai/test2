package com.test.action.useraction;

import com.test.service.serviceImpl.BoardServiceImpl;
import com.test.service.serviceImpl.UserServiceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 登录控制器
 * 
 * @author Administrator
 *
 */
public class FirstIntoAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4663216347304744814L;
	String id;
	String name;
	String password;
	private UserServiceImpl userServiceImpl;
	private BoardServiceImpl boardServiceImpl;
	private Map session;

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
		String str = "error";
		if (id == null || name == null || password == null)
			return str;
		str = userServiceImpl.joinBoard(id, name, password);
		ActionContext context = ActionContext.getContext();
		session = context.getSession();
		if (str.equals("success")) {
			boardServiceImpl.deleteOld();
			session.put("boards", boardServiceImpl.changePage(0,""));
			session.put("id", id);
			session.put("name", name);
			int totalPages[] = new int[boardServiceImpl.getPageCounts("")];
			for (int i = 0; i < totalPages.length; i++)
				totalPages[i] = i + 1;
			session.put("totalPages", totalPages);
			session.put("nowPage", 1);
			session.put("search","");
		} else {
			addFieldError("loginError", str);
			str = "fail";
		}
		return str;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
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