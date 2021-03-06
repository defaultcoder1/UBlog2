package Controllers;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.tomcat.dbcp.dbcp.DbcpException;

import DB.Condition;
import DB.DB;
import DB.ResultList;
import DB.Row;
import Models.Author;
import Models.Blog;
import Models.User;

import com.mysql.jdbc.Connection;

public class UserController {

	private Connection con;

	public UserController(Connection connection) {
		this.con = connection;
	}
	public int getUserArticlesNum(String userId){
		DB db = new DB(this.con, "ublog");
		Condition c = new Condition(true);
		c.add("User_ID", userId);
		db.where(c);
		return db.get("article").size();
	}
	public Author getAuthorById(String authorId){
		Author a;
		DB db = new DB(this.con,"ublog");
		Condition con = new Condition(true);
		con.add("User_ID", authorId);
		db.where(con);
		ResultList rl = db.get("user");
		a = new Author(authorId, rl.first().get("FName"), rl.first().get("LName"),
				rl.first().get("Image"));
		return a;
	}
	public User getUserById(String userId) {
		DB db = new DB(this.con, "ublog");
		Condition cnd = new Condition(true);
		cnd.add("User_ID", userId);
		db.where(cnd);
		ResultList rl = db.get("user");
		if (rl.size() == 0)
			return null;
		Row userData = rl.first();
		User user = new User(userData.get("User_ID"), userData.get("FName"),
				userData.get("LName"), userData.get("EMail"),
				userData.get("Password"), userData.get("Image"),
				userData.get("Blog_Name"));
		ArrayList<String> arr = this.getSubscribers(userId);
		for (int i = 0; i < arr.size(); i++) {
			user.addSubscriber(arr.get(i));
		}
		arr = this.getSusbscribed(userId);
		for (int i = 0; i < arr.size(); i++) {
			user.addSubscription(arr.get(i));
		}
		return user;
	}

	public ArrayList<Blog> getUserBlogs(String userId) {
		BlogController bc = new BlogController(this.con);
		DB db = new DB(this.con, "ublog");
		Condition cnd = new Condition(true);
		cnd.add("User_ID", userId);
		db.where(cnd);
		db.order("Date DESC");
		ResultList rl = db.get("article");
		ArrayList<Blog> userBlogs = new ArrayList<Blog>();
		for (int i = 0; i < rl.size(); i++) {
			Row row = rl.get(i);
			Blog b = bc.getBlogById(row.get("Article_ID"));
			userBlogs.add(b);
		}
		return userBlogs;
	}

	public ArrayList<String> getSubscribers(String userId) {
		DB db = new DB(this.con, "ublog");
		db.select("Subscriber_ID");
		Condition cnd = new Condition(true);
		cnd.add("Author_ID", userId);
		db.where(cnd);
		ResultList rl = db.get("subscribe");
		ArrayList<String> subscribers = new ArrayList<String>();
		for (int i = 0; i < rl.size(); i++) {
			subscribers.add(rl.get(i).get("Subscriber_ID"));
		}
		return subscribers;
	}

	public ArrayList<String> getSusbscribed(String userId) {
		DB db = new DB(this.con, "ublog");
		db.select("Author_ID");
		Condition cnd = new Condition(true);
		cnd.add("Subscriber_ID", userId);
		db.where(cnd);
		ResultList rl = db.get("subscribe");
		ArrayList<String> subscribed = new ArrayList<String>();
		for (int i = 0; i < rl.size(); i++) {
			subscribed.add(rl.get(i).get("Author_ID"));
		}
		return subscribed;
	}

	public void deleteUser(String userId) {
		DB db = new DB(this.con, "ublog");
		HashMap<String, String> m = new HashMap <String, String>();
		Condition con  = new Condition(true);
		con.add("User_ID", userId);
		db.where(con);
		db.delete("User");
	}

}
