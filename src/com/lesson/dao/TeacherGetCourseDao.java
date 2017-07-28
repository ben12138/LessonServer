package com.lesson.dao;

import java.util.List;

import com.lesson.bean.CourseInf;

public interface TeacherGetCourseDao {
	public List<CourseInf> getmycourse(int teacherid);
}
