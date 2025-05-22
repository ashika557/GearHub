package model;
import java.io.File;
//import java.time.LocalDate;
import jakarta.servlet.http.Part;

public class ProductModel {
	private int productId;
	private String productName;
	private String imageUrl;
	private String Brand;
	private String Model;
	private String Screen_size;
	private double price;
	private int quantity;
	
	public ProductModel(String productName, Part imagePart, String brand, String model, String screen_size,
			double price, int quantity) {
		super();
		this.productName = productName;
		this.imageUrl = getImageUrl(imagePart);
		Brand = brand;
		Model = model;
		Screen_size = screen_size;
		this.price = price;
		this.quantity = quantity;
	}
	
	public ProductModel(int productId, String productName, String Model, String image, String Brand, String screen_size, double price,
			int quantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.Model = Model;
		this.imageUrl = image;
		this.Brand = Brand;
		this.Screen_size = screen_size;
		this.price = price;
		this.quantity = quantity;
	}

	//constructor that is used to get thevalues for editing the products
	public ProductModel(int product_id, String product_name, String model, String brand, String screen_size,
			double price, int quantity) {
		super();
		this.productId = product_id;
		this.productName = product_name;
		this.Model = model;
		this.Brand = brand;
		this.Screen_size = screen_size;
		this.price = price;
		this.quantity = quantity;
		// TODO Auto-generated constructor stub
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		this.Brand = brand;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		this.Model = model;
	}

	public String getScreen_size() {
		return Screen_size;
	}

	public void setScreen_size(String screen_size) {
		this.Screen_size = screen_size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	private String getImageUrl(Part imagePart) {
		String savePath = "C:\\Users\\acer\\eclipse-workspace\\GearHub\\src\\main\\webapp\\images\\";
		File fileSaveDir = new File(savePath);
		String imageUrlFromPart = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String contentDisp = imagePart.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
			imageUrlFromPart = "default.jpg";
		}
		return imageUrlFromPart;
	}

	public String getImageUrlFromPart() {
		return imageUrl;
	}

	public void setImageUrlFromPart(Part part) {
		this.imageUrl = getImageUrl(part);
	}
}
