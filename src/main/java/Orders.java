
public class Orders {
	private String oid,id,category,name,cname;
	private int qty;
	private double price,total;
	public Orders(String oid, String id, String category, String name, String cname, int qty, double price,double total) {
		super();
		this.oid = oid;
		this.id = id;
		this.category = category;
		this.name = name;
		this.cname = cname;
		this.qty = qty;
		this.price = price;
		this.total = total;
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
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}
