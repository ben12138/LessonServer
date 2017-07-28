package com.lesson.daoimp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.lesson.dao.GetAllTypeDao;
import com.lesson.databasehelper.DataBaseHelper;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class GetAllTypeDaoImp implements GetAllTypeDao{
	
	@Override
	public Map<String, HashMap<String, HashMap<Integer,String>>> getAllType() {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		String sql = "select * from course";
		Map<String, HashMap<String, HashMap<Integer,String>>> map = new HashMap<String, HashMap<String,HashMap<Integer,String>>>();
		PreparedStatement state;
		try {
			state = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = state.executeQuery();
			while(rs.next()){
				if(map.containsKey(rs.getString(2))){
					if(map.get(rs.getString(2)).containsKey(rs.getString(3))){
						if(!map.get(rs.getString(2)).get(rs.getString(3)).containsKey(rs.getInt(1))){
							map.get(rs.getString(2)).get(rs.getString(3)).put(rs.getInt(1),rs.getString(4));
						}
					}else{
						map.get(rs.getString(2)).put(rs.getString(3), new HashMap<Integer,String>());
						rs.beforeFirst();
					}
				}else{
					map.put(rs.getString(2), new HashMap<String, HashMap<Integer,String>>());
					rs.beforeFirst();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
	public static void main(String[] args) {
		Map<String, HashMap<String, HashMap<Integer,String>>> map = new GetAllTypeDaoImp().getAllType();
		int i = 0;
		for(String key:map.keySet()){
			System.out.println("一级菜单："+key);
			for(String key1:map.get(key).keySet()){
				System.out.println("	二级菜单："+key1);
				for(Integer key2:map.get(key).get(key1).keySet()){
					System.out.println("		id:"+key2+"三级菜单:"+map.get(key).get(key1).get(key2));
				}
			}
		}
		System.out.println(i);
	}
}
