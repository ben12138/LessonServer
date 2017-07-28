package com.lesson.daoimp;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lesson.bean.User;
import com.lesson.bean.UserInf;
import com.lesson.dao.GetUserInfDao;
import com.lesson.databasehelper.DataBaseHelper;
import com.lesson.util.RemoveSuffix;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class GetUserInfDaoImp implements GetUserInfDao{
	@Override
	public UserInf getUserInf(String username) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		String sql = "select * from user_information";
		UserInf user = null;
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()){
				if(rs.getString(2).equals(username)){
					user = new UserInf();
					user.setUsername(username);
					user.setNickname(rs.getString(3));
					user.setHeadimagePath(rs.getString(4));
					user.setEmail(rs.getString(5));
					user.setSex(rs.getInt(6));
					user.setSchool(rs.getString(7));
					user.setBirthday(rs.getString(9));
					user.setIntroduction(rs.getString(10));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		String sql = "select * from user";
		User user = null;
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()){
				if(rs.getString(4).equals(username)){
					user = new User();
					user.setUsername(username);
					user.setPassword(rs.getString(3));
					user.setEmail(rs.getString(4));
					user.setIsonline(rs.getInt(5));
					user.setOnlinedevice1(rs.getString(6));
					user.setOnlinedevice2(rs.getString(7));
					user.setOnlinedevice3(rs.getString(8));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public boolean isRegistered(String email) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		String sql = "select * from user";
		PreparedStatement state;
		try {
			state = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs  =state.executeQuery(sql);
			while(rs.next()){
				if(rs.getString("email").equals(email)){
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static void main(String[] args) {
	}
	@Override
	public void upDateUserInf(UserInf u) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		System.out.println(u.getBirthday());
		String sql = "update user_information set nickname=? ,sex=? ,school=?, birthday=?, introduction=? where email=?";
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
			state.setString(1, u.getNickname());
			state.setInt(2, u.getSex());
			state.setString(3, u.getSchool());

			state.setString(4, u.getBirthday());
			state.setString(5, u.getIntroduction());
			state.setString(6, u.getEmail());
			state.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
