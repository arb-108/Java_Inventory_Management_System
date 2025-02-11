

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class registrantionMessagePage
 */
@WebServlet("/registrantionMessagePage")
public class registrantionMessagePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registrantionMessagePage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		out.println("<html>");
        out.println("<head><title>Dynamic Form Data</title></head>");
        out.println("<body>");
        String confirmation = (String) request.getAttribute("confirmation");

        if (confirmation != null) {
            out.println("<h1>" + confirmation + "</h1>");
            if ("Registration Successfully.".equals(confirmation)) {
                out.println("<button><a href='index.html' style='text-decoration: none; color: inherit;'>Go to Login Page</a></button>");
            } else {
                out.println("<button><a href='index.html' style='text-decoration: none; color: inherit;'>Go to Login Page</a></button>");
                out.println("<button><a href='register.html' style='text-decoration: none; color: inherit;'>Register Again</a></button>");
            }
        } else {
            out.println("<h1>Error: Confirmation message not available.</h1>");
            out.println("<button><a href='register.html' style='text-decoration: none; color: inherit;'>Back to Registration</a></button>");
        }
        out.println("</body>");
        out.println("</html>");
       
	}

}
