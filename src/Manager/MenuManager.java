package Manager;

import Main.Menu;
import Models.MenuModel;

import java.util.ArrayList;

/**
 * Created by hank9653 on 2016/5/2.
 */
public class MenuManager {
    private MenuModel menuModel= new MenuModel();
    public Menu getMenu() {
        Menu menu;
        menu = new Menu();
        return menu;
    }

    public ArrayList<String> getMealType(int restaurant) {
        return menuModel.getMealType(restaurant);
    }
    public String getMeal(int restaurant) {
        return menuModel.getMeal(restaurant);
    }
}
