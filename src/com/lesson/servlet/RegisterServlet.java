package com.lesson.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

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
import com.lesson.dao.AddUserInfDao;
import com.lesson.dao.GetUserInfDao;
import com.lesson.daoimp.AddUserInfDaoImp;
import com.lesson.daoimp.GetUserInfDaoImp;
import com.lesson.util.SendMail;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		System.out.println(request.getServletContext().getRealPath("/")+"/headImage/m13814545863@163.com.jpg");
		File file = new File(request.getServletContext().getRealPath("/")+"/headImage/m13814545863@163.com.jpg");
		System.out.println(file.exists());
		String type = request.getParameter("type");
		if(type.equals("step1")){
			String email = request.getParameter("email");
			GetUserInfDao dao = new GetUserInfDaoImp();
			if(dao.isRegistered(email)){
				JSONObject json = new JSONObject();
				json.put("yanzhengma", "registered");
				PrintWriter out = response.getWriter();
				out.print(json.toString());
				out.flush();
				out.close();
			}else{
				SendMail mail = new SendMail();
				mail.send(email);
				JSONObject json = new JSONObject();
				json.put("yanzhengma", mail.getContent());
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("json/application;charset=utf-8");
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String birthday = request.getParameter("birthday");
		String sexstr = request.getParameter("sex");
		System.out.println(sexstr);
		int sex = Integer.parseInt(sexstr);
		String school = request.getParameter("school");
		String introduction = request.getParameter("introduction");
		String headimagePath = "http://192.168.191.1:8080/LessonServer/headImage"+email+".png";
		String onlinedevice = request.getParameter("onlinedevice");
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		u.setIsonline(0);//0代表在线，1代表离线
		if(onlinedevice.equals("phone")){
			u.setOnlinedevice1("phone");
		}else if(onlinedevice.equals("pc")){
			u.setOnlinedevice2("pc");
		}else if(onlinedevice.equals("web")){
			u.setOnlinedevice3("web");
		}
		u.setEmail(email);
		UserInf user = new UserInf();
		user.setCourse(null);
		user.setEmail(email);
		user.setHeadimagePath(headimagePath);
		user.setIntroduction(introduction);
		if(nickname == null){
			nickname = email;
		}
		user.setNickname(nickname);
		user.setSchool(school);
		user.setSex(sex);
		user.setUsername(username);
		user.setBirthday(birthday);
		AddUserInfDao dao = new AddUserInfDaoImp();
		if(dao.addUser(u)&&dao.addUserInf(user)){
			response.getOutputStream().print("success");
		}else{
			response.getOutputStream().print("faliure");
		}
		
	}

}
