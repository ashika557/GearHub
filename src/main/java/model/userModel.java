package model;

public class userModel {
	private int userId;
    private String userName;
    private String address;
    private String password;
    private String role;
    private String email;

    public userModel(String userName, String address, String password, String email) {
        this.userName = userName;
        this.address = address;
        this.password = password;
        this.email = email;
    }

	//  constructor used for while fetching the data from the database
    public userModel(int userId, String userName, String address, String password, String email) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.address = address;
		this.password = password;
		this.email = email;
	}

	// Getters and setters
    
    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
