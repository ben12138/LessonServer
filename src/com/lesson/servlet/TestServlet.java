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

import com.lesson.bean.Test;
import com.lesson.dao.TestDao;
import com.lesson.dao.TestDaoImp;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
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
		int courseid = Integer.parseInt(request.getParameter("courseid"));
		TestDao dao = new TestDaoImp();
		List<Test> tests = dao.getTest(courseid);
		if(tests.size() == 0){
			out.print("null");
			out.flush();
			out.close();
		}else{
			JSONArray jsonaray = new JSONArray();
			for(Test test:tests){
				JSONObject json = new JSONObject();
				json.put("courseid", test.getCourseid()+"");
				json.put("question", test.getQuestion());
				json.put("choiceA", test.getChoiceA());
				json.put("choiceB", test.getChoiceB());
				json.put("choiceC", test.getChoiceC());
				json.put("choiceD", test.getChoiceD());
				json.put("answer", test.getAnswer());
				jsonaray.put(json);
			}
			out.print(jsonaray.toString());
			out.flush();
			out.close();
		}
	}

}
