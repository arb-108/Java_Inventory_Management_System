

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class AddtocartServlet
 */
@WebServlet("/AddtocartServlet")
public class AddtocartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddtocartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("index.html");
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
		String msg = "";
		session.setAttribute("msg", msg);
		String id,category,name;
		int qty,quantity;
		double price;
		id=request.getParameter("id");
		category=request.getParameter("category");
		name=request.getParameter("name");
		qty=Integer.parseInt(request.getParameter("qty"));
		quantity=Integer.parseInt(request.getParameter("quantity"));
		price=Double.parseDouble(request.getParameter("price"));
		if(quantity>qty || quantity <=0) {
			msg = "Order Not Placed.Only"+qty+" items Left.";
			session.setAttribute("msg", msg);
		}else {
			@SuppressWarnings("unchecked")
			ArrayList<Product> items= (ArrayList<Product>) session.getAttribute("items");
			if(items==null) {
				items=new ArrayList<Product>();
			}
			price=price*quantity;
			int index=-1;
			for (int i = 0; i < items.size(); i++) {
		        if (items.get(i).getId().equals(id)) {
		             index=i;
		        }
		    }
			if(index!=-1) {
				int tempqyt=items.get(index).getQuantity();
				double tempprice=items.get(index).getPrice();
				tempqyt+=quantity;
				tempprice+=price;
				items.get(index).setQuantity(tempqyt);
				items.get(index).setPrice(tempprice);
			}else {	
			Product product=new Product(id, category, name, quantity, price);
			items.add(product);
			}
			session.setAttribute("items", items);
			msg = "Order Placed Successfully.Check Cart";
			session.setAttribute("msg", msg);
		}
		response.sendRedirect("UserServlet");
	   
	}

}
