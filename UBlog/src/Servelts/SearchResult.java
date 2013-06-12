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

import Controllers.BlogController;
import Controllers.DBConnector;
import DB.Condition;
import DB.DB;
import DB.ResultList;
import Models.Blog;

/**
 * Servlet implementation class SearchResult
 */
@WebServlet("/Search")
public class SearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchResult() {
        super();
        // TODO Auto-generated constructor stub
    }
    private ArrayList<Blog> serachCategory(String category){
    	BlogController bc = new BlogController((Connection) ((DBConnector)getServletContext().getAttribute("DBC")).getConnection());
    	if(category.equals("all")){
    		return bc.getBlogs(0, 30);
    	}
    	ArrayList<Blog> arr = new ArrayList<Blog>();
    	DB db = new DB(((DBConnector)getServletContext().getAttribute("DBC")).getConnection(), "ublog");
    	Condition c = new Condition(true);
    	c.add("Category", category);
    	db.where(c);
    	ResultList rl = db.get("article");
    	for(int i=0;i<rl.size();i++){
    		arr.add(bc.getBlogById(rl.get(i).get("Article_ID")));
    	}
    	return arr;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String curCat = request.getParameter("category");
		RequestDispatcher rd;
		if(request.getParameter("searchInput").equals("1")){
			String toSearch = request.getParameter("search");
			ArrayList<Blog> arr = new ArrayList<Blog>();
			DB db = new DB(((DBConnector)getServletContext().getAttribute("DBC")).getConnection(), "ublog");
			Condition c = new Condition(true); 
			c.add("tag", toSearch);
			db.where(c);
			ResultList rl = db.get("tag_article");
			BlogController bc = new BlogController((Connection) ((DBConnector)getServletContext().getAttribute("DBC")).getConnection());
			for(int i=0;i<rl.size();i++){
				Blog b = bc.getBlogById(rl.get(i).get("Article_ID"));
				if(curCat.equals("all") || b.getCategory().equals(curCat))
					arr.add(b);
			}
			request.setAttribute("blogs", arr);
			rd = request.getRequestDispatcher("search.jsp");
		}
		else{
			request.setAttribute("blogs", this.serachCategory(curCat));
			rd = request.getRequestDispatcher("index.jsp");
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
