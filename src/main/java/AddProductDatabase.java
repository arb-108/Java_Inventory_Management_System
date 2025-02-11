

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class AddProductDatabase
 */
@WebServlet("/AddProductDatabase")
public class AddProductDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductDatabase() {
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
        if (!"admin".equals(usertype)) {
            response.sendRedirect("index.html");
            return;
        }
	    	  String msg = "";
	    	  session.setAttribute("msg", msg);
	    	  int qty=-1;
	    	  Double price=-1.0;
	    	  int index=-1;
	    	  String id,category,name;
	    	   index=Integer.parseInt(request.getParameter("index"));
	    	   id=request.getParameter("id").trim();
	    	   category=request.getParameter("category").trim();
	    	   name=request.getParameter("name").trim();
	    	   try {
	    	  qty=Integer.parseInt(request.getParameter("qty"));
	    	   price=Double.parseDouble(request.getParameter("price"));
	    		    if (index > 0 && qty >= 0 && price >= 0 
	    		         && id != null && !id.isEmpty() 
	    		         && category != null && !category.isEmpty() 
	    		         && name != null && !name.isEmpty()) {
	    		        int rs = DatabaseClass.AddProduct(index,id,category,name, qty, price);
	    		        
	    		        if (rs > 0) {
	    		            msg = "ID = " + id + " Added successfully";
	    		            session.setAttribute("msg", msg);
	    		            response.sendRedirect("secondServlet");
	    		        } else {
	    		            msg = " Failed. Please try again.";
	    		            session.setAttribute("msg", msg);
	    		            response.sendRedirect("AddProductForm");
	    		        }
	    		        
	    		    } else {
	    		        session.setAttribute("msg", "Invalid Data Inserted. Please try again.");
	    		        response.sendRedirect("AddProductForm");
	    		    }
	    		} catch (Exception e) {
	    			session.setAttribute("msg", "Invalid Data Inserted. Please try again.");
	    			response.sendRedirect("secondServlet");
	    		}

	      
	}

}
