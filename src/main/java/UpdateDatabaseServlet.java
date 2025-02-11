

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class UpdateDatabaseServlet
 */
@WebServlet("/UpdateDatabaseServlet")
public class UpdateDatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    	  int qty=-1;
    	  Double price=-1.0;
    	  String id=request.getParameter("id");
    	  try {
    	  qty=Integer.parseInt(request.getParameter("qty"));
    	   price=Double.parseDouble(request.getParameter("price"));
    		    if (qty >= 0 && price >= 0) {
    		        int rs = DatabaseClass.updateProduct(id, qty, price);
    		        String msg = "";
    		        if (rs > 0) {
    		            msg = "ID = " + id + " Updated successfully";
    		        } else {
    		            msg = "Update failed. Please try again.";
    		        }
    		        session.setAttribute("msg", msg);
    		    } else {
    		        session.setAttribute("msg", "Invalid quantity or price. Please try again.");
    		    }
    		   response.sendRedirect("secondServlet");
    		} catch (Exception e) {
    			session.setAttribute("msg", "Invalid quantity or price. Please try again.");
    			response.sendRedirect("secondServlet");
    		}

      
	}

}
