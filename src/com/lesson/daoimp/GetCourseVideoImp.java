package com.lesson.daoimp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lesson.bean.CourseUrl;
import com.lesson.dao.GetCourseVideoDao;
import com.lesson.databasehelper.DataBaseHelper;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class GetCourseVideoImp implements GetCourseVideoDao {

	@Override
	public List<CourseUrl> getcourses(int courseinfid) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		String sql = "select * from catalogue where courseinfid=?";
		List<CourseUrl> courses = new ArrayList<>();
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
			state.setInt(1, courseinfid);
			ResultSet rs = state.executeQuery();
			while(rs.next()){
				CourseUrl course = new CourseUrl();
				course.setId(rs.getInt("id"));
				course.setCourseinfid(rs.getInt("courseinfid"));
				course.setCoursename(rs.getString("coursename"));
				course.setCourseurl(rs.getString("courseurl"));
				courses.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return courses;
		}
		
		return courses;
	}

	public static void main(String[] args) {
		List<CourseUrl> courses = new GetCourseVideoImp().getcourses(5);
		for(CourseUrl course:courses){
			System.out.println(course);
		}
	}
	
}
