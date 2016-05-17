package Models;

import Entities.UserInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountModel{
	DB db;
	public boolean accountInquiry(String userId, String userPassword){
		db = new DB();
		boolean loginStatus = false;
		ResultSet resultSet = db.query("SELECT * FROM chefsystem.user WHERE userAccount=\""+userId+"\" AND userPassword=\""+userPassword + "\"");

        try {
            if(resultSet!=null){

                System.out.println("ln");
                while(resultSet.next()) {
                   // userList[0][0] = resultSet.getString("userAccount");
                   // userList[0][1] = resultSet.getString("userPassword");
                    loginStatus = true;
                    System.out.println(resultSet.getString("userAccount"));

                    System.out.println(resultSet.getString("userPassword"));
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

	public UserInfo userInfoInquiry(String userId){
        return null;
    }
}