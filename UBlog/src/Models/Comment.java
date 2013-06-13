package Models;

import java.util.ArrayList;

public class Comment extends Readings{
	
	private ArrayList<Like> likes;
	private String blogId;
	
	public Comment(String id,String userId, String content, String blogId, String date_time, 
			String authorname,String authorlname,String authorimage,ArrayList<Like> likes){
		super(id, content,date_time, userId, authorname, authorlname,authorimage);
		this.blogId = blogId;
		this.likes = new ArrayList<Like>();
		this.likes.addAll(likes);
	}
	public String getBlogId(){
		return this.blogId;
	}
	public ArrayList<Like> getLikes() {
		return this.likes;
	}
}