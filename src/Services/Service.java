package Services;

import Main.ChefHandler;
import Main.Menu;

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
        chefHandler.feedbackToClient(menu.getMealList().toString());
    }
}
