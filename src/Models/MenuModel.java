package Models;

import Entities.Meal;
import Entities.MealType;
import Entities.Menu;

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

    public Menu getMenu(int restaurant) {
        ArrayList<String> mealTypes = new ArrayList<>();

        DB db = new DB();
        String sql = "SELECT DISTINCT mealType FROM meal where idRestaurant = "+restaurant;
        ResultSet result = db.query(sql);
        try {
            while(result.next()){
                mealTypes.add((result.getString("mealType")));
            }
        } catch (SQLException e) {
            System.out.println("MenuModel SQL error :"+e.toString());
        }

        Menu menu = new Menu();
        for(String type : mealTypes){
            sql = "SELECT * FROM meal where idRestaurant = "+restaurant+" AND mealType=\""+type+"\"";
            result = db.query(sql);
            MealType mealType = new MealType();
            mealType.setType(type);
            try {
                while(result.next()){
                    Meal meal = new Meal();
                    meal.id = result.getInt("idMeal");
                    meal.name = result.getString("mealName");
                    meal.description = result.getString("mealDescription");
                    meal.price = result.getInt("mealPrice");
                    meal.mealType = result.getString("mealType");
                    mealType.addMeal(meal);
                }
            } catch (SQLException e) {
                System.out.println("MenuModel SQL error :"+e.toString());
            }
            menu.addMealType(mealType);
        }


        db.close();

        return menu;
    }
}
