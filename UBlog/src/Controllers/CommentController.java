package Controllers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import DB.CString;
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
		Condition cond = new Condition(true);
		cond.add("Comment_ID",commentId);
		db.where(cond);
		ResultList crl = db.get("comment");
		db = new DB(this.con, "ublog");
		cond = new Condition(true);
		cond.add("User_ID", crl.first().get("User_ID"));
		db.where(cond);
		ResultList url = db.get("user");
		LikeController lc = new LikeController(this.con);
		Comment c = new Comment(commentId, url.first().get("User_ID"), crl.first().get("Content"), crl.first().get("Article_ID"),crl.first().get("Date"),
		url.first().get("FName"), url.first().get("LName"),url.first().get("Image"), lc.getCommentLikes(commentId));
		return c;
	}


	public boolean deleteComment(String commentId){
		DB db = new DB (this.con, "ublog");
		Condition cond = new Condition(true);
		cond.add("Comment_ID", commentId);
		db.where(cond);
		int result = db.delete("comment");
		return result>0; 
	}
	
	public String addComment(String userId, String articleId, String content){
		DB db = new DB (this.con, "ublog");
		CString commentId = new CString ("");
		HashMap <String, String> m = new HashMap<String, String>();
		m.put("User_ID", userId);
		m.put("Article_ID", articleId);
		m.put("Content", content);
		db.insert("comment", m, commentId);
		return commentId.toString();
	}
}