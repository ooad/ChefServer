package Entities;

import java.util.ArrayList;

/**
 * Created by hank9653 on 2016/5/17.
 */
public class Menu {
    ArrayList<MealType> mealTypeList = new ArrayList<>();

    public void setMealTypeList(ArrayList<MealType> mealTypeList) {
        this.mealTypeList = mealTypeList;
    }
    public void addMealType(MealType mealType) {
        mealTypeList.add(mealType);
    }

    public ArrayList<MealType> getMealType() {
        return mealTypeList;
    }
}
