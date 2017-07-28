package com.lesson.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.lesson.dao.AddCourseDao;
import com.lesson.daoimp.AddCourseImp;

/**
 * Servlet implementation class AddCourseServlet
 */
@WebServlet("/AddCourseServlet")
public class AddCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCourseServlet() {
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
		String type = request.getParameter("type");
		if(type.equals("addcourse")){
			addcourse(request, response);
		}else if(type.equals("hascourse")){
			hascourse(request, response);
		}else if(type.equals("deletecourse")){
			deletecourse(request, response);
		}
	}

	public void addcourse(HttpServletRequest request,HttpServletResponse response){
		String username = request.getParameter("username");
		int courseinfid = Integer.parseInt(request.getParameter("courseinfid"));
		String device = request.getParameter("device");
		AddCourseDao dao = new AddCourseImp();
		String result = dao.addcourse(username, courseinfid);
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(device != null){
			out.print(result);
			out.flush();
			out.close();
		}else{
			json.put("result", result);
			out.print(json);
			out.flush();
			out.close();
		}
		
	}
	
	public void hascourse(HttpServletRequest request,HttpServletResponse response){
		String username = request.getParameter("username");
		int courseinfid = Integer.parseInt(request.getParameter("courseinfid"));
		AddCourseDao dao = new AddCourseImp();
		String device = request.getParameter("device");
		boolean result = dao.hascourse(username, courseinfid);
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(device != null){
			if(result){
				out.print("has");
				out.flush();
				out.close();
			}else{
				out.print("no");
				out.flush();
				out.close();
			}
		}else{
			if(result){
				json.put("result", true);
				out.print(json);
				out.flush();
				out.close();
			}else{
				json.put("result", false);
				out.print(json);
				out.flush();
				out.close();
			}
		}
	}
	
	public void deletecourse(HttpServletRequest request,HttpServletResponse response){
		String username = request.getParameter("username");
		int courseinfid = Integer.parseInt(request.getParameter("courseinfid"));
		AddCourseDao dao = new AddCourseImp();
		String device = request.getParameter("device");
		String result = dao.deletecourse(username, courseinfid);
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(device != null){
			out.print(result);
			out.flush();
			out.close();
		}else{
			json.put("result", result);
			out.print(json);
			out.flush();
			out.close();
		}
	}
	
}
