

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class AddProductForm
 */
@WebServlet("/AddProductForm")
public class AddProductForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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
		int maxid=DatabaseClass.maxIdfind();
		maxid++;
		String id="C"+maxid+"";
		PrintWriter out=response.getWriter();
		out.println("<html>");
      out.println("<head><title>Dynamic Form Data</title></head>");
      out.println("<body>");
      String msg = "";
	    if (session.getAttribute("msg") != null) {
	        msg = session.getAttribute("msg").toString();
	    }
	    out.println(msg);
	    session.setAttribute("msg", "");
      out.println("<form action='AddProductDatabase' method='post'>");
      out.println("<input type='hidden' name='index' value='" + maxid + "'>");
      out.println("<h2>Add New Product</h2>");
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
      out.println("<input type='text' name='category' required >");
      out.println("</td>");
      out.println("</tr>");
      
      out.println("<tr>");
      out.println("<td>");
      out.println("Name :");
      out.println("</td>");
      out.println("<td>");
      out.println("<input type='text' name='name' required>");
      out.println("</td>");
      out.println("</tr>");
      
      out.println("<tr>");
      out.println("<td>");
      out.println("Quantity :");
      out.println("</td>");
      out.println("<td>");
      out.println("<input type='text' name='qty'  required>");
      out.println("</td>");
      out.println("</tr>");
      
      out.println("<tr>");
      out.println("<td>");
      out.println("Price :");
      out.println("</td>");
      out.println("<td>");
      out.println("<input type='text' name='price' required>");
      out.println("</td>");
      out.println("</tr>");
      
      out.println("<tr>");
      out.println("<td>");
      out.println("");
      out.println("</td>");
      out.println("<td>");
      out.println("<input type='submit' value='Add Product'>");
      out.println("<button><a href='secondServlet' style='text-decoration: none; color: inherit;'>Cancel</a></button>");
      out.println("</td>");
      out.println("</tr>");
	    
	    out.println("</table>");
	    out.println("</form>");
	    
	    out.println("</body>");
	    out.println("</html>");
	     
	}

}
