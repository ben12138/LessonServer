package com.lesson.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lesson.bean.Course;
import com.lesson.bean.CourseInf;

public interface GetCourseDao {
	public HashMap<String, ArrayList<String>> getAllCatalogue(String type1);
	public List <CourseInf> getAllCourse(String type1,String type3);
	public HashMap<String, ArrayList<CourseInf>> getTuiSong();
	public List<CourseInf> getallCourses();
	public List<CourseInf> getmyCourses(String username);
	public List<CourseInf> getcourses(String coursetype1,String coursetype2,String coursetype3);
	public List<CourseInf> searchcourses(String name);
	public List<CourseInf> getcourses(String coursetype1);
	public List<CourseInf> getcourses(String coursetype1,String coursetype2);
}
