package Main;

import Manager.MenuManager;
import Manager.OrderManager;
import Services.MyServer;
import Services.Service;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.Socket;
import java.util.HashMap;

/**
 * Created by hank9653 on 2016/4/28.
 */
public class ChefHandler implements Runnable {
    HashMap<String, Socket> user = new HashMap<String, Socket>();
    MyServer server;

    public ChefHandler(Socket socket) {
        this.server = new MyServer(socket);
    }

    public void run() {
        try {
            while(true){
                String receiveMessage = server.requestFromClient();
                System.out.println(receiveMessage);
                JSONObject getClientRequest = new JSONObject(receiveMessage);
                if (getClientRequest.getString("requestServiceType").equals("ChefClientService")){
                    Service service = new Service();
                    service.setRespondClient(server);
                    service.SelectService(getClientRequest.get("requestService").toString());
                }else if(getClientRequest.getString("requestServiceType").equals("MenuManager")){
                    MenuManager menuManager = new MenuManager();
                    for (Meal meal: menuManager.getMenu().getMealList()) {
                        server.respondToClient(meal.description);
                    }
                    server.closeRespond();
                }else if(getClientRequest.getString("requestServiceType").equals("OrderManager")){
                    OrderManager orderManager = new OrderManager();
                    orderManager.setRespondClient(server);
                    orderManager.OrderMeal(1,2,3,'4',5);
                    //server.closeRespond();
                }else if(getClientRequest.getString("requestServiceType").equals("Account")){

                }else{
                    server.respondToClient("No Service");
                    server.respondToClient("CLOSECONNECT");
                    System.out.println("no Service");
                }
            }
        } catch (JSONException e) {
            server.respondToClient("No Service");
            server.respondToClient("CLOSECONNECT");
        }catch(NullPointerException e){
            server.close();
            server = null;
        }
    }


    public void updateUser(HashMap<String, Socket> user) {
        this.user = user;
    }

}