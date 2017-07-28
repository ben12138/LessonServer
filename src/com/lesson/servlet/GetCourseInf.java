package com.lesson.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lesson.bean.CourseInf;
import com.lesson.dao.GetAllTypeDao;
import com.lesson.dao.GetCourseDao;
import com.lesson.daoimp.GetAllTypeDaoImp;
import com.lesson.daoimp.GetCourseDaoImp;
import com.lesson.util.RemoveSuffix;

/**
 * Servlet implementation class GetCourseInf
 */
@WebServlet("/GetCourseInf")
public class GetCourseInf extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetCourseInf() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("json/application;charset=utf-8");
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		if (type.equals("phone")) {
			String result = phoneGetInf(request);
			System.out.println(result);
			out.print(result);
			out.flush();
			out.close();
		}
	}

	private String phoneGetInf(HttpServletRequest request) {
		String getInf = request.getParameter("getInf");
		JSONArray jsonarray = new JSONArray();
		if (getInf.equals("tuisong")) {
			GetCourseDao dao = new GetCourseDaoImp();
			HashMap<String, ArrayList<CourseInf>> map = dao.getTuiSong();
			for (String key : map.keySet()) {
				JSONObject json = new JSONObject();
				json.put("coursetype1", key);
				jsonarray.put(json);
				ArrayList<CourseInf> courses = map.get(key);
				for (int i = 0; i < courses.size(); i++) {
					JSONObject json1 = new JSONObject();
					json1.put("id", courses.get(i).getId());
					json1.put("courseid", courses.get(i).getCourseid());
					json1.put("teacherid", courses.get(i).getTeacherid());
					json1.put("coursename", courses.get(i).getCoursename());
					json1.put("courseintroduction", courses.get(i)
							.getCourseintroduction());
					json1.put("coursedegree", courses.get(i).getCoursedegree());
					json1.put("coursecomments", courses.get(i)
							.getCoursecomments());
					json1.put("catalogue", courses.get(i).getCatalogue());
					json1.put("androidimage", courses.get(i).getAndroidimage());
					json1.put("image", courses.get(i).getImage());
					json1.put("watchernum", courses.get(i).getWatchernum());
					jsonarray.put(json1);
				}
			}
		} else if (getInf.equals("allcoursename")) {
			GetAllTypeDao dao = new GetAllTypeDaoImp();
			Map<String, HashMap<String, HashMap<Integer, String>>> map = dao
					.getAllType();
			for (String key : map.keySet()) {
				JSONObject json1 = new JSONObject();
				json1.put("coursetype1", key);
				jsonarray.put(json1);
				for (String key1 : map.get(key).keySet()) {
					JSONObject json2 = new JSONObject();
					json2.put("coursetype2", key1);
					jsonarray.put(json2);
					for (Integer key2 : map.get(key).get(key1).keySet()) {
						JSONObject json3 = new JSONObject();
						json3.put("id", key2 + "");
						json3.put("coursetype3",
								map.get(key).get(key1).get(key2));
						jsonarray.put(json3);
					}
				}
			}
		} else if (getInf.equals("allcourses")) {
			GetCourseDao dao = new GetCourseDaoImp();
			List<CourseInf> courses = dao.getallCourses();
			return getjsonarray(courses);
		} else if (getInf.equals("getmycourse")) {
			String username = request.getParameter("username");
			username = new RemoveSuffix().removeSuffix(username);
			GetCourseDao dao = new GetCourseDaoImp();
			List<CourseInf> courses = dao.getmyCourses(username);
			return getjsonarray(courses);
		} else if (getInf.equals("getcourses")) {
			String coursetype1 = request.getParameter("coursetype1");
			String coursetype2 = request.getParameter("coursetype2");
			String coursetype3 = request.getParameter("coursetype3");
			GetCourseDao dao = new GetCourseDaoImp();
			List<CourseInf> courses = dao.getcourses(coursetype1, coursetype2,
					coursetype3);
			return getjsonarray(courses);
		} else if (getInf.equals("searchcourses")) {
			String name = request.getParameter("name");
			GetCourseDao dao = new GetCourseDaoImp();
			List<CourseInf> courses = dao.searchcourses(name);
			return getjsonarray(courses);
		}else if(getInf.equals("getcoursetype1")){
			String coursetype1 = request.getParameter("coursetype1");
			GetCourseDao dao = new GetCourseDaoImp();
			List<CourseInf> courses = dao.getcourses(coursetype1);
			return getjsonarray(courses);
		}else if(getInf.equals("getcoursetype2")){
			String coursetype1 = request.getParameter("coursetype1");
			String coursetype2 = request.getParameter("coursetype2");
			GetCourseDao dao = new GetCourseDaoImp();
			List<CourseInf> courses = dao.getcourses(coursetype1, coursetype2);
			return getjsonarray(courses);
		}else if (getInf.equals("getcoursetype3")) {
			String coursetype1 = request.getParameter("coursetype1");
			String coursetype2 = request.getParameter("coursetype2");
			String coursetype3 = request.getParameter("coursetype3");
			GetCourseDao dao = new GetCourseDaoImp();
			List<CourseInf> courses = dao.getcourses(coursetype1, coursetype2,
					coursetype3);
			return getjsonarray(courses);
		}
		return jsonarray.toString();
	}

	public String getjsonarray(List<CourseInf> courses) {
		JSONArray jsonarray = new JSONArray();
		if (courses.size() == 0) {
			return "null";
		} else {
			for (int i = 0; i < courses.size(); i++) {
				JSONObject json = new JSONObject();
				CourseInf course = courses.get(i);
				json.put("id", course.getId());
				json.put("courseid", course.getCourseid());
				json.put("teacherid", course.getTeacherid());
				json.put("coursename", course.getCoursename());
				json.put("courseintroduction", course.getCourseintroduction());
				json.put("coursedegree", course.getCoursedegree());
				json.put("coursecomments", course.getCoursecomments());
				json.put("catalogue", course.getCatalogue());
				json.put("androidimage", course.getAndroidimage());
				json.put("image", course.getImage());
				json.put("watchernum", course.getWatchernum());
				jsonarray.put(json);
			}
			return jsonarray.toString();
		}
	}
}
