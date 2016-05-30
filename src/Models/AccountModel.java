package Models;

import Entities.UserInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountModel{
	DB db;
	public UserInfo accountInquiry(UserInfo userInfo){
		db = new DB();
		boolean loginStatus = false;
		ResultSet resultSet = db.query("SELECT * FROM chefsystem.user WHERE userAccount=\""+userInfo.getUserAccount()+"\" AND userPassword=\""+userInfo.getUserPassword() + "\"");

        try {
            if(resultSet!=null){
                while(resultSet.next()) {
                    userInfo.setUserType(resultSet.getString("userAccount"));
                    userInfo.loginStatus("true");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userInfo;
	}

	public boolean accountInsert(UserInfo userInfo){
        return false;
    }

	public UserInfo userInfoInquiry(UserInfo userInfo){
        return null;
    }
}