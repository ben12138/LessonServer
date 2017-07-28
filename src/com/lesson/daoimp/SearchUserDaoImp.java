package com.lesson.daoimp;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lesson.bean.User;
import com.lesson.dao.SearchUserDao;
import com.lesson.databasehelper.DataBaseHelper;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class SearchUserDaoImp implements SearchUserDao{
	
	@Override
	public int searchUser(User u) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		String sql = "select * from user";
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()){
				if(rs.getString("email").equals(u.getUsername())){
					if(rs.getString("password").equals(u.getPassword())){
						return SUCCESS;
					}else{
						return PASSWORD_ERROR;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return USERNAME_NOT_EXITS;
	}
	
	@Override
	public boolean upDateUser(User u) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		String sql = "update user set online=? onlinedevice1=? onlinedevice2=? onlinedevice3=? where email=?" ;
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
			state.setInt(1, u.getIsonline());
			if(u.getOnlinedevice1() == null){
				state.setString(2, "null");
			}else{
				state.setString(2, u.getOnlinedevice1());
			}
			if(u.getOnlinedevice2() == null){
				state.setString(3, "null");
			}else{
				state.setString(3, u.getOnlinedevice2());
			}
			if(u.getOnlinedevice3() == null){
				state.setString(4, "null");
			}else{
				state.setString(4, u.getOnlinedevice3());
			}
			state.setString(5, u.getUsername());
			state.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
