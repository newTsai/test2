package com.test.service;

import java.util.List;

import com.test.entity.board.Board;

public interface BoardService {
	public boolean addAnnouncement(String user_id,String newTitle,String newContentHTML);

	public boolean alterAnnouncement(int id,String user_id,String newTitle,String newContentHTML);

	public Board searchAnnouncement(int id);
	public List<Board> searchAnnouncements(String user_id);

	public List<Board> changePage(int page,String Search);
	
	public int  getPageCounts(String Search);
	
	public void deleteOld();

	public boolean deleteAnnouncementList(String listStr);
}
