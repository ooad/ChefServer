package Services;

import Main.ChefHandler;
import Main.Meal;
import Main.Menu;

import java.util.ArrayList;

/**
 * Created by hank9653 on 2016/4/28.
 */
public class Service {
    private ChefHandler chefHandler;
    public Service(ChefHandler chefServerService){
        this.chefHandler = chefServerService;
    }
    public void SelectService(String receiveFromClient){
        switch(receiveFromClient){
            case "connectToServer":
                chefHandler.feedbackToClient("You are connect to server.");
                break;
            case "getMenu":
                sendMenu();
                break;
        }
    }

    public void sendMenu(){
        Menu menu = new Menu();
        ArrayList<Meal> mealList = menu.getMealList();
        for (Meal meal: mealList){
            System.out.println(meal.id+meal.name+meal.description+meal.price+meal.mealType);
        }
        chefHandler.feedbackToClient(menu.getMealList().toString());
    }
}
