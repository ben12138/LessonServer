package com.lesson.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.net.ServerSocketFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.lesson.bean.User;
import com.lesson.bean.UserInf;
import com.lesson.dao.GetUserInfDao;
import com.lesson.dao.SearchUserDao;
import com.lesson.daoimp.GetUserInfDaoImp;
import com.lesson.daoimp.SearchUserDaoImp;

/**
 * Servlet implementation class servlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("json/application;charset=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String onlinedevice = request.getParameter("device");
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		u.setIsonline(0);
//		if(onlinedevice.equals("phone")){
//			u.setOnlinedevice1("phone");
//		}else if(onlinedevice.equals("pc")){
//			u.setOnlinedevice2("pc");
//		}else if(onlinedevice.equals("web")){
//			u.setOnlinedevice3("web");
//		}
		u.setOnlinedevice3("web");
		SearchUserDao dao = new SearchUserDaoImp();
		int type = dao.searchUser(u);
		JSONObject json = null;
		PrintWriter out = null;
		switch(type){
		case SearchUserDao.SUCCESS:
			json = new JSONObject();
			out = response.getWriter();
			//dao.upDateUser(u);
			GetUserInfDao dao1 = new GetUserInfDaoImp();
			UserInf user = dao1.getUserInf(username);
			json.put("result", "success");
			json.put("username", user.getUsername());
			json.put("nickname", user.getNickname());
			json.put("headimage", user.getHeadimagePath());
			json.put("email", user.getEmail());
			json.put("sex", user.getSex());
			json.put("school", user.getSchool());
			json.put("birthday", user.getBirthday());
			json.put("introduction", user.getIntroduction());
			System.out.println(json.toString());
//			out.print(json.toString());
//			out.flush();
//			out.close();
			break;
		case SearchUserDao.USERNAME_NOT_EXITS:
			json = new JSONObject();
			out = response.getWriter();
			json.put("result", "username_not_exist");
//			out.print(json.toString());
//			out.flush();
//			out.close();
			break;
		case SearchUserDao.PASSWORD_ERROR:
			json = new JSONObject();
			out = response.getWriter();
			json.put("result", "password_error");
//			out.print(json.toString());
//			out.flush();
//			out.close();
			break;
		}
		if(onlinedevice != null && onlinedevice.equals("phone")){
			out.print(json.toString());
			out.flush();
			out.close();
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("jjj", json);
			response.sendRedirect("main.jsp");
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

