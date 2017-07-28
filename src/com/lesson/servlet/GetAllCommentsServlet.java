package com.lesson.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lesson.bean.Comments;
import com.lesson.dao.GetAllCommentsDao;
import com.lesson.daoimp.GetAllCommentsDaoImp;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetAllCommentsServlet
 */
@WebServlet("/GetAllCommentsServlet")
public class GetAllCommentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllCommentsServlet() {
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
		String type = request.getParameter("type");
		if(type.trim().equals("praise")){
			int id = Integer.parseInt(request.getParameter("id"));
			GetAllCommentsDao dao = new GetAllCommentsDaoImp();
			dao.upDateAllComments(id);
			PrintWriter out = response.getWriter();
			out.print("success");
			out.flush();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("json/application;charset=utf-8");
		JSONArray jsonarray = new JSONArray();
		GetAllCommentsDao dao = new GetAllCommentsDaoImp();
		int courseinfid = Integer.parseInt(request.getParameter("courseinfid"));
		List<Comments> comments = dao.getAllComments(courseinfid);
		for(Comments comment:comments){
			JSONObject json = new JSONObject();
			json.put("id", comment.getId());
			json.put("sender", comment.getSender());
			json.put("content", comment.getContent());
			json.put("sendertime", comment.getSendtime());
			json.put("praisenum", comment.getPraisenum());
			System.out.println(comment.getPraisenum());
			json.put("sendernickname", comment.getSenderNickName());
			json.put("senderheadimage", comment.getSenderheadImage());
			jsonarray.put(json);
		}
		PrintWriter out = response.getWriter();
		if(comments == null|| comments.size() == 0){
			out.print("null");
			out.flush();
		}else{
			out.print(jsonarray.toString());
			out.flush();
		}
	}

}
