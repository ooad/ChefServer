package Manager;

import Models.AccountModel;
import Services.MyServer;
import Entities.UserInfo;

/**
 * Created by hank9653 on 2016/5/2.
 */
public class AccountManager{
	MyServer myServer;

	public void selectService(String requestService,UserInfo userInfo) {
		if(requestService.equals("Login")){
			login(userInfo);
		}else if(requestService.equals("Register")){

		}else if(requestService.equals("Venfication")){

		}
	}

	public void login(UserInfo userInfo){
		if(new AccountModel().accountInquiry(userInfo)){
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
