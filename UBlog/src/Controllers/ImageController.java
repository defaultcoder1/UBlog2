package Controllers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import DB.Condition;
import DB.DB;
import DB.ResultList;

public class ImageController {
	
	private Connection con;
	
	public ImageController(Connection con) {
		this.con = con;
	}
	
	public ArrayList<String> getImagesByUserID(String userID) {
		DB db = new DB(this.con, "ublog");
		db.select("Image");
		Condition cnd = new Condition(true);
		cnd.add("User_ID", userID);
		db.where(cnd);
		ResultList rl = db.get("user_images");
		ArrayList<String> images = new ArrayList<String>();
		
		for(int i=0; i<rl.size(); i++) {
			images.add(rl.get(i).get("Image"));
		}
		
		return images;
	}
	
	public String getNextName(String userID) {
		DB db = new DB(this.con, "ublog");
		Condition cnd = new Condition(true);
		cnd.add("User_ID", userID);
		db.select("Image");
		db.where(cnd);
		ResultList rl = db.get("user_images");
		return "" + rl.size();
	}
	
	public void addImage(String userID, String src) {
		DB db = new DB(this.con, "ublog");
		HashMap<String, String> values = new HashMap<String, String>();
		values.put("User_ID", userID);
		values.put("Image", src);
		db.insert("user_images", values, null);
	}
	
}