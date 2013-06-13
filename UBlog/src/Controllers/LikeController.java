package Controllers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.tomcat.dbcp.dbcp.DbcpException;

import DB.Condition;
import DB.DB;
import DB.ResultList;
import Models.Like;

public class LikeController {

	private Connection con;
	
	public LikeController(Connection con) {
		this.con = con;
	}
	
	public ArrayList<Like> getArticleLikes(String blogId){
		DB db = new DB(this.con, "ublog");
		Condition con = new Condition(true);
		con.add("Article_ID",blogId);
		db.where(con);
		ResultList rl = db.get("like_article");	
		ArrayList<Like> arr = new ArrayList<Like>();
		for(int i=0;i<rl.size();i++){
			con = new Condition(true);
			con.add("User_ID", rl.get(i).get("User_ID"));
			db.where(con);
			ResultList url = db.get("user");
			Like l = new Like(url.first().get("User_ID"), blogId, url.first().get("FName"),
				url.first().get("LName"),url.first().get("Image"));
			arr.add(l);
		}
		return arr;
	}
	public ArrayList<Like> getCommentLikes(String commentId){
		DB db = new DB(this.con, "ublog");
		Condition con = new Condition(true);
		con.add("Comment_ID",commentId);
		db.where(con);
		ResultList rl = db.get("like_comment");	
		ArrayList<Like> arr = new ArrayList<Like>();
		for(int i=0;i<rl.size();i++){
			con = new Condition(true);
			con.add("User_ID", rl.get(i).get("User_ID"));
			db.where(con);
			ResultList url = db.get("user");
			Like l = new Like(url.first().get("User_ID"),commentId, url.first().get("FName"),
				url.first().get("LName"),url.first().get("Image"));
			arr.add(l);
		}
		return arr;
	}
	
	public void unlikeComment(String commentId, String userId){
		DB db = new DB(this.con, "ublog");
		Condition con = new Condition(true);
		con.add("Comment_ID", commentId);
		con.add("User_ID", userId);
		db.where(con);
		db.delete("like_comment");
	}
	public void unlikeArticle(String articleId, String userId){
		DB db = new DB(this.con, "ublog");
		Condition con = new Condition(true);
		con.add("Article_ID", articleId);
		con.add("User_ID", userId);
		db.where(con);
		db.delete("like_article");
	}
	  public void addLikeToArticle(String userid,String blogID,Connection con){
	    DB db = new DB(con, "ublog");
	    HashMap<String, String> m = new HashMap<String,String>();
		m.put("User_ID",userid);
		m.put("Article_ID", blogID);
		db.insert("like_article", m,null);
	 }
	 public void addLikeToComment(String userid,String commentId,Connection con){
	    DB db = new DB(con, "ublog");
	    HashMap<String, String> m = new HashMap<String,String>();
		m.put("User_ID",userid);
		m.put("Comment_ID", commentId);
		db.insert("like_comment", m,null);
	 }
	 public void deleteLikesOfArticle(String blogId){
		 DB db = new DB(this.con, "ublog");
		 Condition c = new Condition(true);
		 c.add("Article_ID", blogId);
		 db.where(c);
		 db.delete("like_article");
	 }
	 public void deleteLikesOfComment(String commentId){
		 DB db = new DB(this.con, "ublog");
		 Condition c = new Condition(true);
		 c.add("Comment_ID", commentId);
		 db.where(c);
		 db.delete("like_comment");
	 }
}
