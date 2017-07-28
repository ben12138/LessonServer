package com.lesson.servlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import net.sf.json.JSONObject;

import com.lesson.bean.User;
import com.lesson.bean.UserInf;
import com.lesson.dao.GetUserInfDao;
import com.lesson.daoimp.GetUserInfDaoImp;
import com.lesson.util.SendMail;

/**
 * Servlet implementation class FindPasswordServlet
 */
@WebServlet("/FindPasswordServlet")
public class FindPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPasswordServlet() {
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
		String email = request.getParameter("email");
		String type = request.getParameter("type");
		if(type.equals("getYanzhengma")){
			SendMail mail = new SendMail();
			mail.send(email);
			PrintWriter out = response.getWriter();
			out.print(mail.getContent());
			out.flush();
			out.close();
		}else if(type.equals("getUserInf")){
			GetUserInfDao dao = new GetUserInfDaoImp();
			UserInf user = dao.getUserInf(email);
			User u = dao.getUser(email);
			if(u!=null){
				SendMail mail = new SendMail();
				mail.setContent("您的密码是："+u.getPassword()+"请妥善保存");
				mail.setSubject("您的上课啦的账号密码，请妥善保存");
				mail.send(email);
				JSONObject json = new JSONObject();
				json.put("username", user.getUsername());
				json.put("nickname", user.getNickname());
				json.put("headimage", user.getHeadimagePath());
				json.put("email", user.getEmail());
				json.put("sex", user.getSex());
				json.put("school", user.getSchool());
				json.put("birthday", user.getBirthday());
				json.put("introduction", user.getIntroduction());
				System.out.println(json.toString());
				PrintWriter out = response.getWriter();
				out.print(json.toString());
				out.flush();
				out.close();
			}
			
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
