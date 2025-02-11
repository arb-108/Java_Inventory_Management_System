

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class passwordChangeDatabase
 */
@WebServlet("/passwordChangeDatabase")
public class passwordChangeDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public passwordChangeDatabase() {
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
		String Musername= session.getAttribute("Musername").toString();
		String op=request.getParameter("op");
		String np1=request.getParameter("np1");
		String np2=request.getParameter("np2");
		boolean check=DatabaseClass.checkoldpass(Musername, op);
		if(check) {
			if(np1.trim().isEmpty() || np2.trim().isEmpty()) {
				session.setAttribute("msg","Password Change Failed");
				response.sendRedirect("cancelservlet");
				return;
			}
			if(np1.equals(np2)) {
				int rs=DatabaseClass.changeuserPass(Musername, np2);
				if(rs>0) {
					session.setAttribute("msg","Password Change Successfully");
					response.sendRedirect("cancelservlet");
					return;
				}else {
					session.setAttribute("msg","Password Change Failed");
					response.sendRedirect("cancelservlet");
					return;
				}
			}else {
				session.setAttribute("msg","Confirm Password Not Match.");
				response.sendRedirect("cancelservlet");
				return;
				
			}
		}else {
			session.setAttribute("msg","Incorrect Password");
			response.sendRedirect("cancelservlet");
			return;
		}
	}

}
