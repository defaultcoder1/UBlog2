package Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	
	static String account = "root";
	static String password = "gigipataraia";
	static String server = "localhost";
	static String database = "ublog";
	private Connection con;
	
	public DBConnector(){
		connect();
	}
	
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection
			( "jdbc:mysql://" + server, account ,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return con;
	}
}