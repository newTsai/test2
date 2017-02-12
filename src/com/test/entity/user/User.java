package com.test.entity.user;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class User implements Serializable{
	
	private static final long serialVersionUID = 2962102133937797270L;


	public User() {
		}
	private String user_id;
	private String user_name;
	private String user_password;
	private String user_mail;
	private String user_phone;
	private int user_newsCounts;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime")  
	private Date createTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastLoginTime")  
	private Date lastLoginTime;


	public User(String user_id, String user_name, String user_password,Date createTime,Date lastLoginTime) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_password = user_password;
		this.createTime=createTime;
		this.lastLoginTime = lastLoginTime;
	}
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public int getUser_newsCounts() {
		return user_newsCounts;
	}

	public void setUser_newsCounts(int user_newsCounts) {
		this.user_newsCounts = user_newsCounts;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

}
