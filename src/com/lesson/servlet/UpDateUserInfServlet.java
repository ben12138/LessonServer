package com.lesson.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lesson.bean.UserInf;
import com.lesson.dao.GetUserInfDao;
import com.lesson.daoimp.GetUserInfDaoImp;

/**
 * Servlet implementation class UpDateUserInfServlet
 */
@WebServlet("/UpDateUserInfServlet")
public class UpDateUserInfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpDateUserInfServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("json/application;charset=utf-8");
		UserInf user = new UserInf();
		GetUserInfDao dao = new GetUserInfDaoImp();
		String nickname = request.getParameter("nickname");
		int sex = Integer.parseInt(request.getParameter("sex").toString());
		String birthday = request.getParameter("birthday");
		String school = request.getParameter("school");
		String introduction = request.getParameter("introduction");
		String email = request.getParameter("email");
		user.setBirthday(birthday);
		user.setSex(sex);
		user.setSchool(school);
		user.setIntroduction(introduction);
		user.setNickname(nickname);
		user.setEmail(email);
		dao.upDateUserInf(user);
		PrintWriter out = response.getWriter();
		out.print("success");
		out.flush();
	}

}
