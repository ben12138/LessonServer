package com.lesson.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lesson.bean.Comments;
import com.lesson.dao.GetAllCommentsDao;
import com.lesson.daoimp.GetAllCommentsDaoImp;

/**
 * Servlet implementation class AddAllCommentsServlet
 */
@WebServlet("/AddAllCommentsServlet")
public class AddAllCommentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAllCommentsServlet() {
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
		String sendernickname = request.getParameter("sendernickname");
		String senderheadimage = request.getParameter("senderheadimage");
		String content = request.getParameter("content");
		String sender = request.getParameter("sender");
		int courseinfid = Integer.parseInt(request.getParameter("courseinfid"));
		int praisenum = 0;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String sendtime = sf.format(new Date());
		Comments comment = new Comments();
		comment.setSender(sender);
		comment.setContent(content);
		comment.setPraisenum(praisenum);
		comment.setSenderheadImage(senderheadimage);
		comment.setSenderNickName(sendernickname);
		comment.setSendtime(sendtime);
		comment.setCourseinfid(courseinfid);
		GetAllCommentsDao dao = new GetAllCommentsDaoImp();
		int result = dao.addCommens(comment);
		PrintWriter out = response.getWriter();
		if(result != -1){
			out.print(result);
			out.flush();
		}else{
			out.print("failure");
			out.flush();
		}
	}

}
