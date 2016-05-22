package Models.DataModels;

import Entities.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hank9653 on 2016/5/17.
 */
public class MenuManagerModel {
    UserInfo userInfo = null;
    String service = null;

    public MenuManagerModel decode(JSONObject getClientRequest) {
        try {
            service = getClientRequest.getString("requestService");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String encode(Menu menu){
        JSONObject jsonObject = new JSONObject();
        for(MealType mealType : menu.getMealType()){
            try {
                JSONArray ja = new JSONArray();
                for(Meal meal : mealType.getMealList()){
                        JSONObject mealInfo = new JSONObject();
                        mealInfo.put("idMeal",meal.id);
                        mealInfo.put("mealName",meal.name);
                        mealInfo.put("mealDescription",meal.description);
                        mealInfo.put("mealPrice",meal.price);
                        ja.put(mealInfo);
                }
                jsonObject.put(mealType.getType(),ja);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject.toString();
    }

    public String orderMenuEncode(ArrayList<OrderedMeal> orderMenu){
        JSONObject jsonObject = new JSONObject();
        JSONArray ja;
            try {
                ja = new JSONArray();
                for(OrderedMeal orderMeal : orderMenu) {
                    JSONObject orderMealInfo = new JSONObject();
                    orderMealInfo.put("idOrderMeal", orderMeal.getIdOrderMeal());
                    orderMealInfo.put("orderMealName", orderMeal.getOrderMealName());
                    orderMealInfo.put("orderMealDescription", orderMeal.getOrderMealDescription());
                    orderMealInfo.put("orderMealPrice", orderMeal.getOrderMealPrice());
                    orderMealInfo.put("orderMealType", orderMeal.getOrderMealType());
                    orderMealInfo.put("orderMealStatus", orderMeal.getOrderMealStatus());
                    ja.put(orderMealInfo);
                }
                jsonObject.put("orderMenu",ja);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return jsonObject.toString();
    }
}
