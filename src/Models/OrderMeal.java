package Models;

/**
 * Created by hank9653 on 2016/5/9.
 */
public class OrderMeal {
    public boolean OrderMeal(int idRestaurant,int tableNum,int idMeal ,char mealStatus ,int idUser){
        DB db = new DB();
        String sql = "INSERT INTO ordermeal (idRestaurant, tableNum, idMeal , mealStatus ,idUser) " +
                "VALUES ("+idRestaurant+","+tableNum+","+idMeal+","+mealStatus+","+idUser+")";
        boolean result = db.execute(sql);
        db.close();
        return result;
    }
    public boolean changeMealStatus(int idUser,int idMeal,char mealStatus){
        DB db = new DB();
        String sql = "UPDATE ordermeal SET mealStatus='"+mealStatus+"'" +
                "WHERE idUser='"+idUser+"' AND idMeal='"+idMeal+"'";
        boolean result = db.execute(sql);
        db.close();
        return result;
    }
}
