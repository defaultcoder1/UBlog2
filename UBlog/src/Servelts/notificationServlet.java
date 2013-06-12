package Servelts;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.comet.CometEvent;
import org.apache.catalina.comet.CometProcessor;

import com.mysql.jdbc.Connection;

import Controllers.DBConnector;
import Controllers.UserController;

/**
 * Servlet implementation class notificationServlet
 */
@WebServlet("/notificationServlet")
public class notificationServlet extends HttpServlet implements CometProcessor {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public notificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		new UserController((Connection) ((DBConnector)getServletContext().getAttribute("DBC"))).getSubscribers("18");
		response.getWriter().print("gigi she bozo");
	}

	@Override
	public void event(CometEvent event) throws IOException, ServletException {
		System.out.println("Received COMET Event: "+event.getEventType());
        event.close();
	}

}
