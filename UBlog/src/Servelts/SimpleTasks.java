package Servelts;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import Controllers.CommentController;
import Controllers.DBConnector;
import Controllers.LikeController;
import DB.CString;
import DB.Condition;
import DB.DB;
import Models.Blog;
import Models.Comment;
import Models.User;

/**
 * Servlet implementation class SimpleTasks
 */
@WebServlet("/SimpleTasks")
public class SimpleTasks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimpleTasks() {
        super();
        // TODO Auto-generated constructor stub
    }
  
    private void addSubscriber(String who,String towho,Connection con){
    	DB db = new DB(con, "ublog");
    	HashMap<String, String> m = new HashMap<String,String>();
		m.put("Subscriber_ID",who);
		m.put("Author_ID", towho);
		db.insert("subscribe", m,null);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = (Connection) ((DBConnector)getServletContext().getAttribute("DBC")).getConnection();
		String which = request.getParameter("which");
		LikeController lc = new LikeController(con);
		CommentController cmc = new CommentController(con);
		switch (which) {
		case "likearticle":
			lc.addLikeToArticle(((User)request.getSession().getAttribute("user")).getId(),
					request.getParameter("articleid"), con);
			break;
		case "likecomment":
			lc.addLikeToComment(((User)request.getSession().getAttribute("user")).getId(),
					request.getParameter("commentid"), con);
			break;
		case "addcomment":
			cmc.addComment(request.getParameter("articleid"), 
((User)request.getSession().getAttribute("user")).getId(), request.getParameter("content"),
	request.getParameter("date"), con);
			break;
		case "addsusbcribe":
			this.addSubscriber(((User)request.getSession().getAttribute("user")).getId(),
		request.getParameter("towho"), con);
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
