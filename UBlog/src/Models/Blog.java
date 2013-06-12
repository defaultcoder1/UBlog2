

package Models;

import java.util.ArrayList;

public class Blog extends Readings{
	private String image;
	private String title;
	private ArrayList<Like> likes;
	private ArrayList<Comment> comments;
	public Blog(String id, String userId, String image, String title, String content, String date,
				ArrayList<Like> likes, ArrayList<Comment> comments,String aname,String alname,String aimage){
		super(id,content,date,userId,null,null,null);
		this.image = image;
		this.title = title;
		this.likes = likes;
		this.comments = comments;
	}
	
	public String getImage(){
		return this.image;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public int getLikeNum(){
		return this.likes.size();
	}
	
	public ArrayList<Like> getLikes(){
		return this.likes;
	}
	
	public int getCommentNum(){
		return this.comments.size();
	}
	
	public ArrayList<Comment> getComments(){
		return this.comments;
	}
}
