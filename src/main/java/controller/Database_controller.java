package controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.*;
import util.stringUtils;

public class Database_controller {
	
	 public Connection getConnection() throws SQLException, ClassNotFoundException {
	        Class.forName("com.mysql.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/gearhub";
	        String user = "root";
	        String pass = "";
	        return DriverManager.getConnection(url, user, pass);
	    }
	 
//	 code for recoding the product details into the database
	 public int addProduct(ProductModel productModel) {
		    int result;

		    try (Connection con = getConnection()) {
		        PreparedStatement st = con.prepareStatement(stringUtils.INSERT_PRODUCT);

		        st.setString(1, productModel.getProductName());
		        st.setString(2, productModel.getImageUrlFromPart());
		        st.setString(3, productModel.getBrand());
		        st.setString(4, productModel.getModel());
		        st.setString(5, productModel.getScreen_size());
		        st.setDouble(6, productModel.getPrice());
		        st.setInt(7, productModel.getQuantity());
		        
		        System.out.println(st);

		        result = st.executeUpdate();

		    } catch (SQLException | ClassNotFoundException ex) { 
	            ex.printStackTrace(); 
	            return -2;
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            return -1;
	        }

		    return result; 
		}
	 
//	 code that fetches data form the database
	 public List<ProductModel> getAllProducts() throws ClassNotFoundException{
		  List<ProductModel> products = new ArrayList<>();
		  
		 try (Connection con = getConnection()) {
			 
			 PreparedStatement st = con.prepareStatement(stringUtils.Get_Details);
		     ResultSet rs = st.executeQuery();
		     
	            while (rs.next()) {
	                int product_id = rs.getInt(1);
	                String product_name = rs.getString(2);
	                String image = rs.getString(3);
	                String Brand = rs.getString(4);
	                String Model = rs.getString(5);
	                String Sceen_size = rs.getString(6);
	                int price = rs.getInt(7);
	                int qunatity = rs.getInt(8);
	                
	                ProductModel prodcuct = new ProductModel( product_id, product_name, Model, image, Brand, Sceen_size, price ,qunatity);
	                products.add(prodcuct);
	                }
		 }catch(SQLException e) {
			  e.printStackTrace(); 
		 }
		 catch( ClassNotFoundException d) {
			 d.printStackTrace();
		 }
		 return products;
	 }
	 
//	 code to register user into the database 
	 public int registerUser(userModel user) {
		    int result;

		    try (Connection con = getConnection()) {
		        PreparedStatement st = con.prepareStatement(stringUtils.INSERT_USER);

		        st.setString(1, user.getUserName());
		        st.setString(2, user.getAddress());
		        st.setString(3, user.getPassword());
		        st.setString(4, user.getEmail());


		        result = st.executeUpdate();

		    } catch (SQLException | ClassNotFoundException ex) {
		        ex.printStackTrace();
		        return -2;

		    } catch (Exception e) {
		        e.printStackTrace();
		        return -1;
		    }

		    return result;
		}
	 
	 //checking username existence
	 public boolean doesUserExist(String userName) {
		    boolean userExists = false;

		    try (Connection con = getConnection()) {
		        PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) AS count FROM users WHERE username = ?");
		        stmt.setString(1, userName);
		        ResultSet rs = stmt.executeQuery();

		        if (rs.next()) {
		            int count = rs.getInt("count");
		            userExists = count > 0;
		        }
		    } catch (SQLException | ClassNotFoundException ex) {
		        ex.printStackTrace();
		        // You might want to handle this exception differently based on your application's requirements
		    }

		    return userExists;
		}

	 
	 //method to check login info
	 public int getUserLogin(String Username, String Password) {
		 
		    String AES_KEY = "E2C24C4957DE9E75F0A53ECD1079A9F8";
		    
			try (Connection con = getConnection()) {
				PreparedStatement st = con.prepareStatement(stringUtils.LOGIN_QUERY);
				st.setString(1, Username);
				ResultSet result = st.executeQuery();

				if (result.next()) {
					String password = result.getString("password");
					
			        String decryptedPassword = null;
					try {
						decryptedPassword = PasswordEncryptionAes.decryptPassword(password, AES_KEY);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if (Password.equals(decryptedPassword)) {
						int role = result.getInt("role");
						if (role == 1) {
							return 1;// indicates that the user is admin

						} else if (role == 0) {
							return 2; // indicates that the user type is user
						}
					}
				}
				return 0; // Username or password is incorrect
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
				return -1; // Server error or exception
			}
		}
	 
	 //method used to update product
	 public int updateProfile(userModel users ,int userId ) {
		 int result;
		 try (Connection con = getConnection()) {
		        PreparedStatement stat = con.prepareStatement(stringUtils.UPDATE_PROFILE);
		        
		        stat.setString(1, users.getUserName());
		        stat.setString(2, users.getAddress());
		        stat.setString(3, users.getPassword());
		        stat.setString(4, users.getEmail());
		        stat.setInt(5, userId);
		        System.out.println(stat);
		        result = stat.executeUpdate();
		        System.out.println(result);
		    } catch (SQLException | ClassNotFoundException ex) {
		        ex.printStackTrace();
		        return -2;

		    } catch (Exception e) {
		        e.printStackTrace();
		        return -1;
		    }

		    return result;	 
	 }
	 
	 
	 //method to delete the produt using id
	 public int deleteProduct(int productId) throws SQLException, ClassNotFoundException {
		 try(Connection con = getConnection()){
			 PreparedStatement st = con.prepareStatement("DELETE FROM products WHERE productId=?");
			 st.setInt(1, productId);
			 int result = st.executeUpdate();
//			 System.out.println("Result Set: " + result);
			 return result;
		 }catch(SQLException e){
			 return -1;	 
		 }
	 }

	 // method to get product accroding to the id
	 public List<ProductModel> getProductByID(int produdctId) throws ClassNotFoundException{
		 List<ProductModel> products = new ArrayList<>(); 
		 try (Connection con = getConnection()) {
			 
			 PreparedStatement stat = con.prepareStatement(stringUtils.Get_PRODUCT_BY_ID);
			 stat.setInt(1, produdctId);
		     ResultSet rs = stat.executeQuery();
		     
	            while (rs.next()) {
	                int product_id = rs.getInt(1);
	                String product_name = rs.getString(2);
	                String image = rs.getString(3);
	                String Brand = rs.getString(4);
	                String Model = rs.getString(5);
	                String Sceen_size = rs.getString(6);
	                double price = rs.getDouble(7);
	                int qunatity = rs.getInt(8);
	                
	                ProductModel prodcuct = new ProductModel( product_id, product_name, Model, image, Brand, Sceen_size, price, qunatity);
	                products.add(prodcuct);
	            }
		 }catch(SQLException e) {
			  e.printStackTrace(); 
		 }
		 catch( ClassNotFoundException d) {
			 d.printStackTrace();
		 }
		 return products;
	 }
	 
	 // Method to update product details
	 public int updateProduct(ProductModel product, int productId) {
		 try(Connection con = getConnection()) {
			 PreparedStatement st = con.prepareStatement("UPDATE products SET productname=?, brand=?, model=?, screen_size=?, price=?, quantity=? WHERE productid=?");
			 st.setString(1, product.getProductName());
			 st.setString(2, product.getBrand());
			 st.setString(3, product.getModel());
			 st.setString(4, product.getScreen_size());
			 st.setDouble(5, product.getPrice());
			 st.setInt(6, product.getQuantity());
			 st.setInt(7, productId);

			 int result = st.executeUpdate();
			 return result;
		 } catch (SQLException | ClassNotFoundException e) {
			 e.printStackTrace();
			 return -1;
		 }
	 }
	 
	 public  List<userModel> getUserDetialsByName(String username) throws ClassNotFoundException, SQLException {
		 
		 List<userModel> user = new ArrayList<>(); 
		 String AES_KEY = "E2C24C4957DE9E75F0A53ECD1079A9F8";

		  try (Connection con = getConnection()) {
			  PreparedStatement st = con.prepareStatement(stringUtils.GET_USER_DETAIL_BY_USERNMAE);
			  st.setString(1, username);
			  ResultSet rs = st.executeQuery();
			  if (rs.next()) {
				  int userId = rs.getInt(1);
				  String userName = rs.getString(2);
				  String address = rs.getString(3);
				  String encryptdPass = rs.getString(4);
				  String email = rs.getString(6);
				  
				  String decryptedPassword = null;
					try {
						decryptedPassword = PasswordEncryptionAes.decryptPassword(encryptdPass, AES_KEY);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				  
				  
				  userModel users = new userModel(userId,userName, address, decryptedPassword, email);
				  user.add(users);
			  }
		  }
		  catch (Exception e) {
		        e.printStackTrace();
		    }
		return user;
		
	}
	 
	 // method that is used to insert the user feedback into the database 
	 public int recordFeedback(feedbackModel feed) {
		    int result;

		    try (Connection con = getConnection()) {
		    	 PreparedStatement st = con.prepareStatement(stringUtils.ADD_FEEDBACK);
		        st.setString(1, feed.getUserName());
		        st.setString(2, feed.getEmail());
		        st.setString(3, feed.getFeedback());
		        
		        System.out.println(st);

		        result = st.executeUpdate();
		       
		        st.close();
		        con.close();
		        
		    } catch (SQLException | ClassNotFoundException ex) { 
	            ex.printStackTrace(); 
	            return -2;
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            return -1;
	        }

		    return result; 
		}
	 
	 public List<feedbackModel> getFeedbacks() throws ClassNotFoundException{
		 
		  List<feedbackModel> feed = new ArrayList<>();
		  
		 try (Connection con = getConnection()) {
			 
			 PreparedStatement st = con.prepareStatement(stringUtils.GET_ALL_FEEDBACK);
		     ResultSet rs = st.executeQuery();
		     
	            while (rs.next()) {
	                String name = rs.getString(2);
	                String email = rs.getString(3);
	                String feedback = rs.getString(4);

	                
	                feedbackModel feedbacks = new feedbackModel(name, email, feedback);
	                feed.add(feedbacks);    
	                System.out.println(feed);
	                }
		 }catch(SQLException e) {
			  e.printStackTrace(); 
		 }
		 catch( ClassNotFoundException d) {
			 d.printStackTrace();
		 }
		 return feed;
	 }
	 
	 public int deleteFeedback(String userName, String email, String feedback) throws ClassNotFoundException {
		 try (Connection con = getConnection()) {
			 PreparedStatement st = con.prepareStatement(stringUtils.DELETE_FEEDBACK);
			 st.setString(1, userName);
			 st.setString(2, email);
			 st.setString(3, feedback);
			 return st.executeUpdate();
		 } catch (SQLException e) {
			 e.printStackTrace();
			 return -1;
		 }
	 }
	 
	 public int getUserIDByUsername(String username) throws ClassNotFoundException {
	        int userID = -1;
	       
	        try (Connection con = getConnection()) {
	           PreparedStatement stmt = con.prepareStatement(stringUtils.GET_USER_ID);
	                stmt.setString(1, username);
	               ResultSet rs = stmt.executeQuery();
	                    if (rs.next()) {
	                        userID = rs.getInt("userid");
	                    }
	         }
	         catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return userID;
	    }
	 
	 public int addToCart(int userID, int productId, int quantity, double totalPrice) throws ClassNotFoundException {
		    // Check if the product already exists in the cart for the given user
		    boolean productExists = false;
		    int existingQuantity = 0;
		    double existingTotalPrice = 0;

		    try (Connection con = getConnection()) {
		        // Query to check if the product exists in the cart
		        String checkQuery = "SELECT quantity, price FROM cart WHERE userid = ? AND productid = ?";
		        try (PreparedStatement checkStmt = con.prepareStatement(stringUtils.CHECK_CART_ITEM)) {
		            checkStmt.setInt(1, userID);
		            checkStmt.setInt(2, productId);
		            try (ResultSet rs = checkStmt.executeQuery()) {
		                if (rs.next()) {
		                    productExists = true;
		                    existingQuantity = rs.getInt("quantity");
		                    existingTotalPrice = rs.getDouble("price");
		                }
		            }
		        }
		        if (productExists) {
		            // checking weather the product exists, update the quantity and total price
		            int newQuantity = existingQuantity + quantity;
		            double newTotalPrice = existingTotalPrice + (quantity * totalPrice);
		            String updateQuery = "UPDATE cart SET quantity = ?, price = ? WHERE userid = ? AND productid = ?";
		            try (PreparedStatement updateStmt = con.prepareStatement(stringUtils.CART_UPDATE)) {
		                updateStmt.setInt(1, newQuantity);
		                updateStmt.setDouble(2, newTotalPrice);
		                updateStmt.setInt(3, userID);
		                updateStmt.setInt(4, productId);
		                return updateStmt.executeUpdate();
		            }
		        } else {
		            // If the product doesn't exist, insert a new row
		            String insertQuery = "INSERT INTO cart (userid, quantity, productid, price) VALUES (?, ?, ?, ?)";
		            try (PreparedStatement insertStmt = con.prepareStatement(stringUtils.ADD_ITEM_TO_CART)) {
		                insertStmt.setInt(1, userID);
		                insertStmt.setInt(2, quantity);
		                insertStmt.setInt(3, productId);
		                insertStmt.setDouble(4, quantity * totalPrice);
		                return insertStmt.executeUpdate();
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return -1; // Return -1 if an error occurs
		}	 
	 
	// Method to fetch cart items by userID from the database using SQL join
	 public List<cartModel> getCartItemsByUserID(int userID) throws ClassNotFoundException {
	     List<cartModel> cartItems = new ArrayList<>();
	     
	     String query = "SELECT cart.cartid, cart.userid, cart.quantity, cart.productid, cart.price, " +
	             		"products.productName, products.imageUrl " +
	             		"FROM cart " +
	             		"JOIN products ON cart.productid = products.productid " +
	             		"WHERE cart.userid = ?";
	     
	     try  (Connection con = getConnection()){
	         PreparedStatement statement = con.prepareStatement(stringUtils.GET_ALL_CART_ITEMS);
	         statement.setInt(1, userID);
	   	     try (ResultSet resultSet = statement.executeQuery()) {
	             while (resultSet.next()){
	                 int cartId = resultSet.getInt("cartid");
	                 int productId = resultSet.getInt("productid");
	                 int quantity = resultSet.getInt("quantity");
	                 double totalPrice = resultSet.getDouble("price");
	                 String productName = resultSet.getString("productName");
	                 String productImage = resultSet.getString("imageUrl");
	                
	                 cartModel cartItem = new cartModel(cartId, userID, productId, quantity, totalPrice, productName, productImage);
	                 cartItems.add(cartItem);
	             }
	         }
	     } catch (SQLException e) {
	         e.printStackTrace();
	     }
	     return cartItems;
	 }
	 
	 //method that is used to remove the items from the cart 
	 public void removeItem(int cartId) throws ClassNotFoundException {
		    try (Connection con = getConnection()) {
		        // Query to get the quantity of the item to be removed
		        String quantityQuery = "SELECT quantity, productId FROM cart WHERE cartId = ?";
		        try (PreparedStatement quantityStmt = con.prepareStatement(stringUtils.GET_CART_QUNATITY)) {
		            quantityStmt.setInt(1, cartId);
		            try (ResultSet rs = quantityStmt.executeQuery()) {
		                if (rs.next()) {
		                    int quantity = rs.getInt("quantity");
		                    int productId = rs.getInt("productId");

		                    if (quantity > 1) {
		                        // If quantity is greater than 1, decrease quantity and total price
		                        String updateQuery = "UPDATE cart SET quantity = quantity - 1, price = price - (SELECT price FROM products WHERE productId = ?) WHERE cartId = ?";
		                        try (PreparedStatement updateStmt = con.prepareStatement(stringUtils.UPDATE_CART_DETAILS)) {
		                            updateStmt.setInt(1, productId);
		                            updateStmt.setInt(2, cartId);
		                            updateStmt.executeUpdate();
		                        }
		                    } else {
		                        // If quantity is 1, permanently remove the item
		                        String deleteQuery = "DELETE FROM cart WHERE cartId = ?";
		                        try (PreparedStatement deleteStmt = con.prepareStatement(stringUtils.DELETE_CART)) {
		                            deleteStmt.setInt(1, cartId);
		                            deleteStmt.executeUpdate();
		                        }
		                    }
		                }
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

    // Method to create an order from cart items
    public int createOrder(int userId, int productId, int quantity, double totalPrice) throws ClassNotFoundException {
        try (Connection con = getConnection()) {
            String query = "INSERT INTO Orders (userId, productId, quantity, totalPrice) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, userId);
                stmt.setInt(2, productId);
                stmt.setInt(3, quantity);
                stmt.setDouble(4, totalPrice);
                return stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Method to get all orders with user and product details
    public List<orderModel> getAllOrders() throws ClassNotFoundException {
        List<orderModel> orders = new ArrayList<>();
        String query = "SELECT o.id, o.userId, o.productId, o.quantity, o.totalPrice, " +
                      "u.userName, p.productName, p.imageUrl " +
                      "FROM Orders o " +
                      "JOIN Users u ON o.userId = u.userId " +
                      "JOIN Products p ON o.productId = p.productId " +
                      "ORDER BY o.id DESC";
        
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("userId");
                int productId = rs.getInt("productId");
                int quantity = rs.getInt("quantity");
                double totalPrice = rs.getDouble("totalPrice");
                
                orderModel order = new orderModel(id, userId, productId, quantity, totalPrice);
                order.setUserName(rs.getString("userName"));
                order.setProductName(rs.getString("productName"));
                order.setProductImage(rs.getString("imageUrl"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Method to delete an order
    public int deleteOrder(int orderId) throws ClassNotFoundException {
        try (Connection con = getConnection()) {
            String query = "DELETE FROM Orders WHERE id = ?";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, orderId);
                return stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
	 


