package com.test.dao;

import java.util.Date;
import java.util.List;

import com.test.entity.board.Board;

public interface BoardDao {
	public boolean addAnnouncement(Board b);

	public boolean alterAnnouncement(Board b, String title, String content, Date modifyTime);

	public Board searchAnnouncement(int id);

	public List<Board> searchAnnouncements(int page, String search);

	public List<Board> searchUserAnnouncements(String user_id);

	public void alterAnnouncements(String user_id, String user_name);

	public int getAnnouncementCounts(String search);

	public void deleteOldAnnouncements(Date time);

	public List<Board> searchAnnouncements();

	public void deleteAnnouncementList(String listStr);
}