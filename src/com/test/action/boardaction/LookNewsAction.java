package com.test.action.boardaction;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.entity.board.Board;
import com.test.service.serviceImpl.BoardServiceImpl;
import com.test.service.serviceImpl.UserServiceImpl;

public class LookNewsAction extends ActionSupport {
	private UserServiceImpl userServiceImpl;
	private BoardServiceImpl boardServiceImpl;
	private int id;
	private String author_id;
	private String author_name;
	private String title;
	private String content;
	private Date modifyTime;
	private Date deadline;
	private String page;
	private int selectedPage;
	private Map session;
	private String user_id;
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
		setAuthor_id(b.getAuthor_id());
		setAuthor_name(b.getAuthor_name());
		setTitle(b.getTitle());
		setContent(b.getContent());
		setModifyTime(b.getModifyTime());
		setDeadline(b.getDeadline());
		int nowPage = (int) session.get("nowPage");
		int[] totalPages = (int[]) session.get("totalPages");
		session.put("nowPage", nowPage);
		session.put("totalPages", totalPages);
		setSelectedPage(nowPage);
		setPage("selectedPage");
		return "success";

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
	}
	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
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

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getSelectedPage() {
		return selectedPage;
	}

	public void setSelectedPage(int selectedPage) {
		this.selectedPage = selectedPage;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

}
