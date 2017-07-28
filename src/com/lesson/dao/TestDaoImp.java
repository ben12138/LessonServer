package com.lesson.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lesson.bean.Test;
import com.lesson.databasehelper.DataBaseHelper;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class TestDaoImp implements TestDao {

	@Override
	public void addTest(Test test) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Test> getTest(int courseid) {
		// TODO Auto-generated method stub
		List<Test> tests = new ArrayList<>();
		Connection conn = DataBaseHelper.getConnection();
		String sql = "select * from test where courseid = ?";
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
			state.setInt(1, courseid);
			ResultSet rs = state.executeQuery();
			while(rs.next()){
				Test test = new Test();
				test.setCourseid(rs.getInt("courseid"));
				test.setQuestion(rs.getString("question"));
				test.setChoiceA(rs.getString("choiceA"));
				test.setChoiceB(rs.getString("choiceB"));
				test.setChoiceC(rs.getString("choiceC"));
				test.setChoiceD(rs.getString("choiceD"));
				test.setAnswer(rs.getString("answer"));
				tests.add(test);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tests;
	}
	
	public static void main(String[] args) {
		System.out.println(new TestDaoImp().getTest(5));
	}
	
}
