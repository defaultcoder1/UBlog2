package Models;

public class Like {
	
	private String blogId;
	private Author author;
	
	public Like(String userId,String blogId,String authorName,String authorLastName,String authorImage){
		this.author = new Author(userId,authorName,authorLastName,authorImage);
		this.blogId = blogId;
	}
	
	public String getAuthorId(){
		return this.author.getId();
	}
	public String getBlogId(){
		return this.blogId;
	}
	public String getAuthorImage(){
		return this.author.getImage();
	}
	public String getAuthorName(){
		return this.author.getName();
	}
	public String getAuthorLastName(){
		return author.getLastName();
	}
}
