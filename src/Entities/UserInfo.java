package Entities;

public class UserInfo{
	private String userAccount = null;
	private String userPassword = null;
	private String userName = null;
	private String phoneNum = null;
	private String userType = null;
	private String loginStatus = "false";

	public void setUserInfo(String userId, String userPassword, String userName, String phoneNum, String userType){
		this.userAccount = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.phoneNum = phoneNum;
		this.userType = userType;
	}

	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}

	public void setUserName(){

	}

	public void setPhoneNum(){

	}

	public void setUserType(String userType){
		this.userType = userType;
	}

	public void setUserAccount(String userAccount){
		this.userAccount = userAccount;
	}

	public String getUserPassword(){
		return this.userPassword;
	}

	public String getUserName(){
		return this.userName;
	}

	public String getPhoneNum(){
		return this.phoneNum;
	}

	public String getUserType(){
		return this.userType;
	}
	public String getUserAccount(){
		return this.userAccount;
	}
	public String isLogin(){
		return loginStatus;
	}
	public void loginStatus(String status){
		this.loginStatus = status;
	}
}