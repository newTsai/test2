package com.test.action.boardaction;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.service.serviceImpl.BoardServiceImpl;

public class ChangePageAction extends ActionSupport {

	private static final long serialVersionUID = -2058430175844823456L;
	private String page;
	private int selectedPage;
	private String search;
	private BoardServiceImpl boardServiceImpl;
	private Map session;

	@Autowired
	public void setBoardServiceImpl(BoardServiceImpl boardServiceImpl) {
		this.boardServiceImpl = boardServiceImpl;
	}

	@Override
	public String execute() throws Exception {
		ActionContext context = ActionContext.getContext();
		session = context.getSession();
		if ((String) session.get("id") == null || ((String) session.get("id")).equals(""))
			return "error";
		int nowPage = (int) session.get("nowPage");
		if (page.equals("search")) {
			session.put("search", search);
		}
		if (page.equals("home")) {
			session.put("search", "");
		}
		int totalPages[] = new int[boardServiceImpl.getPageCounts((String) session.get("search"))];
		for (int i = 0; i < totalPages.length; i++)
			totalPages[i] = i + 1;
		if (page.equals("selectedPage")) {
			session.put("nowPage", selectedPage == 0 ? nowPage : selectedPage);
			session.put("boards", boardServiceImpl.changePage(selectedPage == 0 ? nowPage - 1 : selectedPage - 1,
					(String) session.get("search")));
		} else if (page.equals("up")) {
			session.put("nowPage", selectedPage == totalPages.length ? totalPages.length : selectedPage + 1);
			session.put("boards",
					boardServiceImpl.changePage(
							selectedPage == totalPages.length ? totalPages.length - 1 : selectedPage,
							(String) session.get("search")));
		} else if (page.equals("down")) {
			int goPage = selectedPage;
			if (goPage == 0 || goPage == 1) {
				session.put("nowPage", 1);
				session.put("boards", boardServiceImpl.changePage(0, (String) session.get("search")));
			} else {
				session.put("nowPage", goPage - 1);
				session.put("boards", boardServiceImpl.changePage(goPage - 2, (String) session.get("search")));
			}
		} else {
			session.put("nowPage", 1);
			session.put("boards", boardServiceImpl.changePage(0, (String) session.get("search")));
		}
		session.put("totalPages", totalPages);
		return "success";

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

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}
