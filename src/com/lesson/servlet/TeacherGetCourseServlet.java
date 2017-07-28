package com.lesson.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lesson.bean.CourseInf;
import com.lesson.dao.TeacherGetCourseDao;
import com.lesson.daoimp.TeacherGetCourseDaoImp;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class TeacherGetCourseServlet
 */
@WebServlet("/TeacherGetCourseServlet")
public class TeacherGetCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherGetCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("json/application;charset=utf-8");
		int teacherid = Integer.parseInt(request.getParameter("teacherid"));
		TeacherGetCourseDao dao = new TeacherGetCourseDaoImp();
		List<CourseInf> courses = dao.getmycourse(teacherid);
		PrintWriter out = response.getWriter();
		if(courses.size() == 0){
			out.println("null");
			out.flush();
			out.close();
		}else{
			JSONArray jsonarray = new JSONArray();
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
			out.println(jsonarray.toString());
			out.flush();
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
