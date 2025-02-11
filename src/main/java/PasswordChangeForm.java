

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class PasswordChangeForm
 */
@WebServlet("/PasswordChangeForm")
public class PasswordChangeForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordChangeForm() {
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
		PrintWriter out=response.getWriter();
		out.println("<html>");
        out.println("<head><title>Dynamic Form Data</title></head>");
        out.println("<body>");
        out.println("<form action='passwordChangeDatabase' method='post'>");
        out.println("<h2>Change Your Password</h2>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<td>");
        out.println("Old Password :");
        out.println("</td>");
        out.println("<td>");
        out.println("<input type='text' name='op' required>");
        out.println("</td>");
        out.println("</tr>");
        
        out.println("<tr>");
        out.println("<td>");
        out.println("New Password :");
        out.println("</td>");
        out.println("<td>");
        out.println("<input type='text' name='np1' required>");
        out.println("</td>");
        out.println("</tr>");
        
        out.println("<tr>");
        out.println("<td>");
        out.println("Confirm New Password :");
        out.println("</td>");
        out.println("<td>");
        out.println("<input type='text' name='np2' required>");
        out.println("</td>");
        out.println("</tr>");
        
        
        out.println("<tr>");
        out.println("<td>");
        out.println("");
        out.println("</td>");
        out.println("<td>");
        out.println("<input type='submit' value='Change Password'>");
        out.println("<button><a href='cancelservlet' style='text-decoration: none; color: inherit;'>Cancel</a></button>");
        out.println("</td>");
        out.println("</tr>");
	    
	    out.println("</table>");
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
