package Services;

import Main.Meal;
import Main.Menu;

import java.util.ArrayList;

/**
 * Created by hank9653 on 2016/4/28.
 */
public class Service {
    MyServer server;
    public void SelectService(String receiveFromClient){
        switch(receiveFromClient){
            case "connectToServer":
                server.respondToClient("回傳中文看會不會亂碼");
                server.respondToClient("You are connect to server.");
                server.respondToClient("CLOSECONNECT");
                break;
        }
    }

    public void sendMenu(){
        Menu menu = new Menu();
        ArrayList<Meal> mealList = menu.getMealList();
        for (Meal meal: mealList){
            System.out.println(meal.id+meal.name+meal.description+meal.price+meal.mealType);
        }
        server.respondToClient(menu.getMealList().toString());
        server.respondToClient("CLOSECONNECT");
    }

    public void setRespondClient(MyServer server) {
        this.server = server;
    }
}
