package com.lesson.daoimp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lesson.bean.CourseInf;
import com.lesson.dao.GetCourseDao;
import com.lesson.databasehelper.DataBaseHelper;
import com.mysql.fabric.xmlrpc.base.Data;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class GetCourseDaoImp implements GetCourseDao {

	@Override
	public HashMap<String, ArrayList<String>> getAllCatalogue(String type1) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		String sql = "select * from course";
		HashMap<String, ArrayList<String>> catalogue = new HashMap<String, ArrayList<String>>();
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()){
				if(rs.getString(2).equals(type1)){
					if(catalogue.containsKey(rs.getString(3))){
						if(!catalogue.get(rs.getString(3)).contains(rs.getString(4))){
							catalogue.get(rs.getString(3)).add(rs.getString(4));
						}
					}else{
						ArrayList<String> list = new ArrayList<String>();
						list.add(rs.getString(4));
						catalogue.put(rs.getString(3), list);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return catalogue;
	}

	@Override
	public List <CourseInf> getAllCourse(String type1, String type3) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		String sql = "select * from course where coursetype1 = ? and coursetype3 = ?";
		List <CourseInf> courses = new ArrayList<CourseInf>();
		PreparedStatement state = null;
		try {
			state = (PreparedStatement) conn.prepareStatement(sql);
			state.setString(1, type1);
			state.setString(2, type3);
			ResultSet rs = state.executeQuery();
			while(rs.next()){
				String sql1 = "select * from courseinf where courseid = ?";
				state = (PreparedStatement) conn.prepareStatement(sql1);
				state.setInt(1, rs.getInt(1));
				ResultSet rs1 = state.executeQuery();
				while(rs1.next()){
					CourseInf course = new CourseInf();
					course.setId(rs1.getInt(1));
					course.setCourseid(rs1.getInt(2));
					course.setTeacherid(rs1.getInt(3));
					course.setCoursename(rs1.getString(4));
					course.setCourseintroduction(rs1.getString(5));
					course.setCoursedegree(rs1.getDouble(6));
					course.setCoursecomments(rs1.getString(7));
					course.setCatalogue(rs1.getString(8));
					course.setAndroidimage(rs1.getString(9));
					course.setImage(rs1.getString(10));
					course.setWatchernum(rs1.getInt(11));
					courses.add(course);
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return courses;
	}

	@Override
	public HashMap<String, ArrayList<CourseInf>> getTuiSong() {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		HashMap<String, ArrayList<CourseInf>> map = new HashMap<String, ArrayList<CourseInf>>();
		String sql = "select * from tuisongcourse";
		PreparedStatement state;
		try {
			state = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs  =state.executeQuery();
			while(rs.next()){
				String sql1 = "select * from course where id = ?";
				state = (PreparedStatement) conn.prepareStatement(sql1);
				state.setInt(1, rs.getInt(2));
				ResultSet rs1 = state.executeQuery();
				while(rs1.next()){
					if(!map.containsKey(rs.getString(1))){
						ArrayList<CourseInf> courses = new ArrayList<CourseInf>();
						String sql3 = "select * from courseinf where courseid = ? order by id desc limit 0,4";
						state = (PreparedStatement) conn.prepareStatement(sql3);
						state.setInt(1, rs1.getInt(1));
						ResultSet rs2 = state.executeQuery();
						while(rs2.next()){
							CourseInf course = new CourseInf();
							course.setId(rs2.getInt(1));
							course.setCourseid(rs2.getInt(2));
							course.setTeacherid(rs2.getInt(3));
							course.setCoursename(rs2.getString(4));
							course.setCourseintroduction(rs2.getString(5));
							course.setCoursedegree(rs2.getDouble(6));
							course.setCoursecomments(rs2.getString(7));
							course.setCatalogue(rs2.getString(8));
							course.setAndroidimage(rs2.getString(9));
							course.setImage(rs2.getString(10));
							course.setWatchernum(rs2.getInt(11));
							courses.add(course);
						}
						map.put(rs1.getString(2), courses);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	@Override
	public List<CourseInf> getallCourses() {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		List<CourseInf> courses = new ArrayList<CourseInf>();
		String sql = "select * from courseinf";
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
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
	
	@Override
	public List<CourseInf> getmyCourses(String username) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		List<CourseInf> courses = new ArrayList<CourseInf>();
		List<CourseInf> allCourses = getallCourses();
		String sql = "select * from "+username+"courses";
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = state.executeQuery();
			while(rs.next()){
				int id = rs.getInt(2);
				for(int i=0;i<allCourses.size();i++){
					if(allCourses.get(i).getId() == id){
						courses.add(allCourses.get(i));
					}
				}
			}
			System.out.println(courses.size());
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
		return courses;
	}
	
	public static void main(String[] args) {
//		System.out.println(new GetCourseDaoImp().searchcourses(""));
	}

	@Override
	public List<CourseInf> getcourses(String coursetype1, String coursetype2,
			String coursetype3) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		System.out.println("search");
		String sql1 = "select * from course where coursetype1=? and coursetype2=? and coursetype3=?";
		String sql2 = "select * from courseinf where courseid=?";
		int courseid = 0;
		List<CourseInf> courses = new ArrayList<CourseInf>();
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql1);
			state.setString(1, coursetype1);
			state.setString(2, coursetype2);
			state.setString(3, coursetype3);
			ResultSet rs = state.executeQuery();
			while(rs.next()){
				courseid = rs.getInt(1);
			}
			if(courseid != 0){
				state = (PreparedStatement) conn.prepareStatement(sql2);
				state.setInt(1, courseid);
				rs = state.executeQuery();
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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return courses;
	}

	@Override
	public List<CourseInf> searchcourses(String name) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		String sql = "select * from courseinf where coursename like ?";
		List<CourseInf> courses = new ArrayList<>();
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
			state.setString(1, "%"+name+"%");
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
	
	/**
	 * 获取一级课程
	 */
	@Override
	public List<CourseInf> getcourses(String coursetype1){
		Connection conn = DataBaseHelper.getConnection();
		String sql1 = "select id from course where coursetype1=?";
		String sql2 = "select * from courseinf where courseid=?";
		List<Integer> courseid = new ArrayList<Integer>();
		List<CourseInf> courses = new ArrayList<CourseInf>();
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql1);
			state.setString(1, coursetype1);
			ResultSet rs = state.executeQuery();
			while(rs.next()){
				courseid.add(rs.getInt(1));
			}
			if(courseid.size() != 0){
				for(int j = 0;j<courseid.size();j++){
					state = (PreparedStatement) conn.prepareStatement(sql2);
					state.setInt(1, courseid.get(j));
					rs = state.executeQuery();
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
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;
	}
	/**
	 * 获取二级课程
	 */
	@Override
	public List<CourseInf> getcourses(String coursetype1, String coursetype2){
		// TODO Auto-generated method stub
				Connection conn = DataBaseHelper.getConnection();
				String sql1 = "select id from course where coursetype1=? and coursetype2=?";
				String sql2 = "select * from courseinf where courseid=?";
				List<Integer> courseid = new ArrayList<Integer>();
				List<CourseInf> courses = new ArrayList<CourseInf>();
				try {
					PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql1);
					state.setString(1, coursetype1);
					state.setString(2, coursetype2);
					ResultSet rs = state.executeQuery();
					while(rs.next()){
						courseid.add(rs.getInt(1));
					}
					if(courseid.size() != 0){
						for(int j = 0;j<courseid.size();j++){
							state = (PreparedStatement) conn.prepareStatement(sql2);
							state.setInt(1, courseid.get(j));
							rs = state.executeQuery();
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
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return courses;
	}
	
}
