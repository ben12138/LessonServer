package com.lesson.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lesson.bean.CourseUrl;
import com.lesson.dao.GetCourseVideoDao;
import com.lesson.daoimp.GetCourseVideoImp;

/**
 * Servlet implementation class GetCourseVideoServlet
 */
@WebServlet("/GetCourseVideoServlet")
public class GetCourseVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCourseVideoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("json/application;charset=utf-8");
		PrintWriter out = response.getWriter();
		int courseinfid = Integer.parseInt(request.getParameter("courseinfid"));
		GetCourseVideoDao dao = new GetCourseVideoImp();
		List<CourseUrl> courses = dao.getcourses(courseinfid);
		JSONArray jsonarray = new JSONArray();
		if(courses.size() == 0){
			out.print("null");
			out.flush();
			out.close();
		}else{
			for(CourseUrl course:courses){
				JSONObject json = new JSONObject();
				json.put("id", course.getId());
				json.put("courseinfid", course.getCourseinfid());
				json.put("coursename", course.getCoursename());
				json.put("courseurl", course.getCourseurl());
				jsonarray.put(json);
			}
			out.print(jsonarray.toString());
			out.flush();
			out.close();
		}
	}

}
