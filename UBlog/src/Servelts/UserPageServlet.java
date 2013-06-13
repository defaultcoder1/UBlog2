package Servelts;

import java.io.IOException;
import java.util.ArrayList;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import Controllers.DBConnector;
import Controllers.UserController;
import Models.Author;
import Models.Blog;
import Models.User;

/**
 * Servlet implementation class index
 */
@WebServlet("/UserPage")
public class UserPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private void addRequestAttributes(HttpServletRequest request,User u,UserController uc){
    	request.setAttribute("user", u);
    	ArrayList<Author> arr = new ArrayList<Author>();
    	for(int i=0;i<u.getSubscribersNum();i++){
    		arr.add(uc.getAuthorById(u.getSubscriber(i)));
    	}
    	request.setAttribute("subscribers", arr);
    	ArrayList<Author> arr2 = new ArrayList<Author>();
    	for(int i=0;i<u.getSubscriptionsNum();i++){
    		arr2.add(uc.getAuthorById(u.getSubscription(i)));
    	}
    	request.setAttribute("subscriptions", arr2);
		ArrayList<Blog> myBlogs = uc.getUserBlogs(u.getId());
		request.setAttribute("myblogs", myBlogs);
		ArrayList<Blog> suBlogs = new ArrayList<Blog>();
		for(int i=0;i<u.getSubscriptionsNum();i++){
			suBlogs.addAll(uc.getUserBlogs(u.getSubscription(i)));
		}
		request.setAttribute("sublogs", suBlogs);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("user.jsp");
		User u;
		UserController uc = new UserController((Connection) ((DBConnector)getServletContext().getAttribute("DBC")).getConnection());
		if(request.getSession().getAttribute("user")!=null){
			if(request.getParameter("id")==request.getSession().getAttribute("id") || 
					request.getParameter("id")==null){
				u = (User) request.getSession().getAttribute("user");
			}
			
			else{
				u = uc.getUserById((String)request.getParameter("id"));
			}
			this.addRequestAttributes(request, u,uc);
		}else{
			rd = request.getRequestDispatcher("Login");
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
