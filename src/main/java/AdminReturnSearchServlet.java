

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Servlet implementation class AdminReturnSearchServlet
 */
@WebServlet("/AdminReturnSearchServlet")
public class AdminReturnSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminReturnSearchServlet() {
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
        if (!"admin".equals(usertype)) {
            response.sendRedirect("index.html");
            return;
        }
        PrintWriter out=response.getWriter();
		out.println("<html>");
        out.println("<head><title>Dynamic Form Data</title></head>");
        out.println("<body>");
        
        out.println("<h2>- Returns</h2>");
        out.println("<button><a href='secondServlet' style='text-decoration: none; color: inherit;'>Go to Dashboard</a></button>");
	    out.println("<button><a href='logoutServlet' style='text-decoration: none; color: inherit;'>Log Out</a></button>");
	    out.println("<h3> </h3>");
	    out.println("<form action='AdminReturnSearchServlet' method='get'>");
	    out.println("<input type='text' name='rid' required placeholder='Enter Return ID'>");
	    out.println("<input type='submit' value='Search Return'>");
	    out.println("</form>");
//	    out.println("<br>");
	    String msg = "";
	    if (session.getAttribute("msg") != null) {
	        msg = session.getAttribute("msg").toString();
	    }
	    out.println(msg);
//	    out.println("<h4>- Orders</h4>");
	    session.setAttribute("msg", "");
	    String oid=request.getParameter("rid");
	    if(!oid.trim().isEmpty() ) {
	    	ArrayList<returns> returnsList = DatabaseClass.returnDetail(oid);
	    	
	    	    if (returnsList.isEmpty()) {
	    	        session.setAttribute("msg", "Return Not Found");
	    	        response.sendRedirect("ShowReturnsServlet");
	    	        return;
	    	    } else {
	    	        String rid, id, category, name, cname="";
	    	        int qty;
	    	        double price, totalprice = 0;

	    	        out.println("<h3>Return ID: " + oid.toUpperCase() + "</h3>");
	    	        out.println("<table style='border: 1px solid black;'>");
	    	        out.println("<tr style='border: 1px solid black;'>");
	    	        out.println("<th style='border: 1px solid black;'>Order ID</th>");
	    	        out.println("<th style='border: 1px solid black;'>ID</th>");
	    	        out.println("<th style='border: 1px solid black;'>Category</th>");
	    	        out.println("<th style='border: 1px solid black;'>Name</th>");
	    	        out.println("<th style='border: 1px solid black;'>Qty</th>");
	    	        out.println("<th style='border: 1px solid black;'>Price</th>");
	    	        out.println("<th style='border: 1px solid black; colspan='2''>Status</th>");
	    	        out.println("</tr>");
	    	        for (returns returnObj : returnsList) {
	    	            rid = returnObj.getOid();
	    	            id = returnObj.getId();
	    	            name = returnObj.getName();
	    	            category = returnObj.getCategory();
	    	            qty = returnObj.getQty();
	    	            price = returnObj.getPrice();
	    	            cname = returnObj.getCname();
	    	            totalprice += returnObj.getTotal();  

	    	            out.println("<tr style='border: 1px solid black;'>");
	    	            out.println("<td style='border: 1px solid black;'>" + rid + "</td>");
	    	            out.println("<td style='border: 1px solid black;'>" + id + "</td>");
	    	            out.println("<td style='border: 1px solid black;'>" + category + "</td>");
	    	            out.println("<td style='border: 1px solid black;'>" + name + "</td>");
	    	            out.println("<td style='border: 1px solid black;'>" + qty + "</td>");
	    	            out.println("<td style='border: 1px solid black;'>" + price + "</td>");
	    	            out.println("<td style='border: 1px solid black;'>");
	    	           
	    	            out.println("</td>");
	    	            out.println("</tr>");
	    	        }

	    	        out.println("</table>");
	    	        out.println("<h3>Total Amount: " + totalprice + "</h3>");
	    	        out.println("<h3>Customer Name: " + cname + "</h3>");
	    	    }
		}else {
			session.setAttribute("msg", "Return Not Found ---");
			response.sendRedirect("ShowReturnsServlet");
            return;
		}
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
