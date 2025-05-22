package model;

public class feedbackModel {
	
	private String userName;
	private String email;
	private String feedback;
	private String date;
	
	//constructor that is used to store the data
	public feedbackModel(String userName, String email, String feedback) {
		super();
		this.userName = userName;
		this.email = email;
		this.feedback = feedback;
	}
	
	//constructor that is used while fetching data form the database 
	public feedbackModel(String userName, String email, String feedback, String date) {
		super();
		this.userName = userName;
		this.email = email;
		this.feedback = feedback;
		this.date = date;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
}
