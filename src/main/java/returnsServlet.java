

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
 * Servlet implementation class returnsServlet
 */
@WebServlet("/returnsServlet")
public class returnsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public returnsServlet() {
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
	        String oid="";
	        oid=request.getParameter("oid");
    		if(oid==null || oid.isEmpty()) {
    			if (session.getAttribute("roid")==null) {
    				session.setAttribute("msg", "");
        			response.sendRedirect("InvoiceSearchServlet");
    	            return;
    	        }
    	        	oid = session.getAttribute("roid").toString();	        	
    	        
    			
    		}
	     
//			 	String msg=session.getAttribute("msg").toString();
	        		if(!oid.trim().isEmpty() ) {
	        			ArrayList<Orders> orderList = DatabaseClass.ordersDetail(oid);

	        			    if (orderList.isEmpty()) {
	        			        session.setAttribute("msg", "Order Not Found");
	        			        response.sendRedirect("InvoiceSearchServlet");
	        			        return;
	        			    } else {
	        			        String msg = session.getAttribute("msg").toString();
	        			        PrintWriter out = response.getWriter();
	        			        out.println("<html>");
	        			        out.println("<head><title>Dynamic Form Data</title></head>");
	        			        out.println("<body>");
	        			        out.println("<h2>Returns</h2>");
	        			        out.println("<button><a href='UserServlet' style='text-decoration: none; color: inherit;'>Go to Dashboard</a></button>");
	        			        out.println("<button><a href='checkReturnCart' style='text-decoration: none; color: inherit;'>Return Cart</a></button>");
	        			        out.println("<button><a href='logoutServlet' style='text-decoration: none; color: inherit;'>Log Out</a></button>");
	        			        out.println("<h5> </h5>");
	        			        out.println(msg);
	        			        session.setAttribute("msg", "");
	        			        out.println("<h5> </h5>");
	        			        session.setAttribute("msg", "");
	        			        out.println("<form action='returnsServlet' method='get' style='display: inline;'>");
	        			        out.println("<input type='text' name='oid' required placeholder='Enter Order ID'>");
	        			        out.println("<input type='submit' value='Search'>");
	        			        out.println("</form>");
	        			        out.println("<h5> </h5>");
	        			        String id, category, name, cname="";
	        			        int qty;
	        			        double price, totalprice = 0, originalprice;
	        			        out.println("<h3>Order ID: " + oid.toUpperCase() + "</h3>");
	        			        out.println("<table style='border: 1px solid black;'>");
	        			        out.println("<tr style='border: 1px solid black;'>");
	        			        out.println("<th style='border: 1px solid black;'>ID</th>");
	        			        out.println("<th style='border: 1px solid black;'>Category</th>");
	        			        out.println("<th style='border: 1px solid black;'>Name</th>");
	        			        out.println("<th style='border: 1px solid black;'>Qty</th>");
	        			        out.println("<th style='border: 1px solid black;'>Price</th>");
	        			        out.println("<th style='border: 1px solid black; colspan='2''>Returns</th>");
	        			        out.println("</tr>");

	        			        for (Orders order : orderList) {
	        			            id = order.getId();
	        			            name = order.getName();
	        			            category = order.getCategory();
	        			            qty = order.getQty();
	        			            price = order.getPrice();
	        			            cname = order.getCname();
	        			            totalprice += order.getTotal(); 
	        			            originalprice = price / qty; 
	        			            
	        			            out.println("<tr style='border: 1px solid black;'>");
	        			            out.println("<td style='border: 1px solid black;'>" + id + "</td>");
	        			            out.println("<td style='border: 1px solid black;'>" + category + "</td>");
	        			            out.println("<td style='border: 1px solid black;'>" + name + "</td>");
	        			            out.println("<td style='border: 1px solid black;'>" + qty + "</td>");
	        			            out.println("<td style='border: 1px solid black;'>" + price + "</td>");
	        			            out.println("<td style='border: 1px solid black;'>");
	        			            out.println("<form action='AddtoReturnCart' method='post' style='display: inline;'>");
	        			            out.println("<input type='hidden' name='id' value='" + id + "'>");
	        			            out.println("<input type='hidden' name='category' value='" + category + "'>");
	        			            out.println("<input type='hidden' name='name' value='" + name + "'>");
	        			            out.println("<input type='hidden' name='qty' value='" + qty + "'>");
	        			            out.println("<input type='hidden' name='price' value='" + originalprice + "'>");
	        			            out.println("<input type='hidden' name='oid' value='" + oid + "'>");
	        			            out.println("Qty: ");
	        			            out.println("<input type='number' name='quantity' min='1' max='" + qty + "' step='1' value='" + 1 + "' style='width: 50px;' required>");
	        			            out.println("<input type='submit' value='Add to Return Cart'>");
	        			            out.println("</form>");
	        			            out.println("</td>");
	        			            out.println("</tr>");
	        			        }

	        			        out.println("</table>");
	        			        out.println("<h3>Total Amount: " + totalprice + "</h3>");
	        			        out.println("<h3>Customer Name: " + cname + "</h3>");
	        			        out.println("</body>");
	        			        out.println("</html>");
	        			    }
	        			
	        		}else {
	        			session.setAttribute("msg", "Order Not Found ---");
	        			response.sendRedirect("InvoiceSearchServlet");
	    	            return;
	        		}
	        		
	        			
	        		
			 	
				
					    
						
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
