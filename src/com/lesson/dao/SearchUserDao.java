package com.lesson.dao;

import com.lesson.bean.User;

public interface SearchUserDao {
	public static int SUCCESS = 0;
	public static int USERNAME_ERROR = 1;
	public static int PASSWORD_ERROR = 2;
	public static int USERNAME_NOT_EXITS = 3;
	public int searchUser(User u);
	public boolean upDateUser(User u);
}
