package com.lesson.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.lesson.bean.Teacher;
import com.lesson.dao.GetTeacherInfDao;
import com.lesson.daoimp.GetTeacherInfImp;

/**
 * Servlet implementation class TeacherLoginServlet
 */
@WebServlet("/TeacherLoginServlet")
public class TeacherLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherLoginServlet() {
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
		String teacherUsername = request.getParameter("teacherUsername");
		String password = request.getParameter("password");
		GetTeacherInfDao dao = new GetTeacherInfImp();
		Teacher teacher = dao.getTeacherInf(teacherUsername, password);
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		if(teacher == null){
			json.put("result", "failure");
		}else{
			json.put("result", "success");
			json.put("teacherid", teacher.getId());
			json.put("teacherUsername", teacher.getTeacherUsername());
			json.put("teacherheadimage", teacher.getTeacherimage());
			json.put("teacherintroduction", teacher.getTeacherintroduction());
			json.put("teachername", teacher.getTeachername());
		}
		out.print(json.toString());
		out.flush();
		out.close();
	}

}
