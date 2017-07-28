package com.lesson.dao;

public interface AddCourseDao {
	public String addcourse(String username ,int courseinfid);
	public boolean hascourse(String username,int courseinfid);
	public String deletecourse(String username ,int courseinfid);
}
