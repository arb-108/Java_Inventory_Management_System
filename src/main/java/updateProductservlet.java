

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class updateProductservlet
 */
@WebServlet("/updateProductservlet")
public class updateProductservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
	    	  session.setAttribute("msg", "");
		String id,category,name;
		int qty;
		double price;
		id=request.getParameter("id");
		category=request.getParameter("category");
		name=request.getParameter("name");
		qty=Integer.parseInt(request.getParameter("qty"));
		price=Double.parseDouble(request.getParameter("price"));
		PrintWriter out=response.getWriter();
		out.println("<html>");
        out.println("<head><title>Dynamic Form Data</title></head>");
        out.println("<body>");
        out.println("<form action='UpdateDatabaseServlet' method='post'>");
        out.println("<h2>Update Product Data</h2>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<td>");
        out.println("ID :");
        out.println("</td>");
        out.println("<td>");
        out.println("<input type='text' name='id' value='" + id + "' readonly>");
        out.println("</td>");
        out.println("</tr>");
        
        out.println("<tr>");
        out.println("<td>");
        out.println("Category :");
        out.println("</td>");
        out.println("<td>");
        out.println("<input type='text' name='category' value='" + category + "' readonly>");
        out.println("</td>");
        out.println("</tr>");
        
        out.println("<tr>");
        out.println("<td>");
        out.println("Name :");
        out.println("</td>");
        out.println("<td>");
        out.println("<input type='text' name='name' value='" + name + "' readonly size='" + name.length() + "'>");
        out.println("</td>");
        out.println("</tr>");
        
        out.println("<tr>");
        out.println("<td>");
        out.println("Quantity :");
        out.println("</td>");
        out.println("<td>");
        out.println("<input type='text' name='qty' value='" + qty + "' required>");
        out.println("</td>");
        out.println("</tr>");
        
        out.println("<tr>");
        out.println("<td>");
        out.println("Price :");
        out.println("</td>");
        out.println("<td>");
        out.println("<input type='text' name='price' value='" + price + "' required>");
        out.println("</td>");
        out.println("</tr>");
        
        out.println("<tr>");
        out.println("<td>");
        out.println("");
        out.println("</td>");
        out.println("<td>");
        out.println("<input type='submit' value='Update'>");
        out.println("<button><a href='secondServlet' style='text-decoration: none; color: inherit;'>Cancel</a></button>");
        out.println("</td>");
        out.println("</tr>");
	    
	    out.println("</table>");
	    out.println("</form>");
	    out.println("</body>");
	    out.println("</html>");
	      
	}

}
