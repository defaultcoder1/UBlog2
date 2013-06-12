package DB;

import com.mysql.jdbc.Connection;

import Controllers.DBConnector;
import Controllers.UserController;

public class test {

	public static void main(String[] args) {
		UserController uModel = new UserController((Connection) new DBConnector().getConnection());
		System.out.println(uModel.getUserBlogs("18").get(0).getLikes().get(0).getAuthorId());
	}
}