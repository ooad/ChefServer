package Main;

import Manager.*;
import Models.RestaurantModel;
import Services.MyServer;
import Services.Service;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hank9653 on 2016/4/28.
 */
public class ChefHandler implements Runnable {
    HashMap<String, Socket> users = new HashMap<String, Socket>();
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
                    /*MenuManager menuManager = new MenuManager();
                    for (Meal meal: menuManager.getMenu().getMealList()) {
                        server.respondToClient(meal.description);
                    }*/
                    if(getClientRequest.getString("requestService").equals("getMealType")){
                        MenuManager menuManager = new MenuManager();
                        System.out.println(getClientRequest.getInt("idRestaurant"));
                        ArrayList<String> mealTypes =  menuManager.getMealType(getClientRequest.getInt("idRestaurant"));
                        for(String mealType : mealTypes){
                            server.respondToClient(mealType);
                        }
                        //server.respondToClient(meal.description);
                    }else if(getClientRequest.getString("requestService").equals("getMenu")){
                        System.out.println(getClientRequest.getInt("idRestaurant"));
                        MenuManager menuManager = new MenuManager();
                        String meals =  menuManager.getMeal(getClientRequest.getInt("idRestaurant"));
                        server.respondToClient(meals);
                    }



                    OrderManager orderManager = new OrderManager();
                    //orderManager.SelectService(getClientRequest.get("requestService").toString());
                    orderManager.setRespondClient(server);
                    if(getClientRequest.getString("requestService").equals("setOrderMeal")){
                        //System.out.println(getClientRequest.getJSONObject("OrderMeal"));
                        JSONObject OrderMeal = getClientRequest.getJSONObject("data");
                        orderManager.OrderMeal(OrderMeal.getInt("idRestaurant"),OrderMeal.getInt("tableNum"),OrderMeal.getInt("idMeal"),OrderMeal.getString("mealStatus").charAt(0),OrderMeal.getInt("idUser"));
                        System.out.println(OrderMeal.getInt("idRestaurant")+"  "+OrderMeal.getInt("tableNum")+"  "+OrderMeal.getInt("idMeal")+"  "+OrderMeal.getInt("idUser"));
                        server.respondToClient("true");

                    }else if(getClientRequest.getString("requestService").equals("getOrderMenu")){
                        JSONObject orderMenuAllId = getClientRequest.getJSONObject("data");
                        System.out.println(orderMenuAllId);
                        ResultSet result = orderManager.getOrderMenu(orderMenuAllId.getInt("idRestaurant"), orderMenuAllId.getInt("tableNum"), orderMenuAllId.getInt("idUser"));
                    }
                    else if(getClientRequest.getString("requestService").equals("GetStatus")){
                        //orderManager.OrderMeal(0,0,3,'4',0);
                        ResultSet result = orderManager.getMealStatus(0);
                        addUser(getClientRequest.getString("userType"),socket);
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


                    server.closeRespond();
                }else if(getClientRequest.getString("requestServiceType").equals("MenuManager")){

                }else if(getClientRequest.getString("requestServiceType").equals("AccountService")){
                        AccountManager accountManager = new AccountManager();
                        accountManager.setRespondClient(server);
                        if(getClientRequest.getString("requestService").equals("Login")){
                            accountManager.login(getClientRequest.getString("UserAccount"), getClientRequest.getString("UserPassword"));
                        }else if(getClientRequest.getString("requestService").equals("Register")){

                        }else if(getClientRequest.getString("requestService").equals("Venfication")){

                        }
                }else if(getClientRequest.getString("requestServiceType").equals("RestaurantManager")){
                    if(getClientRequest.getString("requestService").equals("getRestaurantList")){
                        RestaurantModel restaurantModel = new RestaurantModel();
                        ResultSet restaurants =  restaurantModel.getAllRestaurant();
                        JSONArray ja = new JSONArray();
                        while(restaurants.next()){
                            JSONObject RestaurantInfo = new JSONObject();
                            RestaurantInfo.put("idRestaurant",restaurants.getInt("idRestaurant"));
                            RestaurantInfo.put("restaurantName",restaurants.getString("restaurantName"));
                            RestaurantInfo.put("restaurantDescription",restaurants.getString("restaurantDescription"));
                            RestaurantInfo.put("address",restaurants.getString("address"));
                            RestaurantInfo.put("idManager",restaurants.getInt("idManager"));
                            ja.put(RestaurantInfo);
                        }                        //server.respondToClient(meal.description);
                        System.out.println(ja.toString());
                        server.respondToClient(ja.toString());
                    }

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
            System.out.println("NullPointerException "+e.toString());
            System.out.println("fuck");
            server.close();
            server = null;
        } catch (SQLException e) {
            System.out.println("SQLException "+e.toString());
            e.printStackTrace();
        }
    }

    private void addUser(String userType, Socket socket) {
        synchronized(users){
            users.put("userType",socket);
            System.out.println(users);
        }
    }


    public void removeUser(String key) {
        users.remove(key);
        addUser(user,socket);
        /*for (Object key : user.keySet()) {
            System.out.println(key + " : " + user.get(key));
        }*/
    }


    public void setUsers(HashMap<String, Socket> user) {
        synchronized(users){
            this.users = user;
        }
        for (Object key : users.keySet()) {
            System.out.println(key + " : " + user.get(key));
        }
    }
    public void setUser(String user) {
        this.user = user;
    }

    public void Service() {
        //this.user = user;
        /*for (Object key : user.keySet()) {
            System.out.println(key + " : " + user.get(key));
        }*/
    }

}