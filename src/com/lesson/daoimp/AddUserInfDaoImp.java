package com.lesson.daoimp;

import java.sql.SQLException;

import com.lesson.bean.User;
import com.lesson.bean.UserInf;
import com.lesson.dao.AddUserInfDao;
import com.lesson.databasehelper.DataBaseHelper;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class AddUserInfDaoImp implements AddUserInfDao{
	
	@Override
	public boolean addUser(User u) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		String sql = "insert into user(username,	password,email,isonline,onlinedevice1,onlinedevice2,onlinedevice3)values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
			state.setString(1, u.getUsername());
			state.setString(2, u.getPassword());
			state.setString(3, u.getEmail());
			state.setInt(4, u.getIsonline());
			state.setString(5, u.getOnlinedevice1());
			state.setString(6, u.getOnlinedevice2());
			state.setString(7, u.getOnlinedevice3());
			state.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean addUserInf(UserInf user) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		String sql = "insert into user_information(username,nickname,headimage,email,sex,school,course,birthday) values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
			state.setString(1, user.getUsername());
			state.setString(2, user.getNickname());
			state.setString(3, user.getHeadimagePath());
			state.setString(4, user.getEmail());
			state.setInt(5, user.getSex());
			state.setString(6, user.getSchool());
			state.setString(7, user.getCourse());
			state.setString(8, user.getBirthday());
			state.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
