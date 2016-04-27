import java.io.PrintWriter;

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
            case "ISCONNECTSERVER":
                chefHandler.FeedbackToClient("ISCONNECT");
                break;
            case "GETMENU":
                sendMenu();
                break;
        }
    }

    public void sendMenu(){
        Menu menu = new Menu();
        chefHandler.FeedbackToClient(menu.getMenu().toString());
    }
}
