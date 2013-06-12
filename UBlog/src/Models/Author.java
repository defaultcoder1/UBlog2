package Models;

public class Author extends Visitor{
  private String name;
	private String lname;
	private String image;
	public Author(String id,String name,String lname,String image) {
		super(id);
		this.name = name;
		this.image = image;
		this.lname = lname;
	}
	public String getName(){
		return this.name;
	}
	
	public String getLastName(){
		return this.lname;
	}
	public String getImage(){
		return this.image;
	}
}
