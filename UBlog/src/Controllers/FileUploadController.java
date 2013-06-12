package Controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.fileupload.FileItemStream;


public class FileUploadController {
	
	public static boolean processFile(String path, FileItemStream item) {
		try {
			File savedFile = new File("C:\\Users\\GiorgiTS\\git\\UBlog2\\UBlog\\WebContent\\user_uploads\\" + item.getName());
			FileOutputStream fos = new FileOutputStream(savedFile);
			InputStream is = item.openStream();
			int x = 0;
			byte[] b = new byte[1024];
			while((x = is.read(b)) != -1) {
				fos.write(b, 0, x);
			}
			fos.flush();
			fos.close();
			return true;
			
		} catch(Exception e) { e.printStackTrace(); }
	
		
		return false;
	}
}