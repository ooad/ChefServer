package Models;

import java.sql.ResultSet;

/**
 * Created by hank9653 on 2016/5/15.
 */
public class RestaurantModel {
    public ResultSet getAllRestaurant(){
        DB db = new DB();
        String sql = "SELECT * FROM restaurant";
        ResultSet result = db.query(sql);
        return result;
    }
}
