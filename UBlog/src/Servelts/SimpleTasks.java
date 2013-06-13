package Servelts;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import Controllers.DBConnector;
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
    private void addLikeToArticle(String userid,String blogID,Connection con){
    	DB db = new DB(con, "ublog");
    	HashMap<String, String> m = new HashMap<String,String>();
		m.put("User_ID",userid);
		m.put("Article_ID", blogID);
		db.insert("like_article", m,null);
    }
    private void addLikeToComment(String userid,String commentId,Connection con){
    	DB db = new DB(con, "ublog");
    	HashMap<String, String> m = new HashMap<String,String>();
		m.put("User_ID",userid);
		m.put("Comment_ID", commentId);
		db.insert("like_comment", m,null);
    }
    private void addSubscriber(String who,String towho,Connection con){
    	DB db = new DB(con, "ublog");
    	HashMap<String, String> m = new HashMap<String,String>();
		m.put("Subscriber_ID",who);
		m.put("Author_ID", towho);
		db.insert("subscribe", m,null);
    }
    private void addComment(String articleid,String userid,String content,String date,Connection con){
    	DB db = new DB(con, "ublog");
    	HashMap<String, String> m = new HashMap<String,String>();
		m.put("Article_ID",articleid);
		m.put("User_ID",userid);
		m.put("Content", content);
		m.put("Date", date);
		db.insert("comment", m,null);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = (Connection) ((DBConnector)getServletContext().getAttribute("DBC")).getConnection();
		String which = request.getParameter("which");
		switch (which) {
		case "likearticle":
			this.addLikeToArticle(((User)request.getSession().getAttribute("user")).getId(),
					request.getParameter("articleid"), con);
			break;
		case "likecomment":
			
			break;
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
