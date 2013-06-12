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

import Controllers.FileUploadController;

/**
 * Servlet implementation class PublishServlet
 */
@WebServlet("/Publish")
public class PublishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublishServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		if(request.getSession().getAttribute("user")==null){
			rd = request.getRequestDispatcher("Login");
		}else{
			rd = request.getRequestDispatcher("publish.jsp");
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//this.doGet(request, response);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart) {
			ServletFileUpload upload = new ServletFileUpload();
			try {
				FileItemIterator itr = upload.getItemIterator(request);
				while(itr.hasNext()) {
					FileItemStream item = itr.next();
					if(item.isFormField()) {}
					else {
						String path = getServletContext().getRealPath("/");
						if(FileUploadController.processFile(path, item)) {
							response.getWriter().println("File uploaded successfully");
						} else response.getWriter().println("File uploading failed");
					}
				}
			} catch (FileUploadException fue) { fue.printStackTrace(); }
		}
	}

}
