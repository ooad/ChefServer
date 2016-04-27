import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hank9653 on 2016/4/28.
 */

public class MenuModel {
    private Connection con = null;
    public MenuModel(){
        connectDB();
    }
    public JSONObject getMealList(){
        ResultSet result = null;
        JSONObject jsonObject = new JSONObject();
        try
        {
            String sql = "SELECT * FROM meal";
            result = con.createStatement().executeQuery(sql);
            System.out.println("idMeal\t\tname\t\tdescription\t\tprice\t\tmealType");
            while(result.next())
            {
                System.out.println(result.getInt("idMeal")+"\t\t"+
                        result.getString("name")+"\t\t"+result.getString("description")+"\t\t"+result.getString("price")+"\t\t"+result.getString("mealType"));
                jsonObject.put("idMeal",result.getInt("idMeal"));
                jsonObject.put("name",result.getString("name"));
                jsonObject.put("description",result.getString("description"));
                jsonObject.put("price",result.getInt("price"));
                jsonObject.put("mealType",result.getString("mealType"));
                System.out.println(jsonObject.toString());
            }
        }
        catch(SQLException e)
        {
            System.out.println("DropDB Exception :" + e.toString());
        } catch (JSONException e) {
            System.out.println("Json Exception :" + e.toString());
        } finally
        {
            try{
                result.close();
            }
            catch(SQLException e){
                System.out.println("Close Exception :" + e.toString());
            }
        }
        return jsonObject;
    }

    public void connectDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //註冊driver
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/chef?useUnicode=true&characterEncoding=UTF8",
                    "root","1qaz@WSX");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("DriverClassNotFound :"+e.toString());
        }//有可能會產生sqlexception
        catch(SQLException x) {
            System.out.println("Exception :"+x.toString());
        }
    }
}
