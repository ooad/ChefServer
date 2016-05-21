package Models;

import Entities.UserInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountModel{
	DB db;
	public boolean accountInquiry(UserInfo userInfo){
		db = new DB();
		boolean loginStatus = false;
		ResultSet resultSet = db.query("SELECT * FROM chefsystem.user WHERE userAccount=\""+userInfo.getUserAccount()+"\" AND userPassword=\""+userInfo.getUserPassword() + "\"");

        try {
            if(resultSet!=null){

                System.out.println("ln");
                while(resultSet.next()) {
                    loginStatus = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loginStatus;
	}

	public boolean accountInsert(UserInfo userInfo){
        return false;
    }

	public UserInfo userInfoInquiry(UserInfo userInfo){
        return null;
    }
}