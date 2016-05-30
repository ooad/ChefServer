package Manager;

import Entities.Menu;
import Entities.OrderedMeal;
import Entities.Users;
import Models.DataModels.MenuManagerModel;
import Models.MenuModel;
import Models.OrderMeal;
import Services.MyServer;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by hank9653 on 2016/5/2.
 */
public class MenuManager {
    private MyServer myServer;
    private MenuModel menuModel= new MenuModel();
    private OrderMeal orderMeal = new OrderMeal();
    MenuManagerModel menuManagerModel = new MenuManagerModel();
    Users users;

    public Menu getMenu(int restaurant) {
        return menuModel.getMenu(restaurant);
    }

    public void setRespondClient(MyServer server) {
        this.myServer = server;
    }

    public void selectService(String requestService, JSONObject getClientRequest) {
        try {
            if(requestService.equals("getMenu")){
                System.out.println(getClientRequest.getInt("idRestaurant"));
                Menu menu = getMenu(getClientRequest.getInt("idRestaurant"));
                System.out.println(menuManagerModel.encode(menu));
                myServer.respondToClient(menuManagerModel.encode(menu));
            }else if(requestService.equals("setOrderMeal")){
                JSONObject OrderMeal = getClientRequest.getJSONObject("data");
                OrderMeal(OrderMeal.getInt("idRestaurant"),OrderMeal.getInt("tableNum"),OrderMeal.getInt("idMeal"),OrderMeal.getString("mealStatus").charAt(0),OrderMeal.getInt("idUser"));
                System.out.println(OrderMeal.getInt("idRestaurant")+"  "+OrderMeal.getInt("tableNum")+"  "+OrderMeal.getInt("idMeal")+"  "+OrderMeal.getInt("idUser"));
                myServer.respondToClient("true");

            }else if(requestService.equals("getOrderMenu")){
                JSONObject orderMenuAllId = getClientRequest.getJSONObject("data");
                System.out.println(orderMenuAllId);
                ArrayList<OrderedMeal> orderMenu = getOrderMenu(orderMenuAllId.getInt("idRestaurant"), orderMenuAllId.getInt("tableNum"), orderMenuAllId.getInt("idUser"));
                System.out.println(menuManagerModel.orderMenuEncode(orderMenu));
                myServer.respondToClient(menuManagerModel.orderMenuEncode(orderMenu));
            }else if(requestService.equals("getOrderMenuListen")){
                users.add("user",myServer);
                while(true){
                    String message =  myServer.requestFromClient();
                    System.out.println("test: "+message);
                    if(message.equals("CLOSECONNECT")){
                        System.out.println("break");
                        break;

                    }else{
                        System.out.println("talk");
                        users.talkToUser("user");
                    }
                }
                users.remove("user");
            }else if(requestService.equals("changeMealStatus")){
               for (Object key : users.getOnlineUser().keySet()) {
                    System.out.println(key);
                    if(key.equals("user")){
                        MyServer test1 = users.getOnlineUser().get(key);
                        if(test1 == null){

                            System.out.println("NULL");
                        }else{

                            test1.respondToClient("Chef made it ok!!.");
                            System.out.println("MESSAGE IS SEND");
                        }
                        break;
                    }
                }
                //myServer.respondToClient(menuManagerModel.orderMenuEncode(orderMenu));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public boolean OrderMeal(int idRestaurant, int tableNum, int idMeal, char mealStatus, int idUser){
        return orderMeal.OrderMeal(idRestaurant,tableNum,idMeal,mealStatus,idUser);
    }

    public boolean changeMealStatus(int idUser,int idMeal,char mealStatus){
        if(orderMeal.changeMealStatus(idUser,idMeal,mealStatus)){
            myServer.changeStatus(idUser,idMeal,mealStatus);
            return true;
        }
        return false;
    }

    public ResultSet getMealStatus(int idUser){
        return orderMeal.getMealStatus(idUser);
    }

    public ArrayList<OrderedMeal> getOrderMenu(int idRestaurant, int tableNum, int idUser){
        return orderMeal.getOrderMenu(idRestaurant, tableNum, idUser);
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
