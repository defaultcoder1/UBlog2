package Servelts;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Controllers.DBConnector;
import Controllers.FileUploadController;
import Controllers.ImageController;
import DB.CString;
import Models.User;

/**
 * Servlet implementation class GalleryServlet
 */
@WebServlet("/Gallery")
public class GalleryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GalleryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = (User)request.getSession().getAttribute("user");
		DBConnector dbCon = (DBConnector)getServletContext().getAttribute("DBC");
		ImageController ic = new ImageController(dbCon.getConnection());
		request.setAttribute("images", ic.getImagesByUserID(u.getId()));
		RequestDispatcher rd = request.getRequestDispatcher("/assets/TEMPLATES/Gallery.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		User u = (User)request.getSession().getAttribute("user");
		DBConnector dbCon = (DBConnector)getServletContext().getAttribute("DBC");
		ImageController ic = new ImageController(dbCon.getConnection());
		if(isMultipart) {
			ServletFileUpload upload = new ServletFileUpload();
			try {
				FileItemIterator itr = upload.getItemIterator(request);
				while(itr.hasNext()) {
					FileItemStream item = itr.next();
					if(item.isFormField()) {}
					else {
						String name = ic.getNextName(u.getId());
						if(FileUploadController.processFile(name, item, u.getId())) {
							ic.addImage(u.getId(), "/UBlog/user_uploads/"+u.getId()+"/"+name+item.getName().substring(item.getName().length()-4));
							this.doGet(request, response);
						}
					}
				}
			} catch (FileUploadException fue) { fue.printStackTrace(); }
		}
	}

}
