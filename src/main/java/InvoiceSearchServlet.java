

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

/**
 * Servlet implementation class InvoiceSearchServlet
 */
@WebServlet("/InvoiceSearchServlet")
public class InvoiceSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvoiceSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		 if (session == null || session.getAttribute("usertype") == null) {
	            response.sendRedirect("index.html");
	            return;
	        }

	        String usertype = session.getAttribute("usertype").toString();
	        if (!"user".equals(usertype)) {
	            response.sendRedirect("index.html");
	            return;
	        }
	        	
			 	String msg=session.getAttribute("msg").toString();
			 	
				 PrintWriter out=response.getWriter();
					out.println("<html>");
			        out.println("<head><title>Dynamic Form Data</title></head>");
			        out.println("<body>");
			        out.println("<h2>Returns</h2>");
			        out.println("<button><a href='UserServlet' style='text-decoration: none; color: inherit;'>Go to Dashboard</a></button>");
			        out.println("<button><a href='checkReturnCart' style='text-decoration: none; color: inherit;'>Return Cart</a></button>");
				    out.println("<button><a href='logoutServlet' style='text-decoration: none; color: inherit;'>Log Out</a></button>");
				   
				    out.println("<h5> </h5>");
				    out.println(msg);
				    out.println("<h5> </h5>");
				    session.setAttribute("msg", "");
					    out.println("<form action='returnsServlet' method='get' style='display: inline;'>");
					    out.println("<input type='text' name='oid' required placeholder='Enter Order ID'>");
					    out.println("<input type='submit' value='Search'>");
					    out.println("</form>");
					    
						
				out.println("</body>");
		        out.println("</html>");
			 
			 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
