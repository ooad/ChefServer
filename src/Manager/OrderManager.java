package Manager;

import Models.OrderMeal;
import Services.MyServer;

/**
 * Created by hank9653 on 2016/5/2.
 */
public class OrderManager {
    OrderMeal om = new OrderMeal();
    MyServer myServer;
    public boolean OrderMeal(int idRestaurant, int tableNum, int idMeal, char mealStatus, int idUser){
        return om.OrderMeal(idRestaurant,tableNum,idMeal,mealStatus,idUser);
    }
    public boolean changeMealStatus(int idUser,int idMeal,char mealStatus){
        if(om.changeMealStatus(idUser,idMeal,mealStatus)){
            myServer.changeStatus(idUser,idMeal,mealStatus);
            return true;
        }
        return false;
    }

    public void setRespondClient(MyServer respondClient) {
        this.myServer = respondClient;
    }
}
