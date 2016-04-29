package Models;

import Main.Meal;

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
            Meal meal = null;
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
}
