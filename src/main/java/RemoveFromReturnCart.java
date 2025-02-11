

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
 * Servlet implementation class RemoveFromReturnCart
 */
@WebServlet("/RemoveFromReturnCart")
public class RemoveFromReturnCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveFromReturnCart() {
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
			ArrayList<Product> items= (ArrayList<Product>) session.getAttribute("Returnitems");
			 PrintWriter out=response.getWriter();
				
				if(items==null || items.isEmpty()) {
					String msg = "";
					session.setAttribute("msg", "");
					response.sendRedirect("checkCartServlet");
				}else {
					String id=request.getParameter("id");
					int index=-1;
					for (int i = 0; i < items.size(); i++) {
				        if (items.get(i).getId().equals(id)) {
				             index=i;
				        }
				    }
					if(index!=-1) {
						items.remove(index);
					session.setAttribute("msg", "Item Removed");
					session.setAttribute("Returnitems", items);
					}
					response.sendRedirect("checkReturnCart");
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
