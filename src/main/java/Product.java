
public class Product {
	private String oid;
	private String id;
    private String category;
    private String name;
    private int quantity;
    private double price;
	public Product(String oid,String id, String category, String name, int quantity, double price) {
		this.oid=oid;
		this.id = id;
		this.category = category;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	public Product(String id, String category, String name, int quantity, double price) {
        this("e", id, category, name, quantity, price);
    }
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

    
}
