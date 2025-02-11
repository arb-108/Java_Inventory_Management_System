

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
 * Servlet implementation class UserproductSearch
 */
@WebServlet("/UserproductSearch")
public class UserproductSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserproductSearch() {
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
			 String str=request.getParameter("searchid");
			 ArrayList<Product> products=DatabaseClass.searchproduct(str);
			 
				
				 if (products.isEmpty()) {
					 session.setAttribute("msg", "Not Found");
					 response.sendRedirect("secondServlet");
		        }else {
		        	session.setAttribute("msg", "");
					PrintWriter out=response.getWriter();
		        	out.println("<html>");
			        out.println("<head><title>Dynamic Form Data</title></head>");
			        out.println("<body>");
			        String fname=(String)session.getAttribute("fname");
			        out.println("<h2>Name : "+fname+"</h2>");
				    out.println("<button><a href='UserServlet' style='text-decoration: none; color: inherit;'>Go to Dashboard</a></button>");
				    out.println("<button><a href='logoutServlet' style='text-decoration: none; color: inherit;'>Log Out</a></button>");
				    out.println("<br>");
				    out.println("<br>");
//			        PrintWriter out = response.getWriter();
			        out.println("<table style='border: 1px solid black;'>");
			        out.println("<tr style='border: 1px solid black;'>");
			        out.println("<th style='border: 1px solid black;'>ID</th>");
			        out.println("<th style='border: 1px solid black;'>Category</th>");
			        out.println("<th style='border: 1px solid black;'>Name</th>");
//			        out.println("<th style='border: 1px solid black;'>Qty</th>");
			        out.println("<th style='border: 1px solid black;'>Price</th>");
			        out.println("<th style='border: 1px solid black;'>Controls</th>");
			        out.println("</tr>");

			        for (Product product : products) {
			            String id = product.getId();
			            String category = product.getCategory();
			            String name = product.getName();
			            int qty = product.getQuantity();
			            double price = product.getPrice();

			            out.println("<tr style='border: 1px solid black;'>");
			            out.println("<td style='border: 1px solid black;'>" + id + "</td>");
			            out.println("<td style='border: 1px solid black;'>" + category + "</td>");
			            out.println("<td style='border: 1px solid black;'>" + name + "</td>");
//			            out.println("<td style='border: 1px solid black;'>" + qty + "</td>");
			            out.println("<td style='border: 1px solid black;'>" + price + "</td>");
			            out.println("<td style='border: 1px solid black;'>");
//			            
//			            out.println("<form action='removeProductservlet' method='get' style='display: inline;'>");
//			            out.println("<input type='hidden' name='id' value='" + id + "'>");
//			            out.println("<input type='submit' value='Remove'>");
//			            out.println("</form>");
			            out.println("<form action='AddtocartServlet' method='post' style='display: inline;'>");
					    out.println("<input type='hidden' name='id' value='" + id + "'>");
					    out.println("<input type='hidden' name='category' value='" + category + "'>");
					    out.println("<input type='hidden' name='name' value='" + name + "'>");
					    out.println("<input type='hidden' name='qty' value='" + qty + "'>");
					    out.println("<input type='hidden' name='price' value='" + price + "'>");
					    out.println("Qty : ");
					    out.println("<input type='number' name='quantity' value='" + 1+ "' style='width: 50px;' required>");
					    out.println("<input type='submit' value='Add to Cart'>");
					    out.println("</form>");

					    out.println("</td>");
					    out.println("</tr>");
			        }

					out.println("</table>");
		        out.println("</body>");
		        out.println("</html>");
		        }
			 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
