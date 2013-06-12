package DB;

import java.util.ArrayList;

public class Condition {
	
	private ArrayList<String> values;
	private String alias;
	private String condType;

	public Condition(boolean typeAND) {
		this.values = new ArrayList<String>();
		this.alias = " WHERE ";
		if(typeAND) this.condType = " AND ";
		else this.condType = " OR ";
	}
	
	public void add(String colName, String value) {
		colName = colName.trim();
		value = value.trim();
		
		String next = "";
		if(this.values.size() > 0) next = this.condType;
		this.alias += next + colName + " = ?";
		
		this.values.add(value);
	}
	
	public int size() {
		return this.values.size();
	}
	
	public ArrayList<String> getValues() {
		return this.values;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public boolean hasNext() {
		return this.values.size() > 0;
	}
	
	public String next() {
		String value = this.values.get(0);
		this.values.remove(0);
		return value;
	}
}
