

package Models;

import java.util.ArrayList;

public class Blog extends Readings{
	private String image;
	private String title;
	private ArrayList<Like> likes;
	private ArrayList<Comment> comments;
	private ArrayList<String> tags;
	private String category;
	public Blog(String id, String userId, String image, String title, String content, 
		String date,ArrayList<Like> likes, ArrayList<Comment> comments,String aname,
		String alname,String aimage,ArrayList<String> tags,String category){
		super(id,content,date,userId,aname,alname,aimage);
		this.tags = tags;
		this.image = image;
		this.title = title;
		this.likes = likes;
		this.comments = comments;
		this.category = category;
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
	public int getTagsNum(){
		if(this.tags == null)
			return 0;
		else
			return this.tags.size();
	}
	public String getTag(int index){
		return this.tags.get(index);
	}
	public String getCategory(){
		return this.category;
	}
}
