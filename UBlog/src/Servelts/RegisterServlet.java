package Servelts;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;


import Controllers.DBConnector;
import Controllers.RegisterController;
import Controllers.UserController;
import Models.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private boolean checkAtributes(HttpServletRequest request){
    	if((String) request.getParameter("rfname")=="" || (String)request.getParameter("rlname")==""
    		|| (String)request.getParameter("remail")=="" || (String)request.getParameter("rbname")=="" 
    		|| (String)request.getParameter("rpassword")=="" 
    		|| !((String)request.getParameter("rrpassword")).equals((String)request.getParameter("rpassword"))){
    		return false;
    	}
    	return true;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		UserController uc = new UserController((Connection) ((DBConnector)getServletContext().getAttribute("DBC")).getConnection());
		if(!checkAtributes(request)){
			rd = request.getRequestDispatcher("login.jsp");
		}else{
			RegisterController rc = new RegisterController(((DBConnector)getServletContext().getAttribute("DBC")).getConnection());
			String a = rc.addNewUser((String)request.getParameter("rfname"),(String)request.getParameter("rlname"),
					(String)request.getParameter("remail"),(String)request.getParameter("rpassword"),
					"",(String)request.getParameter("rbname"));
			if(!a.equals("-1")){
				rd = request.getRequestDispatcher("/Login");
			}
			else{
				rd = request.getRequestDispatcher("login.jsp");
			}
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
