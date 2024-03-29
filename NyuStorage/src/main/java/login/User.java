	package login;
	
	public class User {
	    private String userID;
	    private String userPassword;
	    private String userName;
	    private String userIC;
	    private String userPhoneNo; // New attribute for user address
	    private String userAddress; // New attribute for user IC
	
	    // Constructor
	    public User(String userID, String userPassword, String userName, String userIC, String userPhoneNo, String userAddress) {
	        this.userID = userID;
	        this.userPassword = userPassword;
	        this.userName = userName;
	        this.userIC = userIC;
	        this.userPhoneNo = userPhoneNo;
	        this.userAddress = userAddress;
	    }
	
	    // Getters and Setters for all attributes
	    public String getUserID() {
	        return userID;
	    }
	
	    public void setUserID(String userID) {
	        this.userID = userID;
	    }
	
	    public String getUserPassword() {
	        return userPassword;
	    }
	
	    public void setUserPassword(String userPassword) {
	        this.userPassword = userPassword;
	    }
	
	    public String getUserName() {
	        return userName;
	    }
	
	    public void setUserName(String userName) {
	        this.userName = userName;
	    }
	
	    public String getUserPhoneNo() {
	        return userPhoneNo;
	    }
	
	    public void setUserPhoneNo(String userPhoneNo) {
	        this.userPhoneNo = userPhoneNo;
	    }
	
	    public String getUserAddress() {
	        return userAddress;
	    }
	
	    public void setUserAddress(String userAddress) {
	        this.userAddress = userAddress;
	    }
	
	    public String getUserIC() {
	        return userIC;
	    }
	
	    public void setUserIC(String userIC) {
	        this.userIC = userIC;
	    }
	}