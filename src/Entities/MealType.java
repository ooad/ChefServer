package Entities;

import java.util.ArrayList;

/**
 * Created by hank9653 on 2016/4/27.
 */
public class MealType {
    private ArrayList<Meal> mealList = new ArrayList<>();
    String type;

    public ArrayList<Meal> getMealList(){return mealList;}

    public void addMeal(Meal meal){
        mealList.add(meal);
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
