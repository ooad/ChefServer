package Manager;

import Models.AccountModel;
import Services.MyServer;
import Main.UserInfo; 

/**
 * Created by hank9653 on 2016/5/2.
 */
public class AccountManager extends AccountModel{
	MyServer myServer;
	public void login(String userAccount, String userPassword){
		if(accountInquiry(userAccount, userPassword)){
			//myServer.respondToClient("true");
			System.out.println("true");
		}else{
			//myServer.respondToClient("false");
			System.out.println("false");
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
