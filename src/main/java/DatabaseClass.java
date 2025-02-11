
import java.sql.*;
import java.util.ArrayList;
public  class DatabaseClass {
	private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/javaproject";
        return DriverManager.getConnection(url, "root", "");
//        String url = "jdbc:mysql://localhost:3306/javaproject";
//        return DriverManager.getConnection(url, "arb108", "arb108");
    }
//
//    public static User checkUser(String username, String pass) {
//        try (Connection con = getConnection();
//             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM `users` WHERE `Username` = ? AND `Password` = ?")) {
//            pstmt.setString(1, username);
//            pstmt.setString(2, pass);
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.next()) {
//                String userType = rs.getString("UserType");
//                String fname = rs.getString("Name");
//                String tempUsername = rs.getString("Username");
//                return new User(userType, fname, tempUsername);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
		public static user checkuser(String username,String pass){
			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				String url = "jdbc:mysql://localhost:3306/javaproject";
//				Connection con=DriverManager.getConnection(url,"root","");
				Connection con=getConnection();
				Statement st=con.createStatement();
				String queryString="Select * from `users` WHERE `Username`='"+username+"' AND `Password`='"+pass+"'";
				ResultSet rs=st.executeQuery(queryString);
				if(rs.next()) {
					String usertype=rs.getString("UserType");
					String fname=rs.getString("Name");
					String tempusername=rs.getString("Username");
					return new user( fname,tempusername,usertype);
				}else {
					return null;
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}
		public static int insertuser(String username,String pass,String fname) {
				try {
//					Class.forName("com.mysql.cj.jdbc.Driver");
//					String url = "jdbc:mysql://localhost:3306/javaproject";
//					Connection con=DriverManager.getConnection(url,"root","");
					Connection con=getConnection();
					String insertString = "INSERT INTO `users`(`Name`, `Username`, `Password`) VALUES (?,?,?)";
					PreparedStatement pstmt = con.prepareStatement(insertString);
				        pstmt.setString(1, fname);  
				        pstmt.setString(2, username); 
				        pstmt.setString(3, pass); 
				        int rs = pstmt.executeUpdate();
				        return rs;
				} catch (Exception e) {
					return -108;
					}
		}
		
		public static int AddProduct(int index,String id,String category,String name,int qty,double price) {
			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				String url = "jdbc:mysql://localhost:3306/javaproject";
//				Connection con=DriverManager.getConnection(url,"root","");
				Connection con=getConnection();
				String insertString = "INSERT INTO `product`(`index`, `id`, `category`, `name`, `qty`, `price`) VALUES (?,?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(insertString);
					pstmt.setInt(1, index);
			        pstmt.setString(2, id);  
			        pstmt.setString(3,category); 
			        pstmt.setString(4, name); 
			        pstmt.setInt(5, qty);
			        pstmt.setDouble(6, price);
			        int rs = pstmt.executeUpdate();
			        return rs;
			} catch (Exception e) {
				// TODO: handle exception
				}
		return 0;
	}
		public static ArrayList<Product> Allproducts() {
			 ArrayList<Product> productslist = new ArrayList<>();
			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				String url = "jdbc:mysql://localhost:3306/javaproject";
//				Connection con=DriverManager.getConnection(url,"root","");
				Connection con=getConnection();
				Statement st=con.createStatement();
//				String queryString="Select * from `product` ORDER BY `product`.`index` ASC";
				String queryString="SELECT * FROM `product` ORDER BY `product`.`index` ASC LIMIT 150";
				ResultSet rs=st.executeQuery(queryString);
				 while (rs.next()) {
					 String id = rs.getString("id");
					 String  name = rs.getString("name");
					 String  category = rs.getString("category");
					  int  qty = rs.getInt("qty");
					 double   price = rs.getDouble("price");

			            // Add user to the list
			            productslist.add(new Product(id,category,name,qty,price));
			        }
				
			} catch (Exception e) {
				// TODO: handle exception
				}
		return productslist;
	}
	
		public static int removeProduct(String id) {
			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				String url = "jdbc:mysql://localhost:3306/javaproject";
//				Connection con=DriverManager.getConnection(url,"root","");
				Connection con=getConnection();
				String insertString = "DELETE FROM `product` WHERE `id`=?";
				PreparedStatement pstmt = con.prepareStatement(insertString);
			        pstmt.setString(1, id);
			        int rs = pstmt.executeUpdate();
			        return rs;
			} catch (Exception e) {
				// TODO: handle exception
				}
		return 0;
	}
	
		public static ArrayList<Product> searchproduct(String sid) {
			ArrayList<Product> productslist = new ArrayList<>();
			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				String url = "jdbc:mysql://localhost:3306/javaproject";
//				Connection con=DriverManager.getConnection(url,"root","");
				Connection con=getConnection();
				Statement st=con.createStatement();
				String queryString="SELECT * FROM `product` WHERE `id` = '"+sid+"' OR `name` LIKE '%"+sid+"%'";
				ResultSet rs=st.executeQuery(queryString);
				 while (rs.next()) {
					 String id = rs.getString("id");
					 String  name = rs.getString("name");
					 String  category = rs.getString("category");
					  int  qty = rs.getInt("qty");
					 double   price = rs.getDouble("price");

			            // Add user to the list
			            productslist.add(new Product(id,category,name,qty,price));
			        }
			} catch (Exception e) {
				// TODO: handle exception
				}
		return productslist;
	}
	
		public static int updateProduct(String id,int qty,double price) {
			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				String url = "jdbc:mysql://localhost:3306/javaproject";
//				Connection con=DriverManager.getConnection(url,"root","");
				Connection con=getConnection();
				String insertString = "UPDATE `product` SET `qty` = ?, `price` = ? WHERE `id` = ?";
				PreparedStatement pstmt = con.prepareStatement(insertString);
			        pstmt.setString(3, id);  
			        pstmt.setInt(1, qty); 
			        pstmt.setDouble(2, price); 
			        int rs = pstmt.executeUpdate();
			        return rs;
			} catch (Exception e) {
				// TODO: handle exception
				}
		return 0;
	}
		public static int maxIdfind() {
			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				String url = "jdbc:mysql://localhost:3306/javaproject";
//				Connection con=DriverManager.getConnection(url,"root","");
				Connection con=getConnection();
				Statement st=con.createStatement();
				String queryString="SELECT MAX(`index`) AS max_value FROM `product`";
				ResultSet rs=st.executeQuery(queryString);
				int id_value=-1;
				if(rs.next()) {
					id_value=rs.getInt("max_value");
				}
				return id_value;
			} catch (Exception e) {
				// TODO: handle exception
				}
		return -1;
	}
//		public static ResultSet allusers(){
//			try {
////				Class.forName("com.mysql.cj.jdbc.Driver");
////				String url = "jdbc:mysql://localhost:3306/javaproject";
////				Connection con=DriverManager.getConnection(url,"root","");
////				Statement st=con.createStatement();
//				Connection con=getConnection();
//				String queryString="SELECT * FROM `users` WHERE `UserType` = ?";
//				PreparedStatement pstmt = con.prepareStatement(queryString);
//				pstmt.setString(1,"user");
//				ResultSet rs=pstmt.executeQuery();
//				return rs;
//			}catch (Exception e) {
//				return null;// TODO: handle exception
//			}
//			
//		}
		public static ArrayList<user> allusers() {
		    ArrayList<user> userList = new ArrayList<>();

		    try {
		        Connection con = getConnection();
		        String queryString = "SELECT * FROM `users` WHERE `UserType` = ?";
		        PreparedStatement pstmt = con.prepareStatement(queryString);
		        pstmt.setString(1, "user");
		        ResultSet rs = pstmt.executeQuery();

		        while (rs.next()) {
		            String name = rs.getString("Name");
		            String username = rs.getString("Username");
		            String userType = rs.getString("UserType");

		            // Add user to the list
		            userList.add(new user(name, username, userType));
		        }

		        // Close resources
		        rs.close();
		        pstmt.close();
		        con.close();

		    } catch (Exception e) {
		        e.printStackTrace(); // Log exception for debugging
		    }

		    return userList;
		}
		public static int removeUser(String id) {
			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				String url = "jdbc:mysql://localhost:3306/javaproject";
//				Connection con=DriverManager.getConnection(url,"root","");
				Connection con=getConnection();
				String insertString = "DELETE FROM `users` WHERE `Username`=?";
				PreparedStatement pstmt = con.prepareStatement(insertString);
			        pstmt.setString(1, id);
			        int rs = pstmt.executeUpdate();
			        return rs;
			} catch (Exception e) {
				// TODO: handle exception
				}
		return 0;
	}
		public static int maxIndexfind() {
			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				String url = "jdbc:mysql://localhost:3306/javaproject";
//				Connection con=DriverManager.getConnection(url,"root","");
				Connection con=getConnection();
				Statement st=con.createStatement();
				String queryString="SELECT MAX(`index`) AS max_value FROM `orders`";
				ResultSet rs=st.executeQuery(queryString);
				int id_value=-1;
				if(rs.next()) {
					id_value=rs.getInt("max_value");
				}
				return id_value;
			} catch (Exception e) {
				// TODO: handle exception
				}
		return -1;
	}
		public static int AddOrder(ArrayList<Product> items,String oid,String cname,double totalprice) {
			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				String url = "jdbc:mysql://localhost:3306/javaproject";
//				Connection con=DriverManager.getConnection(url,"root","");
				Connection con=getConnection();
				String insertString = "INSERT INTO `orders`( `oid`, `id`, `cname`, `category`, `name`, `quantity`, `price`, `totalprice`) VALUES (?,?,?,?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(insertString);
				String uPString = "UPDATE `product` SET `qty` = `qty` - ? WHERE `id` = ?";
			    PreparedStatement uPstmt = con.prepareStatement(uPString);
				int rs=-1;
				for(Product product:items) {
					
					pstmt.setString(1, oid);
					pstmt.setString(2, product.getId());
			        pstmt.setString(3, cname);  
			        pstmt.setString(4,product.getCategory()); 
			        pstmt.setString(5,product.getName()); 
			        pstmt.setInt(6, product.getQuantity());
			        pstmt.setDouble(7, product.getPrice());
			        pstmt.setDouble(8, totalprice);
			         rs = pstmt.executeUpdate();
			         if (rs > 0) { 
			             uPstmt.setInt(1, product.getQuantity()); 
			             uPstmt.setString(2, product.getId()); 
			             uPstmt.executeUpdate();
			         }
				}
			        return rs;
			} catch (Exception e) {
				// TODO: handle exception
				}
		return 0;
	}
		public static ArrayList<Orders> ordersDetail(String roid) {
			ArrayList<Orders> orders=new ArrayList<Orders>();
			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				String url = "jdbc:mysql://localhost:3306/javaproject";
//				Connection con=DriverManager.getConnection(url,"root","");
				Connection con=getConnection();
				Statement st=con.createStatement();
//				String queryString="SELECT * FROM `orders` WHERE `oid` = '"+oid+"' ";
				String queryString="SELECT * FROM `orders` WHERE `oid` = '"+roid+"' AND `quantity` > 0";
				ResultSet rs=st.executeQuery(queryString);
				while (rs.next()) {
					String	oid=rs.getString("oid");
					String	cname=rs.getString("cname");
					String    id = rs.getString("id");
					String    name = rs.getString("name");
					String    category = rs.getString("category");
					int    qty = rs.getInt("quantity");
					double    price = rs.getDouble("price");
					 double   total = rs.getDouble("totalprice");
					 orders.add(new Orders(oid, id, category, name, cname, qty, price, total));
					 }
			} catch (Exception e) {
				
				}
		return orders;
	}
		public static ArrayList<returns> returnDetail(String roid) {
			ArrayList<returns> returns=new ArrayList<returns>();
			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				String url = "jdbc:mysql://localhost:3306/javaproject";
//				Connection con=DriverManager.getConnection(url,"root","");
				Connection con=getConnection();
				Statement st=con.createStatement();
				String queryString="SELECT * FROM `returns` WHERE `rid` = '"+roid+"' ";
				ResultSet rs=st.executeQuery(queryString);
				while (rs.next()) {
					String	rid=rs.getString("rid");
					String	oid=rs.getString("oid");
					String	cname=rs.getString("cname");
					String    id = rs.getString("id");
					String    name = rs.getString("name");
					String    category = rs.getString("category");
					int    qty = rs.getInt("quantity");
					double    price = rs.getDouble("price");
					double    total = rs.getDouble("totalprice");
					returns.add(new returns(rid, oid, id, category, name, cname, qty, price, total));
					}
			} catch (Exception e) {
				// TODO: handle exception
				}
		return returns;
	}
		public static int Addreturns(ArrayList<Product> items,String rid,String cname,double totalprice) {
			try {
//			    Class.forName("com.mysql.cj.jdbc.Driver");
//			    String url = "jdbc:mysql://localhost:3306/javaproject";
//			    Connection con = DriverManager.getConnection(url, "root", "");
				Connection con=getConnection();

			    String insertString = "INSERT INTO `returns` (`oid`, `id`, `cname`, `category`, `name`, `quantity`, `price`, `totalprice`,`rid`) VALUES (?,?,?,?,?,?,?,?,?)";
			    PreparedStatement pstmt = con.prepareStatement(insertString);

			    String uPString = "UPDATE `product` SET `qty` = `qty` + ? WHERE `id` = ?";
			    PreparedStatement uPstmt = con.prepareStatement(uPString);

			    String orderString = "UPDATE `orders` SET `quantity` = `quantity` - ?, `price` = `price` - ? WHERE `oid` = ? AND `id` = ?";
			    PreparedStatement orderPstmt = con.prepareStatement(orderString);

			    int rs = -1;
			    for (Product product : items) {
			        pstmt.setString(1, product.getOid());
			        pstmt.setString(2, product.getId());
			        pstmt.setString(3, cname);
			        pstmt.setString(4, product.getCategory());
			        pstmt.setString(5, product.getName());
			        pstmt.setInt(6, product.getQuantity());
			        pstmt.setDouble(7, product.getPrice());
			        pstmt.setDouble(8, totalprice);
			        pstmt.setString(9, rid);
			        rs = pstmt.executeUpdate();

			        if (rs > 0) {
			            uPstmt.setInt(1, product.getQuantity());
			            uPstmt.setString(2, product.getId());
			            rs = uPstmt.executeUpdate();

			            if (rs > 0) {
			                
			                orderPstmt.setInt(1, product.getQuantity());
			                orderPstmt.setDouble(2, product.getPrice());
			                orderPstmt.setString(3, product.getOid());
			                orderPstmt.setString(4, product.getId());
			                rs = orderPstmt.executeUpdate();
			            }
			        }
			    }
			    return rs;
			} catch (Exception e) {
				return -1;
			}
		
	}
		public static ArrayList<Orders> Allorders() {
			ArrayList<Orders> orders=new ArrayList<>();
			try {
				
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				String url = "jdbc:mysql://localhost:3306/javaproject";
//				Connection con=DriverManager.getConnection(url,"root","");
				Connection con=getConnection();
				Statement st=con.createStatement();
				String queryString="Select * from `orders`";
				ResultSet rs=st.executeQuery(queryString);
				while (rs.next()) {
				String	oid=rs.getString("oid");
				String	cname=rs.getString("cname");
				String    id = rs.getString("id");
				String    name = rs.getString("name");
				String    category = rs.getString("category");
				int    qty = rs.getInt("quantity");
				double    price = rs.getDouble("price");
				 double   total = rs.getDouble("totalprice");
				 orders.add(new Orders(oid, id, category, name, cname, qty, price, total));
				 }
			} catch (Exception e) {
				// TODO: handle exception
				}
		return orders;
	}
		public static ArrayList<returns> Allreturns() {
			ArrayList<returns> returns=new ArrayList<returns>();
			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				String url = "jdbc:mysql://localhost:3306/javaproject";
//				Connection con=DriverManager.getConnection(url,"root","");
				Connection con=getConnection();
				Statement st=con.createStatement();
				String queryString="Select * from `returns`";
				ResultSet rs=st.executeQuery(queryString);
				while (rs.next()) {
				String	rid=rs.getString("rid");
				String	oid=rs.getString("oid");
				String	cname=rs.getString("cname");
				String    id = rs.getString("id");
				String    name = rs.getString("name");
				String    category = rs.getString("category");
				int    qty = rs.getInt("quantity");
				double    price = rs.getDouble("price");
				double    total = rs.getDouble("totalprice");
				returns.add(new returns(rid, oid, id, category, name, cname, qty, price, total));
				}
			} catch (Exception e) {
				// TODO: handle exception
				}
		return returns;
	}
		public static int maxIndexfindreturns() {
			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				String url = "jdbc:mysql://localhost:3306/javaproject";
//				Connection con=DriverManager.getConnection(url,"root","");
				Connection con=getConnection();
				Statement st=con.createStatement();
				String queryString="SELECT MAX(`index`) AS max_value FROM `returns`";
				ResultSet rs=st.executeQuery(queryString);
				int id_value=-1;
				if(rs.next()) {
					id_value=rs.getInt("max_value");
				}
				return id_value;
			} catch (Exception e) {
				// TODO: handle exception
				}
		return -1;
	}
		public static boolean checkoldpass(String username,String pass){
			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				String url = "jdbc:mysql://localhost:3306/javaproject";
//				Connection con=DriverManager.getConnection(url,"root","");
				Connection con=getConnection();
				Statement st=con.createStatement();
				String queryString="Select * from `users` WHERE `Username`='"+username+"' AND `Password`='"+pass+"'";
				ResultSet rs=st.executeQuery(queryString);
				if(rs.next()) {
					
					return true;
				}else {
					return false;
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			return false;
		}
		
		
		public static int changeuserPass(String username,String pass) {
			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				String url = "jdbc:mysql://localhost:3306/javaproject";
//				Connection con=DriverManager.getConnection(url,"root","");
				Connection con=getConnection();
				String insertString = "UPDATE `users` SET `Password`=? WHERE `Username`=?";
				PreparedStatement pstmt = con.prepareStatement(insertString);  
					pstmt.setString(1, pass); 
			        pstmt.setString(2, username); 
			        int rs = pstmt.executeUpdate();
			        return rs;
			} catch (Exception e) {
				// TODO: handle exception
				}
		return 0;
	}
}
