package Manager;

import Models.RestaurantModel;
import Services.MyServer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hank9653 on 2016/5/17.
 */
public class RestaurantManager {
    MyServer myServer;

    public void selectService(String requestService) {
        if(requestService.equals("getRestaurantList")){
            getRestaurantList();
        }
    }

    private void getRestaurantList() {
        RestaurantModel restaurantModel = new RestaurantModel();
        ResultSet restaurants =  restaurantModel.getAllRestaurant();
        JSONArray ja = new JSONArray();
        try {
            while(restaurants.next()){
                JSONObject RestaurantInfo = new JSONObject();
                RestaurantInfo.put("idRestaurant",restaurants.getInt("idRestaurant"));
                RestaurantInfo.put("restaurantName",restaurants.getString("restaurantName"));
                RestaurantInfo.put("restaurantDescription",restaurants.getString("restaurantDescription"));
                RestaurantInfo.put("address",restaurants.getString("address"));
                RestaurantInfo.put("idManager",restaurants.getInt("idManager"));
                ja.put(RestaurantInfo);
            }                        //server.respondToClient(meal.description);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(ja.toString());
        myServer.respondToClient(ja.toString());
    }

    public void setRespondClient(MyServer server) {
        this.myServer = server;
    }
}
