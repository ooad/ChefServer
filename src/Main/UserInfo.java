package Main;

public class UserInfo{
	private String userId;
	private String userPassword;
	private String userName;
	private String phoneNum;
	private String userType;

	public void setUserInfo(String userId, String userPassword, String userName, String phoneNum, String userType){
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.phoneNum = phoneNum;
		this.userType = userType;
	}

	public String getUserId(){
		return this.userId;
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
}