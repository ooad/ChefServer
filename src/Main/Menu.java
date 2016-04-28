package Main;

import Models.MenuModel;
import org.json.JSONObject;

import java.util.Collection;

/**
 * Created by hank9653 on 2016/4/27.
 */
public class Menu {
    MenuModel menuModel;
    public Menu(){
        menuModel = new MenuModel();
    }
    public Collection<JSONObject> getMenu(){
        return menuModel.getMealList();
    }
    public void setMenu(Meal[] meals){
    }
}
