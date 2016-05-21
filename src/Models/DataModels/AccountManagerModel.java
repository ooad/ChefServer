package Models.DataModels;

import Entities.UserInfo;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hank9653 on 2016/5/17.
 */
public class AccountManagerModel {
    UserInfo userInfo = null;
    String service = null;
    public AccountManagerModel(JSONObject getClientRequest) {
        userInfo =new UserInfo();
        try {
            userInfo.setUserPassword(getClientRequest.getString("UserPassword"));
            userInfo.setUserAccount(getClientRequest.getString("UserAccount"));
            service = getClientRequest.getString("requestService");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public UserInfo getUserInfo(){
        return userInfo;
    }

    public String getService(){
        return service;
    }
}
