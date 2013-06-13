package Controllers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import DB.CString;
import DB.Condition;
import DB.DB;
import DB.ResultList;
import DB.Row;
import Models.Like;
import Models.Blog;
import Models.Comment;

import com.mysql.jdbc.Connection;

public class BlogController {
	
	private Connection con;
	
	public BlogController(Connection con){
		this.con = con;
	}
	
	private ArrayList<String> getTags(String blogId){
		ArrayList<String> arr = new ArrayList<String>();
		DB db = new DB(this.con, "ublog");
		Condition c = new Condition(true);
		c.add("Article_ID", blogId);
		db.where(c);
		ResultList rl = db.get("tag_article");
		for(int i=0;i<rl.size();i++){
			arr.add(rl.get(i).get("Tag"));
		}
		return arr;
	}
	public Blog getBlogById(String blogId){
		DB db = new DB(this.con, "ublog");
		Condition cnd = new Condition(true);
		cnd.add("Article_ID", blogId);
		db.where(cnd);		
		ResultList rl = db.get("article");
		if(rl.size() == 0) return null;
		Row article = rl.first();
		LikeController alModel = new LikeController(this.con);
		cnd = new Condition(true);
		cnd.add("User_ID",article.get("User_ID"));
		db.where(cnd);
		rl = db.get("user");
		Row author = rl.first();
		ArrayList<String> arr = this.getTags(blogId);
		Blog newBlog = new Blog(blogId, article.get("User_ID"), article.get("Image"), 
	article.get("Title"), article.get("Content"), article.get("Date"),
	alModel.getArticleLikes(blogId),this.getBlogComments(blogId),author.get("FName"),
	author.get("LName"),author.get("Image"),arr,article.get("Category"));		
		return newBlog;
	}
	
	private void addTags(String articleId, ArrayList<String> tags){
		DB db = new DB(this.con, "ublog");
		for (int i=0; i<tags.size(); i++){
			HashMap<String, String> m = new HashMap<String, String>();
			m.put("Tag", tags.get(i));
			m.put("Article_ID", articleId);
			db.insert("tag_article", m, null);
		}
	}
	public String addArticle(String image,String title,String content,String userid,
			String category,ArrayList<String> tags){
		HashMap<String, String> m = new HashMap<String,String>();
		m.put("Image", image);
		m.put("Title", title);
		m.put("Content", content);
		m.put("User_ID", userid);
		m.put("Category", category);
		CString article_id = new CString("");
		DB db = new DB(this.con, "ublog");
		db.insert("article", m,article_id);
		addTags(article_id.toString(), tags);
		return article_id.toString();
	}
	
	private ArrayList<Comment> getBlogComments(String blog_id){
		DB db = new DB(this.con, "ublog");
		Condition con = new Condition(true);
		con.add("Article_ID", blog_id);
		db.where(con);
		CommentController cmc= new CommentController(this.con);
		ResultList rl = db.get("comment");
		ArrayList<Comment> arr = new ArrayList<Comment>();
		for(int i=0;i<rl.size();i++){
			Comment c = cmc.getCommentById(rl.get(i).get("Comment_ID"));
			arr.add(c);
		}
		return arr;
	}
	public ArrayList<Blog> getBlogs(int offset,int num){
		DB db = new DB(this.con, "ublog");	
		ArrayList<Blog> arr = new ArrayList<Blog>();
		db.order("Date DESC");
		ResultList l = db.get("article",""+offset,""+num);
		for(int i=0;i<l.size();i++){
			arr.add(this.getBlogById(l.get(i).get("Article_ID")));
		}
		return arr;
	}
	
	public void deleteArticle(String articleId){
		DB db = new DB(this.con, "ublog");
		Condition con = new Condition(true);
		con.add("Article_ID", articleId);
		db.where(con);
		db.delete("article");
		LikeController lc = new LikeController(this.con);
		lc.deleteLikesOfArticle(articleId);
	}
	
}
