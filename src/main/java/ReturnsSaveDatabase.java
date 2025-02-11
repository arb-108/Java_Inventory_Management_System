

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class ReturnsSaveDatabase
 */
@WebServlet("/ReturnsSaveDatabase")
public class ReturnsSaveDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnsSaveDatabase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	        @SuppressWarnings("unchecked")
	        ArrayList<Product> items = (ArrayList<Product>) session.getAttribute("Returnitems");

	        if (items == null || items.isEmpty()) {
	            session.setAttribute("msg", "No items to return.");
	            response.sendRedirect("UserServlet");
	            return;
	        }
	        int maxindex=DatabaseClass.maxIndexfindreturns();
			maxindex++;
			String invoice="R"+maxindex+"";
	        String cname = request.getParameter("cname");
	        double total = 0.0;

	        try {
	            total = Double.parseDouble(request.getParameter("totalprice"));
	        } catch (NumberFormatException e) {
	            session.setAttribute("msg", "Invalid total price.");
	            response.sendRedirect("UserServlet");
	            return;
	        }

	        int rs = DatabaseClass.Addreturns(items,invoice, cname, total);

	        if (rs > 0) {
	            session.setAttribute("msg", "Returns saved successfully.");
	            items.clear(); 
	            session.setAttribute("Returnitems", items); 
	            session.setAttribute("roid", null); 
	        } else {
	            session.setAttribute("msg", "Failed to save returns. Please try again.");
	        }

	        response.sendRedirect("UserServlet");

	}

}
