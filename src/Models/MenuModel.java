package Models;

import Entities.Meal;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by hank9653 on 2016/4/28.
 */

public class MenuModel {
    public ArrayList<Meal> getMealList(){
        DB db = new DB();
        String sql = "SELECT * FROM meal";
        ResultSet result = db.query(sql);
        ArrayList<Meal> mealList = new ArrayList<>();
        try {
            Meal meal;
            while(result.next()){
                meal = new Meal();
                meal.id = result.getInt("idMeal");
                meal.name = result.getString("name");
                meal.description = result.getString("description");
                meal.price = result.getInt("price");
                meal.mealType = result.getString("mealType");
                mealList.add(meal);
            }
        } catch (SQLException e) {
            System.out.println("MenuModel SQL error :"+e.toString());
        }
        db.close();
        return mealList ;
    }

    public ArrayList<String> getMealType(int restaurant){
        ArrayList<String> mealType = new ArrayList<>();
        DB db = new DB();
        String sql = "SELECT DISTINCT mealType FROM meal where idRestaurant = "+restaurant;
        ResultSet result = db.query(sql);
        try {
            while(result.next()){
                mealType.add(result.getString("mealType"));
            }
        } catch (SQLException e) {
            System.out.println("MenuModel SQL error :"+e.toString());
        }
        db.close();
        return mealType;
    }

    public String getMeal(int restaurant) {
        ArrayList<String> mealTypes = new ArrayList<>();
        DB db = new DB();
        String sql = "SELECT DISTINCT mealType FROM meal where idRestaurant = "+restaurant;
        ResultSet result = db.query(sql);
        try {
            while(result.next()){
                mealTypes.add(result.getString("mealType"));
            }
        } catch (SQLException e) {
            System.out.println("MenuModel SQL error :"+e.toString());
        }

        JSONObject jsonObject = new JSONObject();
        for(String mealType : mealTypes){
            sql = "SELECT * FROM meal where idRestaurant = "+restaurant+" AND mealType=\""+mealType+"\"";
            result = db.query(sql);
            JSONArray ja = new JSONArray();
            try {
                while(result.next()){
                    JSONObject mealInfo = new JSONObject();
                    mealInfo.put("idMeal",result.getInt("idMeal"));
                    mealInfo.put("mealName",result.getString("mealName"));
                    mealInfo.put("mealDescription",result.getString("mealDescription"));
                    mealInfo.put("mealPrice",result.getInt("mealPrice"));
                    ja.put(mealInfo);
                }
                jsonObject.put(mealType,ja);
            } catch (SQLException e) {
                System.out.println("MenuModel SQL error :"+e.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        db.close();

        System.out.println(jsonObject.toString());
        return jsonObject.toString();
    }
}
