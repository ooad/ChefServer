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
		userInfo = new AccountModel().accountInquiry(userInfo);
		if(Boolean.valueOf(userInfo.isLogin())){
			myServer.respondToClient(userInfo.isLogin());
			myServer.respondToClient(userInfo.getUserType());
			//System.out.println("islogin");
		}else{
			myServer.respondToClient(userInfo.isLogin());
			//System.out.println("no");
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
