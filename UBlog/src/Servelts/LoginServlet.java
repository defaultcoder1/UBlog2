package Servelts;

import java.io.IOException;
import java.util.ArrayList;

import javax.management.modelmbean.RequiredModelMBean;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import Controllers.DBConnector;
import Controllers.LoginController;
import Controllers.UserController;
import Models.User;

/**
 * Servlet implementation class LoginServelt
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		if(email==null){
			email = (String) request.getParameter("remail");
			password = (String) request.getParameter("rpassword");
		}
		RequestDispatcher rd;
		if(email==null){
			request.getSession().removeAttribute("user");
			rd = request.getRequestDispatcher("login.jsp");
			return;
		}
		if(email=="" || password=="" || email==null){
			rd = request.getRequestDispatcher("login.jsp");
		}
		else{	
			LoginController lc = new LoginController(((DBConnector)getServletContext().getAttribute("DBC")).getConnection());
			String a = lc.checkInput(email, password);
			if(a.equals("-1")){
				rd = request.getRequestDispatcher("login.jsp");
			}
			else{
				rd = request.getRequestDispatcher("/UserPage");
				UserController uc = new UserController((Connection) ((DBConnector)getServletContext().getAttribute("DBC")).getConnection());
				User u = uc.getUserById(a);
				request.getSession().setAttribute("user",u);
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
