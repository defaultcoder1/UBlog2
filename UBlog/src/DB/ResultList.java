package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.ResultSetMetaData;

public class ResultList {

	private ArrayList<HashMap<String, String>> rows;
	
	public ResultList(ResultSet rs) {
		this.rows = new ArrayList<HashMap<String, String>>();
		this.parseResultSet(rs);
	}
	
	public int size() {
		return this.rows.size();
	}
	
	public Row get(int row) {
		if(row < 0 || row >= this.size()) return null;
		return new Row(this.rows.get(row));
	}
	
	public Row first() {
		return this.get(0);
	}
	
	public Row last() {
		return this.get(this.size() - 1);
	}
	
	private void parseResultSet(ResultSet rs) {
		try {
			ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
			int nCols = md.getColumnCount();
			while(rs.next()) {
				HashMap<String, String> row = new HashMap<String, String>();
				for(int i=0; i<nCols; i++) {
					row.put(md.getColumnName(i+1), rs.getString(i+1));
				}
				this.rows.add(row);
			}
		} catch (SQLException e) {e.printStackTrace();}
	}
}
