package Controllers;

import java.util.HashMap;

import DB.Condition;
import DB.DB;

import com.mysql.jdbc.Connection;

public class SubscribeController {
	private Connection con;
	public SubscribeController(Connection con){
		this.con = con;
	}
	public void addSubscriber(String who,String towho){
    	DB db = new DB(con, "ublog");
    	HashMap<String, String> m = new HashMap<String,String>();
		m.put("Subscriber_ID",who);
		m.put("Author_ID", towho);
		db.insert("subscribe", m,null);
    }
	public void reomveSubscribe(String who,String towho){
		DB db = new DB(this.con, "ublog");
		Condition con = new Condition(true);
		con.add("Subscriber_ID", who);
		con.add("Author_ID", towho);
		db.where(con);
		db.delete("subscribe");
	}
}
