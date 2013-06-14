package Controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.apache.commons.fileupload.FileItemStream;

public class FileUploadController {
	
	public static boolean processFile(String name, FileItemStream item, String userID) {
		try {
			File f = new File("C:"+File.separator+"Users"+File.separator+"GiorgiTS"+File.separator+"git"+File.separator+"UBlog2"+File.separator+"UBlog"+File.separator+"WebContent"+File.separator+"user_uploads"+File.separator+userID);
			if(!f.exists()) f.mkdir();
			File savedFile = new File(f.getAbsolutePath() + File.separator + name + "." + item.getName().substring(item.getName().length()-3));
			FileOutputStream fos = new FileOutputStream(savedFile);
			InputStream is = item.openStream();
			int x = 0;
			byte[] b = new byte[1024];
			while((x = is.read(b)) != -1) {
				fos.write(b, 0, x);
			}
			fos.flush();
			fos.close();
			is.close();
			return true;
			
		} catch(Exception e) { e.printStackTrace(); }
	
		return false;
	}
}