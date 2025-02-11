

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.PasswordAuthentication;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Servlet implementation class Manageuser
 */
@WebServlet("/Manageuser")
public class Manageuser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manageuser() {
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
        ArrayList<user> users = DatabaseClass.allusers();

        if (users.isEmpty()) {
            session.setAttribute("msg", "Not Found");
            response.sendRedirect("secondServlet");
        } else {
            PrintWriter out = response.getWriter();
            String msg = (String) session.getAttribute("msg");
            msg = (msg == null) ? "" : msg;

            out.println("<html>");
            out.println("<head><title>Dynamic Form Data</title></head>");
            out.println("<body>");
            out.println(msg);
            session.setAttribute("msg", "");

            String fname = (String) session.getAttribute("fname");
            out.println("<h2>Name: " + fname + "</h2>");
            out.println("<button><a href='secondServlet' style='text-decoration: none; color: inherit;'>Go to Dashboard</a></button>");
            out.println("<button><a href='logoutServlet' style='text-decoration: none; color: inherit;'>Log Out</a></button>");
            out.println("<br><br>");

            out.println("<table style='border: 1px solid black;'>");
            out.println("<tr style='border: 1px solid black;'>");
            out.println("<th style='border: 1px solid black;'>Sr.</th>");
            out.println("<th style='border: 1px solid black;'>Name</th>");
            out.println("<th style='border: 1px solid black;'>Username</th>");
            out.println("<th style='border: 1px solid black;'>Role-Type</th>");
            out.println("<th style='border: 1px solid black;' colspan='2'>Controls</th>");
            out.println("</tr>");

            int index = 1;
            for (user user : users) {
                out.println("<tr style='border: 1px solid black;'>");
                out.println("<td style='border: 1px solid black;'>" + index + "</td>");
                out.println("<td style='border: 1px solid black;'>" + user.getName() + "</td>");
                out.println("<td style='border: 1px solid black;'>" + user.getUsername() + "</td>");
                out.println("<td style='border: 1px solid black;'>" + user.getUserType() + "</td>");
                out.println("<td style='border: 1px solid black;'>");
                out.println("<form action='removeuser' method='get' style='display: inline;'>");
                out.println("<input type='hidden' name='Username' value='" + user.getUsername() + "'>");
                out.println("<input type='hidden' name='name' value='" + user.getName() + "'>");
                out.println("<input type='submit' value='Remove'>");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
                index++;
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
