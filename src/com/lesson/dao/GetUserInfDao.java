package com.lesson.dao;

import com.lesson.bean.User;
import com.lesson.bean.UserInf;

public interface GetUserInfDao {
	public UserInf getUserInf(String username);
	public User getUser(String username);
	public boolean isRegistered(String email);
	public void upDateUserInf(UserInf u);
}
