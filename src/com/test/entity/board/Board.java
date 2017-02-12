package com.test.entity.board;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Board implements Serializable{

	private static final long serialVersionUID = 3898073410590631526L;

	public Board() {
	}
	private int id;
	private String author_id;
	private String author_name;
	private String title;
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime")  
	private Date createTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modifyTime")  
	private Date modifyTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deadline")  
	private Date deadline;

	public Board(String author_id,String author_name,String title, String content, Date createTime,Date deadline) {
		super();
		this.author_id=author_id;
		this.author_name=author_name;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
		this.deadline = deadline;
		this.modifyTime = createTime;

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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

}
