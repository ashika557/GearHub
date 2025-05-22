package model;

public class cartModel {
	
	private int cartId;
	private int useriD;
	private int productId;
	private int quantity;
	private double Price;
	
	private String productName;
	private String productImage;
	
	
	//used while storing the data
	public cartModel(int cartId, int useriD, int productId, int quantity, double Price) {
		super();
		this.cartId = cartId;
		this.useriD = useriD;
		this.productId = productId;
		this.quantity = quantity;
		this.Price = Price;
	}
	
	//consstructor used to get the value to display
	public cartModel(int cartId, int useriD, int productId, int quantity, double price, String productName,
			String productImage) {
		super();
		this.cartId = cartId;
		this.useriD = useriD;
		this.productId = productId;
		this.quantity = quantity;
		Price = price;
		this.productName = productName;
		this.productImage = productImage;
	}

	public int getCartId() {
		return cartId;
	}
	
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	
	public int getUseriD() {
		return useriD;
	}
	
	public void setUseriD(int useriD) {
		this.useriD = useriD;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getTotalPrice() {
		return Price;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public void setTotalPrice(double totalPrice) {
		this.Price = totalPrice;
	}	
}