package com.lesson.dao;

import com.lesson.bean.Teacher;

public interface GetTeacherInfDao {
	public Teacher getTeacherInf(int id);
	public Teacher getTeacherInf(String teacherUsername,String password);
}
