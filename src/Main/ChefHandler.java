package Main;

import Manager.*;
import Services.MyServer;
import Services.Service;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                System.out.println("message: "+receiveMessage);
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
                }else if(getClientRequest.getString("requestServiceType").equals("OrderMealService")){
                    OrderManager orderManager = new OrderManager();
                    //orderManager.SelectService(getClientRequest.get("requestService").toString());
                    orderManager.setRespondClient(server);
                    if(getClientRequest.getString("requestService").equals("OrderMeal")){
                        System.out.println(getClientRequest.getJSONObject("OrderMeal"));
                        JSONObject OrderMeal = getClientRequest.getJSONObject("OrderMeal");
                        //orderManager.OrderMeal(1,2,3,'4',5);
                        System.out.println(OrderMeal.getInt("idRestaurant")+"  "+OrderMeal.getInt("tableNum")+"  "+OrderMeal.getInt("idMeal")+"  "+OrderMeal.getInt("idUser"));
                        server.respondToClient("OrderMealSusses");
                        
                    }else if(getClientRequest.getString("requestService").equals("GetStatus")){
                        //orderManager.OrderMeal(0,0,3,'4',0);
                        ResultSet result = orderManager.getMealStatus(0);
                        while(result.next()){
                            server.respondToClient(result.getInt("idRestaurant")+"  "+result.getInt("tableNum")+"  "+result.getInt("idMeal")+"  "+result.getString("mealStatus")+"  "+result.getInt("idUser"));
                        }
                        while(true){
                            if(server.requestFromClient().equals("CLOSECONNECT")){
                                break;
                            }
                        }
                        //server.close();
                    }
                }else if(getClientRequest.getString("requestServiceType").equals("AccountService")){
                    AccountManager accountManager = new AccountManager();
                    accountManager.setRespondClient(server);
                    if(getClientRequest.getString("requestService").equals("Login")){
                        accountManager.login(getClientRequest.getString("userAccount"), getClientRequest.getString("userPassword"));
                    }else if(getClientRequest.getString("requestService").equals("Register")){

                    }else if(getClientRequest.getString("requestService").equals("Venfication")){

                    }
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
            System.out.println("NullPointerException "+e.toString());
            System.out.println("fuck");
            server.close();
            server = null;
        } catch (SQLException e) {
            System.out.println("SQLException "+e.toString());
            e.printStackTrace();
        }
    }


    public void addUser(HashMap<String, Socket> user) {
        this.user = user;
        /*for (Object key : user.keySet()) {
            System.out.println(key + " : " + user.get(key));
        }*/
    }

    public void Service() {
        this.user = user;
        /*for (Object key : user.keySet()) {
            System.out.println(key + " : " + user.get(key));
        }*/
    }

}