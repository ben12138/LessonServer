package com.lesson.dao;

import java.util.List;

import com.lesson.bean.Test;

public interface TestDao {
	public void addTest(Test test);
	public List<Test> getTest(int courseid);
}
