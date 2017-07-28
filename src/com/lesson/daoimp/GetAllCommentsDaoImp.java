package com.lesson.daoimp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lesson.bean.Comments;
import com.lesson.dao.GetAllCommentsDao;
import com.lesson.databasehelper.DataBaseHelper;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class GetAllCommentsDaoImp implements GetAllCommentsDao{
	@Override
	public List<Comments> getAllComments(int courseinfid) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		String sql = "select * from comments where courseinfid=? order by id desc";
		List<Comments> comments = new ArrayList<Comments>();
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
			state.setInt(1, courseinfid);
			ResultSet rs = state.executeQuery();
			while (rs.next()) {
				Comments comment = new Comments();
				int id = rs.getInt(1);
				String sender = rs.getString(2);
				String content = rs.getString(3);
				int praisenum = rs.getInt(5);
				String sendernickname = rs.getString(6);
				String senderheadimage = rs.getString(7);
				comment.setId(id);
				comment.setSender(sender);
				comment.setContent(content);
				comment.setPraisenum(praisenum);
				comment.setSendtime(rs.getString(4));
				comment.setSenderNickName(sendernickname);
				comment.setSenderheadImage(senderheadimage);
				comments.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return comments;
	}
	public static void main(String[] args) {
		Comments comments = new Comments();
		comments.setContent("aaa");
		comments.setPraisenum(1);
		comments.setSender("aaa");
		comments.setSenderheadImage("aaa");
		comments.setSenderNickName("aaa");
		comments.setSendtime("aa");
		System.out.println(new GetAllCommentsDaoImp().addCommens(comments));
	}
	@Override
	public void upDateAllComments(int id) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		String sql = "update comments set praisenum = praisenum+1 where id=?";
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql);
			state.setInt(1, id);
			state.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public int addCommens(Comments comment) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseHelper.getConnection();
		String sql = "insert into comments (sender,content,sendtime,praisenum,sendernickname,senderheadimage,courseinfid) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement state = (PreparedStatement) conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			state.setString(1, comment.getSender());
			state.setString(2, comment.getContent());
			state.setString(3, comment.getSendtime());
			state.setInt(4, comment.getPraisenum());
			state.setString(5, comment.getSenderNickName());
			state.setString(6, comment.getSenderheadImage());
			state.setInt(7, comment.getCourseinfid());
			state.executeUpdate();
			ResultSet rs = state.getGeneratedKeys();
			int id = -1;
			while(rs.next()){
				id = rs.getInt(1);
			}
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
}
