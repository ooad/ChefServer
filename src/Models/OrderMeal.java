package Models;

        import Entities.OrderedMeal;

        import java.sql.ResultSet;
        import java.util.ArrayList;

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
    public ResultSet getMealStatus(int idUser){
        DB db = new DB();
        String sql = "SELECT * FROM ordermeal WHERE idUser="+idUser;
        ResultSet result = db.query(sql);
        db.close();
        return result;
    }
    public ArrayList<OrderedMeal> getOrderMenu(int idRestaurant, int tableNum, int idUser){
        ArrayList<OrderedMeal> orderMenu = new ArrayList<OrderedMeal>();
        DB db = new DB();
        String sql = "SELECT * FROM ordermeal NATURAL JOIN meal WHERE idRestaurant=" + idRestaurant + " AND "
                + "tableNum=" + tableNum + " AND "
                + "idUser=" + idUser;
        ResultSet result = db.query(sql);
        try{
            while(result.next()){
                OrderedMeal orderedMeal = new OrderedMeal();
                orderedMeal.setOrderedMeal(result.getInt("idOrderMeal"), result.getString("mealName"), result.getString("mealDescription"), result.getInt("mealPrice"), result.getString("mealType"), result.getInt("mealStatus"));
                orderMenu.add(orderedMeal);
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        db.close();
        return orderMenu;
    }
}
