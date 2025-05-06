package util;

import java.io.File;

public class stringUtils {
	
//	Database queries used in the project
	public static final String INSERT_PRODUCT = "INSERT INTO products" 
					+ "(productName, imageUrl, brand, model, screen_size, price, quantity)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	public static final String ADD_FEEDBACK = "INSERT INTO feedback (userName, email,feedback) VALUES (?, ?, ?)";
	public static final String LOGIN_QUERY = "SELECT * from users where username = ?";
	public static final String INSERT_USER = "INSERT INTO users (username, address, password, email) VALUES (?, ?, ?, ?)";
	public static final String Get_Details = "SELECT * from products";
	public static final String GET_PRODUCT_NAME = "SELECT * FROM products WHERE productName = ?";
	public static final String GET_USER_DETAIL_BY_USERNMAE = "SELECT * from users where username = ?";
	public static final String Get_PRODUCT_BY_ID = "SELECT * from products where productId=?";
	public static final String UPDATE_PRODUCT = "UPDATE products SET product_name=?, Brand=?, Model=?,Screen_size=?,price=?,quantity=? where productId=?";
	public static final String UPDATE_PROFILE = "UPDATE users SET username=?, address=?, password=?,email=? where userID=?";
	public static final String GET_ALL_FEEDBACK = "SELECT * from feedback";
	public static final String GET_USER_ID = "SELECT userid FROM users WHERE username = ?";
	public static final String CHECK_CART_ITEM ="SELECT quantity, price FROM cart WHERE userId = ? AND productId = ?";
	public static final String CART_UPDATE = "UPDATE cart SET quantity = ?, price = ? WHERE userId = ? AND productId = ?";
	public static final String ADD_ITEM_TO_CART = "INSERT INTO cart (userid, quantity, productid, price) VALUES (?, ?, ?, ?)";
	public static final String GET_ALL_CART_ITEMS ="SELECT cart.cartid, cart.userid, cart.quantity, cart.productid, cart.price, " +
     											  "products.productName, products.imageUrl " +
     											  "FROM cart " +
     		                                      "JOIN products ON cart.productid = products.productid " +
     		                                      "WHERE cart.userid = ?";
//	query related t reomovig cart items;
	public static final String GET_CART_QUNATITY = "SELECT quantity, productId FROM cart WHERE cartId = ?";
	public static final String UPDATE_CART_DETAILS = "UPDATE cart SET quantity = quantity - 1, price = price - (SELECT price FROM products WHERE productId = ?) WHERE cartId = ?";
	public static final String DELETE_CART = "DELETE FROM cart WHERE cartId = ?";
//	setting imagepath in a variable
	public static final String IMAGE_DIR = "Users\\acer\\eclipse-workspace\\GearHub\\src\\main\\webapp\\images\\";
	public static final String IMAGE_DIR_PATH = "D:\\java servlet\\demo\\src\\main\\webapp\\images";
;
	
//  String utils for user variables
	
	
//	String utils for product variables
	public static final String PRODUCT_NAME = "productName";
	public static final String BRAND = "brand";
	public static final String MODEL = "model";
	public static final String SCREEN_SIZE = "screenSize";
	public static final String PRICE = "price";
	public static final String QUANTITY = "quantity";
	public static final String IMAGE = "image";	
	
//	String utils for feedback variables
	public static final String USERNAME = "senderName";
	public static final String USER_EMAIL = "senderEmail";
	public static final String FEEDBACK = "feedback";
	
	public static final String SUCESS_MESSAGE = "sucess";
	public static final String ERROR_MESSAGE = "error";	
	
//	String utils for messages
    public static final String LOGIN_DETEAIL_ERROR = "Username or Password Incorrect !";
	public static final String SERVER_ERROR_MESSAGE = "An unexpected server error occurred !";

}

