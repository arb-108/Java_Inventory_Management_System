

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
/**
 * Servlet implementation class sessionServlet
 */
@WebServlet("/sessionServlet")
public class sessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("index.html");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
////      String userName=request.getParameter("username");
//		if (session != null && session.getAttribute("usertype") != null) {
//		    response.sendRedirect("index.html");
//		    return;
//		}
		PrintWriter out=response.getWriter();
		String uname=request.getParameter("username");
		String upass=request.getParameter("password");
		user result=DatabaseClass.checkuser(uname, upass);
	        if(result!=null) {
	        	out.println("<h2>Welcome Sir Ge"+result.getUserType()+"</h2>");
	        	session = request.getSession(true);
	        	session.setAttribute("usertype", result.getUserType());
	        	session.setAttribute("fname", result.getName());
	        	session.setAttribute("Musername", result.getUsername());
	        	if(result.getUserType().toLowerCase().equals("admin")) {
	        		response.sendRedirect("secondServlet");	        		
	        	}else {
	        		response.sendRedirect("UserServlet");	
	        	}
	        }else {
	        	response.sendRedirect("register.html");
			}

	}
}
