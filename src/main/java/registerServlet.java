

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name,username,password;
		name=request.getParameter("fname");
		username=request.getParameter("username");
		password=request.getParameter("password");
		if (name == null || name.trim().isEmpty() || username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
			    response.sendRedirect("register.html");
		} else {
			int insert=DatabaseClass.insertuser(username.trim(), password.trim(), name.trim());
			RequestDispatcher rd=request.getRequestDispatcher("registrantionMessagePage");
			if(insert>0) {
				request.setAttribute("confirmation","Registration Successfully.");
//				response.sendRedirect("index.html");
			}else if(insert==-108){
				request.setAttribute("confirmation","Registration Failed.(Username already exist.)");
//				response.sendRedirect("register.html");
			}else {
				request.setAttribute("confirmation","Registration Failed.");
			}
			rd.forward(request, response);
			
		}
	}

}
