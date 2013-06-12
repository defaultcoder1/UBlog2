package Models;

public class Readings {
	private String content;
	private String id;
	private Author author;
	private String date_time;
	public Readings(String id,String content,String date_time,String authorId,String name,
			String lname,String image){
		this.id = id;
		this.date_time = date_time;
		this.content = content;
		this.author = new Author(authorId, name, lname, image);
	}
	public String getDate(){
		return this.date_time;
	}
	public String getContent(){
		return this.content;
	}
	public String getId(){
		return this.id;
	}
	public String getAuthorId(){
		return this.author.getId();
	}
	public String ggetAuthorName(){
		return this.author.getName();
	}
	public String getAuthorLastName(){
		return this.author.getLastName();
	}
	public String getAuthorImage(){
		return this.author.getImage();
	}
}
