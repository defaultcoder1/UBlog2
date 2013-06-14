package Models;

import java.util.ArrayList;

import sun.nio.cs.US_ASCII;

public class User extends Visitor{
	private String name;
	private String lastName;
	private String passWord;
	private String email;
	private String image;
	private String blogName;
	private ArrayList<String> subscriptions;
	private ArrayList<String> subscribers;
	public User(String id,String name,String lastName,
		String email,String passWord,String image,String blogName){
		super(id);
		this.name = name;
		this.lastName = lastName;
		this.passWord = passWord;
		this.email = email;
		this.image = image;
		this.blogName = blogName;
		this.subscribers= new ArrayList<String>();
		this.subscriptions = new ArrayList<String>();
	}
	public void addSubscriber(String id){
		if(id==super.getId()) return;
		this.subscribers.add(id);
	}
	public void addSubscription(String id){
		if(id==super.getId()) return;
		this.subscriptions.add(id);
	}
	public int getSubscribersNum(){
		return this.subscribers.size();
	}
	public String getSubscriber(int index){
		return this.subscribers.get(index);
	}
	public int getSubscriptionsNum(){
		return this.subscriptions.size();
	}
	public String getSubscription(int index){
		return this.subscriptions.get(index);
	}
	public String getName(){
		return this.name;
	}
	public String getLastName(){
		return this.lastName;
	}
	public String getPassWord(){
		return this.passWord;
	}
	public String getEmail(){
		return this.email;
	}
	public String getImage(){
		return this.image;
	}
	public String getBlogName(){
		return this.blogName;
	}
}