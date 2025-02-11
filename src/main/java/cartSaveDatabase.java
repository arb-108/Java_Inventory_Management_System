

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class cartSaveDatabase
 */
@WebServlet("/cartSaveDatabase")
public class cartSaveDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cartSaveDatabase() {
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

	        String usertype = session.getAttribute("usertype").toString();
	        if (!"user".equals(usertype)) {
	            response.sendRedirect("index.html");
	            return;
	        }
			 @SuppressWarnings("unchecked")
			ArrayList<Product> items= (ArrayList<Product>) session.getAttribute("items");
			
			    String msg = "";
			    session.setAttribute("msg", "");
				if(items==null || items.isEmpty()) {
					
				}else {
					int maxindex=DatabaseClass.maxIndexfind();
					maxindex++;
					String invoice="C"+maxindex+"";
					String cnameString=request.getParameter("cname");
					double total=Double.parseDouble(request.getParameter("totalprice"));
					int rs=DatabaseClass.AddOrder(items, invoice, cnameString, total);
					if(rs>0) {
						session.setAttribute("msg", "Order Placed. Order Id = "+invoice+"");
						items.clear();
						session.setAttribute("items", items);
						response.sendRedirect("UserServlet");
					}
				}
					
		 
	}

}
