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
 * Servlet implementation class GetTeacherInfServlet
 */
@WebServlet("/GetTeacherInfServlet")
public class GetTeacherInfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTeacherInfServlet() {
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
		GetTeacherInfDao dao = new GetTeacherInfImp();
		int id = Integer.parseInt(request.getParameter("id"));
		Teacher teacher = dao.getTeacherInf(id);
		JSONObject json = new JSONObject();
		json.put("teachername", teacher.getTeachername());
		json.put("teacherimage", teacher.getTeacherimage());
		json.put("teacherintroduction", teacher.getTeacherintroduction());
		out.print(json.toString());
		out.flush();
		out.close();
	}

}
