package Controllers;

import java.sql.Connection;
import java.util.HashMap;

import DB.Condition;
import DB.DB;
import DB.CString;
import Models.User;

public class RegisterController {
	
	private Connection con;
	
	public RegisterController(Connection con) {
		this.con = con;
	}
	
	public String addNewUser(String rfname,String rlname,
			String remail,String rpassword,
			String image,String rbname) {
		DB db = new DB(this.con, "ublog");
		Condition cnd = new Condition(true);
		cnd.add("EMail",remail);
		db.where(cnd);
		if(db.get("user")==null) return "-1";
		HashMap<String, String> values = new HashMap<String, String>();
		values.put("FName",rfname);
		values.put("LName", rlname);
		values.put("EMail", remail);
		values.put("Password", rpassword);
		values.put("Image", image);
		CString lastID = new CString("");
		db.insert("user", values, lastID);
		System.out.println(lastID);
		return lastID.toString();
	}
	public String addOldUser(String id,String rfname,String rlname,
			String remail,String rpassword,
			String image,String rbname) {
		DB db = new DB(this.con, "ublog");
		Condition cnd = new Condition(true);
		cnd.add("EMail",remail);
		db.where(cnd);
		if(db.get("user")==null) return "-1";
		HashMap<String, String> values = new HashMap<String, String>();
		values.put("User_ID", id);
		values.put("FName",rfname);
		values.put("LName", rlname);
		values.put("EMail", remail);
		values.put("Password", rpassword);
		values.put("Blog_Name", rbname);
		values.put("Image", image);
		CString lastID = new CString("");
		db.insert("user", values, lastID);
		System.out.println(lastID);
		return lastID.toString();
	}
}
