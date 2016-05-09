package Main;

import Models.MenuModel;

import java.util.ArrayList;

/**
 * Created by hank9653 on 2016/4/27.
 */
public class Menu extends MenuModel {
    private ArrayList<Meal> mealList = null;
    @Override
    public ArrayList<Meal> getMealList() {
        MenuModel menuModel = new MenuModel();
        menuModel.getMealList();
        this.mealList = super.getMealList();
        return mealList;
    }

    /*public String toJson(){
        String json = null;
        for(Meal meal : mealList){
            json = "{'"+meal.mealType"':"";
        }
        return json;
    }*/
}
