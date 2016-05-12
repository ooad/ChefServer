package Manager;

import Models.AccountModel;
import Services.MyServer;
import Main.UserInfo; 

/**
 * Created by hank9653 on 2016/5/2.
 */
public class AccountManager{
	MyServer myServer;
	public void login(String userAccount, String userPassword){
		if(new AccountModel().accountInquiry(userAccount, userPassword)){
			myServer.respondToClient("true");
		}else{
			myServer.respondToClient("false");
		}
	}

	public void register(UserInfo userInfo){

	}

	public void venfication(String otgKey, String phoneNum){

	}

	public void setRespondClient(MyServer respondClient) {
        this.myServer = respondClient;
    }
}
