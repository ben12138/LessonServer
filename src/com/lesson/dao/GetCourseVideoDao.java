package com.lesson.dao;

import java.util.List;

import com.lesson.bean.CourseUrl;

public interface GetCourseVideoDao {
	public List<CourseUrl> getcourses(int courseinfid);
}
