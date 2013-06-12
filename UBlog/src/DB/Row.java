package DB;

import java.util.HashMap;

public class Row {

	private HashMap<String, String> row;
	
	public Row(HashMap<String, String> row) {
		this.row = row;
	}
	
	public int getColumnSize() {
		return this.row.size();
	}
	
	public String get(String column) {
		return this.row.get(column);
	}
}
