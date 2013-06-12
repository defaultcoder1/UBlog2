package Controllers;

import java.sql.Connection;
import DB.Condition;
import DB.DB;
import DB.ResultList;

public class LoginController {
	
	private Connection con;
	
	public LoginController(Connection con) {
		this.con = con;
	}
	
	public String checkInput(String email,String password) {
		DB db = new DB(this.con, "ublog");
		Condition cnd = new Condition(true);
		cnd.add("EMail", email);
		cnd.add("Password", password);
		db.where(cnd);
		ResultList rl = db.get("user");
		if(rl.size() > 0) return rl.first().get("User_ID");
		return "-1";
	}
}
