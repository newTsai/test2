package com.test.service.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import com.test.dao.BoardDao;
import com.test.dao.UserDao;
import com.test.entity.board.Board;
import com.test.entity.user.User;
import com.test.service.BoardService;

public class BoardServiceImpl implements BoardService {
	private BoardDao boardDaoImpl;
	private UserDao userDaoImpl;

	@Autowired
	public void setBoardDaoImpl(BoardDao boardDaoImpl) {
		this.boardDaoImpl = boardDaoImpl;
	}

	@Autowired
	public void setUserDaoImpl(UserDao userDaoImpl) {
		this.userDaoImpl = userDaoImpl;
	}

	public UserDao getUserDaoImpl() {
		return userDaoImpl;
	}

	@Override
	public boolean addAnnouncement(String user_id, String newTitle, String newContentHTML) {
		User u = userDaoImpl.searchUser(user_id);
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar.setTime(date);
		calendar2.setTime(date);
		calendar2.add(Calendar.DATE, 15);

		Board b = new Board(user_id, u.getUser_name(), newTitle, newContentHTML, calendar.getTime(),
				calendar2.getTime());
		boardDaoImpl.addAnnouncement(b);
		return userDaoImpl.alterUser(u, "", "", "", "", u.getUser_newsCounts() + 1, null);

	}

	@Override
	public boolean deleteAnnouncementList(String listStr) {
		boardDaoImpl.deleteAnnouncementList(listStr);
		return true;
	}

	@Override
	public boolean alterAnnouncement(int id, String user_id, String newTitle, String newContentHTML) {
		Board b = boardDaoImpl.searchAnnouncement(id);
		if (b == null)
			return false;
		boardDaoImpl.alterAnnouncement(b, newTitle, newContentHTML, new Date());
		return true;
	}

	@Override
	public Board searchAnnouncement(int id) {
		Board model = boardDaoImpl.searchAnnouncement(id);
		return model;
	}

	@Override
	public List<Board> changePage(int page, String search) {
		return boardDaoImpl.searchAnnouncements(page, search);

	}

	@Override
	public int getPageCounts(String search) {
		if (boardDaoImpl.getAnnouncementCounts(search) == 0)
			return 1;
		return boardDaoImpl.getAnnouncementCounts(search) % 10 == 0 ? boardDaoImpl.getAnnouncementCounts(search) / 10
				: boardDaoImpl.getAnnouncementCounts(search) / 10 + 1;
	}

	@Override
	public List<Board> searchAnnouncements(String user_id) {

		return boardDaoImpl.searchUserAnnouncements(user_id);

	}

	@Override
	public void deleteOld() {
		boardDaoImpl.deleteOldAnnouncements(new Date());
	}
}
