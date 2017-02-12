package com.test.action.boardaction;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.service.serviceImpl.BoardServiceImpl;
import com.test.service.serviceImpl.UserServiceImpl;

public class ConfirmNewsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2751048582471925675L;
	private UserServiceImpl userServiceImpl;
	private BoardServiceImpl boardServiceImpl;
	private String newTitle;
	private String newContentHTML;
	private String newContentText;

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
			if (boardServiceImpl.addAnnouncement(user_id, getNewTitle(), getNewContentHTML())) {
				session.put("search","");
				result = "success";
				int totalPages[] = new int[boardServiceImpl.getPageCounts("")];
				int nowPage = (int) session.get("nowPage");
				for (int i = 0; i < totalPages.length; i++)
					totalPages[i] = i + 1;
				nowPage=1;
				session.put("totalPages", totalPages);
				session.put("nowPage", nowPage);
				session.put("boards", boardServiceImpl.changePage(nowPage - 1,""));
				
			}
		return result;

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

}
