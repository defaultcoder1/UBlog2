package Controllers;

import java.sql.Connection;
import java.util.ArrayList;

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
}
