

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class removeProductservlet
 */
@WebServlet("/removeProductservlet")
public class removeProductservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("usertype") == null) {
            response.sendRedirect("index.html");
            return;
        }

        String usertype = session.getAttribute("usertype").toString();
        if (!"admin".equals(usertype)) {
            response.sendRedirect("index.html");
            return;
        }
		String id=request.getParameter("id");
		int rs=DatabaseClass.removeProduct(id);
		String msg = "";
		if(rs>0) {
			 msg = "ID = " + id + " Remove successfully";
		}else {
			msg = "Removal failed. Please try again.";
		}
		session.setAttribute("msg", msg);
		response.sendRedirect("secondServlet");
	  
	}
}
