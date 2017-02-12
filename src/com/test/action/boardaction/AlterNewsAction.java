package com.test.action.boardaction;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.entity.board.Board;
import com.test.service.serviceImpl.BoardServiceImpl;
import com.test.service.serviceImpl.UserServiceImpl;

public class AlterNewsAction extends ActionSupport{

	private static final long serialVersionUID = 2756989031760066639L;
	private Map session;
	private int id;
	private String title,content,user_id;
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
		Board b = (Board) boardServiceImpl.searchAnnouncement(getId());
		if(b==null)
			return "notfound";
		setTitle(b.getTitle());
		setContent(b.getContent());
		return "success";
	
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
