package Servelts;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controllers.DBConnector;
import Controllers.RegisterController;
import Controllers.UserController;
import DB.Condition;
import DB.DB;
import Models.User;

import com.mysql.jdbc.Connection;
import com.oracle.jrockit.jfr.RequestDelegate;

/**
 * Servlet implementation class UserInfo
 */
@WebServlet("/UserInfo")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfo() {
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
		if(request.getParameter("submit")!=null){
			if(!this.checkAtributes(request)){
				RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
				rd.forward(request, response);
				return;
			}
			RegisterController rc = new RegisterController((Connection) ((DBConnector)getServletContext().getAttribute("DBC")).getConnection());
			UserController usc = new UserController((Connection) ((DBConnector)getServletContext().getAttribute("DBC")).getConnection());
			usc.deleteUser((String) request.getAttribute("oldid"));
			rc.addOldUser((String)request.getAttribute("oldid"),(String)request.getParameter("rfname"),(String)request.getParameter("rlname"),
					(String)request.getParameter("remail"),(String)request.getParameter("rpassword"),
					(String)request.getParameter("rimage"),(String)request.getParameter("rbname"));
			request.getSession().setAttribute("user",usc.getUserById((String)request.getAttribute("oldid")));
			RequestDispatcher rd = request.getRequestDispatcher("UserPage");
			rd.forward(request, response);
			return;
		}
		if(request.getSession().getAttribute("user")==null){
			RequestDispatcher rd = request.getRequestDispatcher("Login");
			rd.forward(request, response);
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
