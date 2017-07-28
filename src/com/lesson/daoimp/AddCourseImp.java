package com.lesson.daoimp;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lesson.dao.AddCourseDao;
import com.lesson.databasehelper.DataBaseHelper;
import com.lesson.util.RemoveSuffix;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class AddCourseImp implements AddCourseDao {

	@Override
	public String addcourse(String username, int courseinfid) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		username = new RemoveSuffix().removeSuffix(username)+"courses";
		String sql = "insert into "+username+"(courseinfid)values(?)";
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
			state.setInt(1, courseinfid);
			state.executeUpdate();
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failure";
		}
	}

	@Override
	public boolean hascourse(String username, int courseinfid) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		username = new RemoveSuffix().removeSuffix(username)+"courses";
		String sql = "select * from "+username+" where courseinfid=?";
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
			state.setInt(1, courseinfid);
			ResultSet rs = state.executeQuery();
			while(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			String createsql = "create table "+username+"courses  (id int(10) not null AUTO_INCREMENT,courseinfid int(10) not null,primary key(id))charset=utf8";
			try {
				PreparedStatement state = (PreparedStatement) conn.prepareStatement(createsql);
				state.execute();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(new AddCourseImp().deletecourse("m13814545863@163.com", 7));
	}

	@Override
	public String deletecourse(String username, int courseinfid) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		username = new RemoveSuffix().removeSuffix(username)+"courses";
		String sql = "delete from "+username+" where courseinfid=?";
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
			state.setInt(1, courseinfid);
			state.executeUpdate();
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failure";
		}
	}
	
}
