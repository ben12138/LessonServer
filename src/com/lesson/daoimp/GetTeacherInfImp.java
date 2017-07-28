package com.lesson.daoimp;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lesson.bean.Teacher;
import com.lesson.dao.GetTeacherInfDao;
import com.lesson.databasehelper.DataBaseHelper;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class GetTeacherInfImp implements GetTeacherInfDao {

	@Override
	public Teacher getTeacherInf(int id) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		String sql = "select * from teacher where id=?";
		Teacher teacher = null;
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
			state.setInt(1, id);
			ResultSet rs = state.executeQuery();
			while(rs.next()){
				teacher = new Teacher();
				teacher.setTeacherimage(rs.getString("teacherimage"));
				teacher.setTeacherintroduction(rs.getString("teacherintroduct"));
				teacher.setTeachername(rs.getString("teachername"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacher;
	}

	@Override
	public Teacher getTeacherInf(String teacherUsername, String password) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		String sql = "select * from teacher where teacherUsername=? and password=?";
		Teacher teacher = null;
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
			state.setString(1, teacherUsername);
			state.setString(2, password);
			ResultSet rs = state.executeQuery();
			while(rs.next()){
				teacher = new Teacher();
				teacher.setId(rs.getInt("id"));
				teacher.setTeacherUsername(teacherUsername);
				teacher.setTeacherimage(rs.getString("teacherimage"));
				teacher.setTeacherintroduction(rs.getString("teacherintroduct"));
				teacher.setTeachername(rs.getString("teachername"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacher;
	}
}
