package DB;

public class CString {
	
    String str;

    public CString(String str) {
        this.str = str;
    }

    public void changeTo(String newStr) {
        str = newStr;
    }

    public String toString() {
        return str;
    }
}