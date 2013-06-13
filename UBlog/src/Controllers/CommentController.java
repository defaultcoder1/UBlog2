package Controllers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import DB.Condition;
import DB.DB;
import DB.ResultList;
import Models.Comment;

public class CommentController {

	private Connection con;
	
	public CommentController(Connection con) {
		this.con = con;
	}
	
	public Comment getCommentById(String commentId){
		DB db = new DB(this.con, "ublog");
		Condition con = new Condition(true);
		con.add("Comment_ID",commentId);
		db.where(con);
		ResultList crl = db.get("comment");
		db = new DB(this.con, "ublog");
		con = new Condition(true);
		con.add("User_ID", crl.first().get("User_ID"));
		db.where(con);
		ResultList url = db.get("user");
		LikeController lc = new LikeController(this.con);
		Comment c = new Comment(commentId, url.first().get("User_ID"), crl.first().get("Content"), crl.first().get("Article_ID"),crl.first().get("Date"),
		url.first().get("FName"), url.first().get("LName"),url.first().get("Image"), lc.getCommentLikes(commentId));
		return c;
	}
	public void addComment(String articleid,String userid,String content,String date,Connection con){
	    DB db = new DB(con, "ublog");
	    HashMap<String, String> m = new HashMap<String,String>();
		m.put("Article_ID",articleid);
		m.put("User_ID",userid);
		m.put("Content", content);
		m.put("Date", date);
		db.insert("comment", m,null);
	}
	public void deleteComment(String commentId){
		DB db = new DB(this.con, "ublog");
		Condition con = new Condition(true);
		con.add("Comment_ID", commentId);
		db.where(con);
		db.delete("Comment");
		db.delete("like_comment");
		
	}
}
