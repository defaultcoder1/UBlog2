package Servelts;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import Controllers.BlogController;
import Controllers.DBConnector;
import Models.User;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/Index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		if(request.getSession().getAttribute("user")!=null){
			if(request.getParameter("content")!=null && !request.getParameter("content").equals("")){
				BlogController bc = new BlogController((Connection) ((DBConnector)getServletContext().getAttribute("DBC")).getConnection());
				User u = (User)(request.getSession().getAttribute("user"));
				bc.addArticle(request.getParameter("image"), request.getParameter("title"), 
						request.getParameter("content"), u.getId());
				request.setAttribute("blogs" , bc.getBlogs(0, 20));
				rd = request.getRequestDispatcher("index.jsp");
			}
			
			else if(request.getSession().getAttribute("user")!=null && request.getParameter("content")==null){
				BlogController bc = new BlogController((Connection) ((DBConnector)getServletContext().getAttribute("DBC")).getConnection());
				request.setAttribute("blogs" , bc.getBlogs(0, 20));
				rd = request.getRequestDispatcher("index.jsp");
			}
			else{
				rd = request.getRequestDispatcher("Publish");
			}
		}
		else{
			rd = request.getRequestDispatcher("Login");
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
