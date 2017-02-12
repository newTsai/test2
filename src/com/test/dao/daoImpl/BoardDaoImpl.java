package com.test.dao.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.test.dao.BoardDao;
import com.test.entity.board.Board;

@Transactional
public class BoardDaoImpl implements BoardDao {
	public BoardDaoImpl() {
	}

	@Resource
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean addAnnouncement(Board b) {
		sessionFactory.getCurrentSession().save(b);
		return true;
	}


	@Override
	public void deleteAnnouncementList(String listStr) {
		List<Integer> list2 = Stream.of(listStr.split(",")).map(str -> Integer.parseInt(str))
				.collect(Collectors.toList());
		String sqlCode = "delete from Board as b where b.id in(:ids)";
		sessionFactory.getCurrentSession().createQuery(sqlCode).setParameterList("ids", list2).executeUpdate();
	}

	@Override
	public boolean alterAnnouncement(Board b, String title, String content, Date modifyTime) {
		b.setTitle(title);
		b.setContent(content);
		b.setModifyTime(modifyTime);
		sessionFactory.getCurrentSession().update(b);
		return true;
	}

	@Override
	public Board searchAnnouncement(int id) {
		String sqlCode = "from Board as b where b.id = ?";
		Board b = (Board) sessionFactory.getCurrentSession().createQuery(sqlCode).setString(0, id + "").uniqueResult();

		return b;
	}

	@Override
	public List<Board> searchAnnouncements() {
		String sqlCode = "from Board as b order by b.modifyTime DESC ,b.id DESC";
		List<Board> result = sessionFactory.getCurrentSession().createQuery(sqlCode).list();
		return result;
	}

	@Override
	public List<Board> searchAnnouncements(int page, String search) {
		String sqlCode = "from Board as b where b.title like? order by b.modifyTime DESC ,b.id DESC";
		List<Board> list = sessionFactory.getCurrentSession().createQuery(sqlCode).setString(0, "%" + search + "%")
				.list();
		List<Board> result = new ArrayList<Board>();
		for (int i = 0; i < list.size(); i++) {
			if (i / 10 < page)
				i += 9;
			else if (i / 10 == page)
				result.add(list.get(i));
			else if (i / 10 > page)
				break;
		}
		return result;
	}

	@Override
	public List<Board> searchUserAnnouncements(String user_id) {
		String sqlCode = "from Board as b where b.author_id= ? order by b.modifyTime DESC ,b.id DESC";
		List<Board> result = sessionFactory.getCurrentSession().createQuery(sqlCode).setString(0, user_id).list();
		return result;
	}

	@Override
	public int getAnnouncementCounts(String search) {
		String sqlCode = "select count(*) from Board as b where b.title like?";
		Query query = sessionFactory.getCurrentSession().createQuery(sqlCode).setString(0, "%" + search + "%");
		return (int) ((Long) query.uniqueResult()).intValue();
	}

	@Override
	public void alterAnnouncements(String user_id, String user_name) {
		String sqlCode = "update Board  as b set b.author_name = ? where b.author_id = ?";
		sessionFactory.getCurrentSession().createQuery(sqlCode).setString(0, user_name).setString(1, user_id)
				.executeUpdate();
	}

	@Override
	public void deleteOldAnnouncements(Date time) {
		String sqlCode = "delete from Board as b where b.deadline < ?";
		sessionFactory.getCurrentSession().createQuery(sqlCode).setTimestamp(0, time).executeUpdate();
	}
}
