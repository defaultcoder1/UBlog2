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
import Controllers.SubscribeController;
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
  
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = (Connection) ((DBConnector)getServletContext().getAttribute("DBC")).getConnection();
		String which = request.getParameter("which");
		LikeController lc = new LikeController(con);
		CommentController cmc = new CommentController(con);
		SubscribeController sbc = new SubscribeController(con);
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
((User)request.getSession().getAttribute("user")).getId(), request.getParameter("content"),
	request.getParameter("date"), con);
			break;
		case "addsusbcribe":
			sbc.addSubscriber(((User)request.getSession().getAttribute("user")).getId(),
		request.getParameter("towho"));
			break;
		case "unsusbcribe":
			sbc.reomveSubscribe(((User)request.getSession().getAttribute("user")).getId(),
		request.getParameter("towho"));
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
