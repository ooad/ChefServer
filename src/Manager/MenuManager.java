package Manager;

import Entities.Menu;
import Models.DataModels.MenuManagerModel;
import Models.MenuModel;
import Models.OrderMeal;
import Services.MyServer;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hank9653 on 2016/5/2.
 */
public class MenuManager {
    private MyServer myServer;
    private MenuModel menuModel= new MenuModel();
    private OrderMeal orderMeal = new OrderMeal();
    MenuManagerModel menuManagerModel = new MenuManagerModel();

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
                ResultSet result = getOrderMenu(orderMenuAllId.getInt("idRestaurant"), orderMenuAllId.getInt("tableNum"), orderMenuAllId.getInt("idUser"));
            }else if(requestService.equals("GetStatus")){
                //orderManager.OrderMeal(0,0,3,'4',0);
                ResultSet result = getMealStatus(0);
                while(result.next()){
                    myServer.respondToClient(result.getInt("idRestaurant")+"  "+result.getInt("tableNum")+"  "+result.getInt("idMeal")+"  "+result.getString("mealStatus")+"  "+result.getInt("idUser"));
                }
                while(true){
                    if(myServer.requestFromClient().equals("CLOSECONNECT")){
                        break;
                    }
                }
                //server.close();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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

    public ResultSet getOrderMenu(int idRestaurant, int tableNum, int idUser){
        ResultSet result = orderMeal.getOrderMenu(idRestaurant, tableNum, idUser);
        return result;
    }
}
