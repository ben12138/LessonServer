package com.lesson.daoimp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lesson.bean.CourseInf;
import com.lesson.dao.TeacherGetCourseDao;
import com.lesson.databasehelper.DataBaseHelper;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class TeacherGetCourseDaoImp implements TeacherGetCourseDao {

	@Override
	public List<CourseInf> getmycourse(int teacherid) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		String sql = "select * from courseinf where teacherid=?";
		List<CourseInf> courses = new ArrayList<>();
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
			state.setInt(1, teacherid);
			ResultSet rs = state.executeQuery();
			while(rs.next()){
				CourseInf course = new CourseInf();
				course.setId(rs.getInt(1));
				course.setCourseid(rs.getInt(2));
				course.setTeacherid(rs.getInt(3));
				course.setCoursename(rs.getString(4));
				course.setCourseintroduction(rs.getString(5));
				course.setCoursedegree(rs.getDouble(6));
				course.setCoursecomments(rs.getString(7));
				course.setCatalogue(rs.getString(8));
				course.setAndroidimage(rs.getString(9));
				course.setImage(rs.getString(10));
				course.setWatchernum(rs.getInt(11));
				courses.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;
	}
	
	public static void main(String[] args) {
		System.out.println(new TeacherGetCourseDaoImp().getmycourse(1));
	}
	
}
