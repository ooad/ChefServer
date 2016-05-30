import Entities.Users;
import Manager.AccountManager;
import Manager.MenuManager;
import Manager.RestaurantManager;
import Models.DataModels.AccountManagerModel;
import Services.MyServer;
import Services.Service;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.Socket;

/**
 * Created by hank9653 on 2016/4/28.
 */
public class ChefHandler implements Runnable {
    Users users = new Users();
    String user;
    MyServer server;
    Socket socket;

    public ChefHandler(Socket socket) {
        this.socket = socket;
        this.server = new MyServer(socket);
    }

    public void run() {
        try {
            while(true){
                String receiveMessage = server.requestFromClient();
                System.out.println("message: "+receiveMessage);
                JSONObject getClientRequest = new JSONObject(receiveMessage);
                if (getClientRequest.getString("requestServiceType").equals("ChefClientService")){
                    Service service = new Service();
                    service.setRespondClient(server);
                    service.SelectService(getClientRequest.get("requestService").toString());
                }else if(getClientRequest.getString("requestServiceType").equals("MenuManager")){
                    MenuManager menuManager = new MenuManager();
                    menuManager.setRespondClient(server);
                    menuManager.setUsers(users);
                    menuManager.selectService(getClientRequest.getString("requestService"),getClientRequest);
                    server.closeRespond();
                }else if(getClientRequest.getString("requestServiceType").equals("AccountService")){
                    AccountManagerModel accountManagerModel = new AccountManagerModel(getClientRequest);
                    AccountManager accountManager = new AccountManager();
                    accountManager.setRespondClient(server);
                    accountManager.selectService(accountManagerModel.getService(),accountManagerModel.getUserInfo());
                    server.closeRespond();
                }else if(getClientRequest.getString("requestServiceType").equals("RestaurantManager")){
                    RestaurantManager restaurantManager = new RestaurantManager();
                    restaurantManager.setRespondClient(server);
                    restaurantManager.selectService(getClientRequest.getString("requestService"));
                    server.closeRespond();
                }else{
                    server.respondToClient("No Service");
                    System.out.println("no Service");
                }
                if(receiveMessage != null){
                    server.respondToClient("CLOSECONNECT");
                }
            }
        } catch (JSONException e) {
            System.out.println("JSONException "+e.toString());
            server.respondToClient("CLOSECONNECT");
            server.close();
        }catch(NullPointerException e){
            e.printStackTrace();
            System.out.println("NullPointerException "+e.toString());
            System.out.println("fuck");
            server.close();
            server = null;
        }
    }




    public void accept(Users users) {
        this.users = users;
    }
}