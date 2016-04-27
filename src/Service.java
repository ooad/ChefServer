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
        if (receiveFromClient.startsWith("ISCONNECTSERVER")) {
            System.out.println("entry");
            chefHandler.FeedbackToClient("ISCONNECT");
        }else if(receiveFromClient.startsWith("GETMENU")){
            sendMenu();
        }
    }

    public void sendMenu(){
        Menu menu = new Menu();
        chefHandler.FeedbackToClient(menu.getMenu());
    }
}
