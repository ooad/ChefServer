import org.json.JSONObject;

/**
 * Created by hank9653 on 2016/4/27.
 */
public class Menu {
    MenuModel menuModel;
    public Menu(){
        menuModel = new MenuModel();
    }
    public JSONObject getMenu(){
        return menuModel.getMealList();
    }
    public void setMenu(Meal[] meals){
    }
}
