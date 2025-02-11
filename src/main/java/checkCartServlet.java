

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
 * Servlet implementation class checkCartServlet
 */
@WebServlet("/checkCartServlet")
public class checkCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkCartServlet() {
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
			 @SuppressWarnings("unchecked")
			ArrayList<Product> items= (ArrayList<Product>) session.getAttribute("items");
			 PrintWriter out=response.getWriter();
				out.println("<html>");
		        out.println("<head><title>Dynamic Form Data</title></head>");
		        out.println("<body>");
		        out.println("<button><a href='UserServlet' style='text-decoration: none; color: inherit;'>Go to Dashboard</a></button>");
			    out.println("<button><a href='logoutServlet' style='text-decoration: none; color: inherit;'>Log Out</a></button>");
			    out.println("<br>");
			    String msg = "";
			    if (session.getAttribute("msg") != null) {
			        msg = session.getAttribute("msg").toString();
			    }
			    out.println(msg);
			    session.setAttribute("msg", "");
				if(items==null || items.isEmpty()) {
					out.println("<br>");
					out.println("No Product in Cart");
				}else {
					double totalPrice=0.0;
					out.println("<h3>Products in Cart:</h3>");
				    out.println("<table border='1'>");
				    out.println("<tr><th>ID</th><th>Category</th><th>Name</th><th>Quantity</th><th>Price</th><th>Actions</th></tr>");
				    for(Product product :items ) {
				        out.println("<tr>");
				        out.println("<td>" + product.getId() + "</td>");
				        out.println("<td>" + product.getCategory() + "</td>");
				        out.println("<td>" + product.getName() + "</td>");
				        out.println("<td>" + product.getQuantity() + "</td>");
				        out.println("<td>" + product.getPrice() + "</td>");
				        out.println("<td style='border: 1px solid black;'>");
					    out.println("<form action='RemoveFromCart' method='get' style='display: inline;'>");
					    out.println("<input type='hidden' name='id' value='" + product.getId() + "'>");
					    out.println("<input type='submit' value='Remove'>");
					    out.println("</form>");
				        out.println("</td>");
				        
				        out.println("</tr>");
				        totalPrice+=product.getPrice();
				    };
				    out.println("</table>");
				    out.println("<br>");
					out.println("<h3>Total Amount : "+totalPrice+"</h3>");
				out.println("<form action='cartSaveDatabase' method='post' style='display: inline;'> ");
				out.println("<input type='text' name='cname' required placeholder='Enter your Name' style='display: inline; '>");
				out.println("<input type='hidden' name='totalprice' value='"+totalPrice+"'>");
				out.println("<input type='submit' value='Checkout'>");
				out.println("</form>");
				}
				out.println("<button><a href='UserServlet' style='text-decoration: none; color: inherit;'>Continue Shoping</a></button>");
//				out.println("<button><a href='secondServlet' style='text-decoration: none; color: inherit;'>Checkout</a></button>");
//				 response.sendRedirect("secondServlet");
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
