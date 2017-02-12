package com.test.service;

import com.test.entity.user.User;

public interface UserService {
public	User getUser(String user_id);
public String login(String user_id,String user_password);
public String joinBoard(String user_id,String user_name,String user_password);
public String alterUser(String user_id, String user_name, String user_mail, String user_phone);
public String alterUser(String id, String user_oldPassword ,String user_password, String user_password2,int a);
}
