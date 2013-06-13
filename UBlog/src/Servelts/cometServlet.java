package Servelts;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/chat.do", asyncSupported = true)
public class cometServlet extends HttpServlet {

	private static final long serialVersionUID = 5268036672498661815L;

	private static Map<String, Teste> list = Collections.synchronizedMap(new HashMap<String, Teste>());

	@Override
	protected void service(final HttpServletRequest req,
			final HttpServletResponse res) throws ServletException, IOException {

		AsyncContext ctx = req.startAsync(req, res);
		
		new Teste(ctx, list).start();
		
		String to = req.getParameter("to");
		
		if (to != null){
			list.get(to).setMsg(req);
		}
	}
}

class Teste extends Thread {

	AsyncContext ctx;
	
	Map<String, Teste> list;
	
	String msg;

	public Teste(AsyncContext ctx, Map<String, Teste> list) {
		this.ctx = ctx;
		this.list = list;
	}

	public void run() {
		String from = ctx.getRequest().getParameter("from");
		
		list.put(from, this);
		
		try {
			synchronized (this) {
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			ctx.getResponse().getWriter().println(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ctx.complete();
		
		list.remove(from);
	};
	
	public void setContext(AsyncContext ctx) {
		this.ctx = ctx;
	}

	public void setMsg(HttpServletRequest req) throws IOException {
		
		String to = req.getParameter("to");
		
		msg = to + " > " + req.getParameter("msg");

		synchronized (this) {
			list.get(to).notify();
		}
	}
}