package com.test.action.boardaction;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.entity.board.Board;
import com.test.service.serviceImpl.BoardServiceImpl;
import com.test.service.serviceImpl.UserServiceImpl;

public class ConfirmAlterNewsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2379677295842951815L;
	private UserServiceImpl userServiceImpl;
	private BoardServiceImpl boardServiceImpl;
	private String newTitle;
	private String newContentHTML;
	private String newContentText;
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
		Map session = context.getSession();
		String result = "error";
		if (session.get("id") == null || ((String) session.get("id")).equals(""))
			return result;

		String user_id = (String) session.get("id");
		if (boardServiceImpl.alterAnnouncement(getId(), user_id, getNewTitle(), getNewContentHTML())) {// 修改成功回公告查看
			result = "success";
			int totalPages[] = new int[boardServiceImpl.getPageCounts("")];
			int nowPage = (int) session.get("nowPage");
			for (int i = 0; i < totalPages.length; i++)
				totalPages[i] = i + 1;
			session.put("totalPages", totalPages);
			if (nowPage > totalPages.length) {
				session.put("nowPage", totalPages.length);
				nowPage = totalPages.length;
			}
			session.put("boards", boardServiceImpl.changePage(nowPage - 1, ""));
			Board b = (Board) boardServiceImpl.searchAnnouncement(getId());
			setAuthor_id(b.getAuthor_id());
			setAuthor_name(b.getAuthor_name());
			setTitle(b.getTitle());
			setContent(b.getContent());
			setModifyTime(b.getModifyTime());
			setDeadline(b.getDeadline());

		}
		return result;// error的話到error頁

	}

	public String getNewTitle() {
		return newTitle;
	}

	public void setNewTitle(String newTitle) {
		this.newTitle = newTitle;
	}

	public String getNewContentHTML() {
		return newContentHTML;
	}

	public void setNewContentHTML(String newContentHTML) {
		this.newContentHTML = newContentHTML;
	}

	public String getNewContentText() {
		return newContentText;
	}

	public void setNewContentText(String newContentText) {
		this.newContentText = newContentText;
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
