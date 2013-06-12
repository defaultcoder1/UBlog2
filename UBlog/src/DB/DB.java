/*
|--------------------------------------------------------------------------
|	DB Class:
|
|	Database Active Records
|--------------------------------------------------------------------------
*/

package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import com.mysql.jdbc.PreparedStatement;

public class DB {

	private Connection con;								//	Database Connection
	private String Database;							//	Actual Database Name
	private String tmp_select = "*";					//	Temporarily Selected Rows
	private String tmp_order = null;					//	Temporarily Selected Order
	private Condition tmp_where = null;					//	Temporary Condition
	private String tmp_join = null;						// Temporary Table To Join With
	private String tmp_join_left = null;				// Join Left Hand Operator	
	private String tmp_join_right = null;				// Join Right Hand Operator
	
	/*
	|--------------------------------------------------------------------------
	|	Constructor:
	|
	|	Parameters: |Connection|String|
	|--------------------------------------------------------------------------
	*/
	public DB(Connection con, String Database) {
		this.con = con;
		this.Database = Database;
	}
	
	/*
	|--------------------------------------------------------------------------
	|	select (Public):
	|
	|	Parameters: |String|
	|--------------------------------------------------------------------------
	*/
	public void select(String tmp_select) {
		this.tmp_select = tmp_select;
	}
	
	/*
	|--------------------------------------------------------------------------
	|	where (Public):
	|
	|	Parameters: |Condition|
	|--------------------------------------------------------------------------
	*/
	public void where(Condition tmp_where) {
		this.tmp_where = tmp_where;
	}
	
	/*
	|--------------------------------------------------------------------------
	|	order (Public):
	|
	|	Parameters: |String|
	|--------------------------------------------------------------------------
	*/
	public void order(String tmp_order) {
		this.tmp_order = tmp_order;
	}
	
	/*
	|--------------------------------------------------------------------------
	|	join (Public):
	|
	|	Parameters: |String|String|String|
	|--------------------------------------------------------------------------
	*/
	public void join(String table, String left, String right) {
		this.tmp_join = table;
		this.tmp_join_left = left;
		this.tmp_join_right = right;
	}
	
	/*
	|--------------------------------------------------------------------------
	|	get (Public):
	|
	|	Parameters: 	|String|
	|	Return Value:	|ArrayList<HashMap<String, String>>|
	|--------------------------------------------------------------------------
	*/
	public ResultList get(String table) {
		return this.get(table, null, null);
	}
		
	/*
	|--------------------------------------------------------------------------
	|	get (Public):
	|
	|	Parameters: |String|String|String|
	|	Return Value:	|ArrayList<HashMap<String, String>>|
	|--------------------------------------------------------------------------
	*/
	public ResultList get(String table, String offset, String limit) {
		ResultSet rs = null;
		String query = "SELECT " + this.tmp_select + " FROM " + this.Database + "." + table;
		
		if(this.tmp_join != null && this.tmp_join_left != null && this.tmp_join_right != null) {
			query += " INNER JOIN " + this.Database + "." + this.tmp_join;
			query += " ON " + table + "." + this.tmp_join_left + " = " + this.tmp_join + "." + this.tmp_join_right;
		}
		
		if(this.tmp_where != null && this.tmp_where.size() > 0)
			query += this.tmp_where.getAlias();
		
		if(this.tmp_order != null) query += " ORDER BY " + this.tmp_order;
		if(offset != null) query += " LIMIT " + offset;
		if(offset != null && limit != null) query += ", " + limit;
		query += ";";
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.con.prepareStatement(query);
			if(this.tmp_where != null && this.tmp_where.size() > 0) {
				for(int i=0; this.tmp_where.hasNext(); i++)
					stmt.setString(i+1, this.tmp_where.next());
			}
			rs = stmt.executeQuery();
		} catch (SQLException e) {e.printStackTrace();}
		
		reset();
		
		return new ResultList(rs);
	}
	
	/*
	|--------------------------------------------------------------------------
	|	insert (Public):
	|
	|	Parameters: 	|String|HashMap|
	|	Return Value:	|int|
	|--------------------------------------------------------------------------
	*/
	public int insert(String table, HashMap<String, String> map, CString genKey) {
		int result = 0;
		String query = "INSERT INTO " + this.Database + "." + table;
		String columns = "(";
		String unknown_values = "(";
		ArrayList<String> values = new ArrayList<String>();
		
		for(String key: map.keySet()) {
			columns += key + ", ";
			unknown_values += "?, ";
			values.add(map.get(key));
		}
		columns = columns.substring(0, columns.length()-2) + ") VALUES";
		unknown_values = unknown_values.substring(0, unknown_values.length()-2) + ");";
		query += columns + unknown_values;
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			for(int i=0; i<values.size(); i++)
				stmt.setString(i+1, values.get(i));
			result = stmt.executeUpdate();
			if(genKey != null) {
				ResultSet genKeys = stmt.getGeneratedKeys();
				if(genKeys.next()) genKey.changeTo(""+genKeys.getLong(1));
			}
		} catch (SQLException e) {e.printStackTrace();}
		
		reset();
		
		return result;
	}
	
	/*
	|--------------------------------------------------------------------------
	|	update (Public):
	|
	|	Parameters: 	|String|HashMap|
	|	Return Value:	|int|
	|--------------------------------------------------------------------------
	*/
	public int update(String table, HashMap<String, String> map) {
		int result = 1;
		String query = "UPDATE " + this.Database + "." + table + " SET ";
		ArrayList<String> values = new ArrayList<String>();
		
		for(String key: map.keySet()) {
			query += key + " = ?, ";
			values.add(map.get(key));
		}
		query = query.substring(0, query.length()-2);
		
		if(this.tmp_where != null && this.tmp_where.size() > 0) {
			query += this.tmp_where.getAlias();
			values.addAll(this.tmp_where.getValues());
		}
		query += ";";
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.con.prepareStatement(query);
			for(int i=0; i<values.size(); i++)
				stmt.setString(i+1, values.get(i));
			result = stmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
		
		reset();
		
		return result;
	}

	/*
	|--------------------------------------------------------------------------
	|	delete (Public):
	|
	|	Parameters: 	|String|
	|	Return Value:	|int|
	|--------------------------------------------------------------------------
	*/
	public int delete(String table) {
		int result = 1;
		String query = "DELETE FROM " + this.Database + "." + table;
		
		if(this.tmp_where != null && this.tmp_where.size() > 0) {
			query += this.tmp_where.getAlias();
		}
		query += ";";
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.con.prepareStatement(query);
			if(this.tmp_where != null && this.tmp_where.size() > 0) {
				for(int i=0; this.tmp_where.hasNext(); i++)
					stmt.setString(i+1, this.tmp_where.next());
			}
			result = stmt.executeUpdate();			
		} catch (SQLException e) {e.printStackTrace();}
		
		reset();
		
		return result;
	}

	
	/*
	|--------------------------------------------------------------------------
	|	reset (Private):
	|--------------------------------------------------------------------------
	*/
	private void reset() {
		this.tmp_select = "*";
		this.tmp_order = null;
		this.tmp_where = null;
		this.tmp_join = null;
		this.tmp_join_left = null;
		this.tmp_join_right = null;
	}
}