package Models;

import Main.UserInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountModel{
	DB db;
	public boolean accountInquiry(String userId, String userPassword){
		db = new DB();
		String [][] userList = null;
		ResultSet resultSet = db.query("SELECT * FROM chefsystem.user WHERE userAccount=\""+userId+"\" AND userPassword=\""+userPassword + "\"");

        try {
            if(resultSet!=null){
                while(resultSet.next()) {
                    userList[0][0] = resultSet.getString("userAccount");
                    userList[0][1] = resultSet.getString("userPassword");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(userList == null)
			return false;
		else
			return true;
	}

	public boolean accountInsert(UserInfo userInfo){
        return false;
    }

	public UserInfo userInfoInquiry(String userId){
        return null;
    }
}