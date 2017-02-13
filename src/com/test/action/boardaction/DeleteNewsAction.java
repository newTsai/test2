package com.test.action.boardaction;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.entity.board.Board;
import com.test.entity.user.User;
import com.test.service.serviceImpl.BoardServiceImpl;
import com.test.service.serviceImpl.UserServiceImpl;

public class DeleteNewsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4875909284394869560L;
	private Map session;
	private String id;
	private UserServiceImpl userServiceImpl;
	private BoardServiceImpl boardServiceImpl;
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
		String str = "error";
		if ((String) session.get("id") == null || ((String) session.get("id")).equals(""))
			return str;
		if (boardServiceImpl.deleteAnnouncementList(id)) {
			int totalPages[] = new int[boardServiceImpl.getPageCounts((String) session.get("search"))];
			int nowPage = (int) session.get("nowPage");
			for (int i = 0; i < totalPages.length; i++)
				totalPages[i] = i + 1;
			session.put("totalPages", totalPages);
			if (nowPage > totalPages.length) {
				session.put("nowPage", totalPages.length);
				nowPage = totalPages.length;
			}
			session.put("boards", boardServiceImpl.changePage(nowPage - 1, (String) session.get("search")));
			setUser(userServiceImpl.getUser((String) session.get("id")));
			setUserBoards(boardServiceImpl.searchAnnouncements((String) session.get("id")));
			str = "success";
		}
		return str;

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Board> getUserBoards() {
		return userBoards;
	}

	public void setUserBoards(List<Board> userBoards) {
		this.userBoards = userBoards;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
