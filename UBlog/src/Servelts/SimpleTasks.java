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
import Controllers.UserController;
import DB.CString;
import DB.Condition;
import DB.DB;
import DB.ResultList;
import DB.Row;
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
    private void removeSubscriber(String who,String towho,Connection con){
    	DB db = new DB(con, "ublog");
    	Condition cnd = new Condition(true);
    	cnd.add("Subscriber_ID", who);
    	cnd.add("Author_ID", towho);
    	db.delete("subscribe");
    }
    private String getLastComment(User u, String articleID, Connection con) {
    	DB db = new DB(con, "ublog");
    	Condition cnd = new Condition(true);
    	cnd.add("User_ID", u.getId());
    	cnd.add("Article_ID", articleID);
    	db.where(cnd);
    	db.order("Date DESC");
    	ResultList rl = db.get("comment", "0", "1");
    	if(rl.size() == 0) return "";
    	Row row = rl.first();
    	String result = "<div class='full_article_comment'><table class='full_article_comment_t' cellpadding='0' cellspacing='0' border='0'><tr><td class='full_article_comment_author_avatar'>";
    	result += "<img src='"+ u.getImage() +"' /></td><td class='full_article_comment_content'>";
    	result += "<font class='full_article_comment_author_name'>"+ u.getName() + " " + u.getLastName() +"</font>";
    	result += row.get("Content");
		result += "<p class='full_article_comment_date'>";
		result += row.get("Date") + "<font class='full_article_comment_like ' commentid='"+row.get("Comment_ID")+"'>Like</font><font class='full_article_comment_like_num'><font class='full_article_comment_like_number'>0</font> bloggers like this</font>";
		result += "</td></tr></table></div>";
    	
    	return result;
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
		case "unlikecomment":
			lc.unlikeComment(request.getParameter("commentid"),
					((User)request.getSession().getAttribute("user")).getId());
			break;
		case "unlikearticle":
			lc.unlikeArticle(request.getParameter("articleid"),
					((User)request.getSession().getAttribute("user")).getId());
			break;
		case "addcomment":
			cmc.addComment(request.getParameter("articleid"), 
((User)request.getSession().getAttribute("user")).getId(), request.getParameter("content"));
			response.getWriter().print(this.getLastComment(((User)request.getSession().getAttribute("user")),
					request.getParameter("articleid"), con));
			break;
		case "addsubscribe":
			this.addSubscriber(((User)request.getSession().getAttribute("user")).getId(),
		request.getParameter("towho"), con);
			break;
		case "removesubscribe":
			this.removeSubscriber(((User)request.getSession().getAttribute("user")).getId(),
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
