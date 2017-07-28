package com.lesson.dao;

import java.util.List;

import com.lesson.bean.Comments;

public interface GetAllCommentsDao {
	public List<Comments> getAllComments(int courseinfid);
	public void upDateAllComments(int id);
	public int addCommens(Comments comment);
}
