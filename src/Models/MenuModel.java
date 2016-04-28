package Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by hank9653 on 2016/4/28.
 */

public class MenuModel {
    private Connection con = null;
    public MenuModel(){
        connectDB();
    }
    public Collection<JSONObject> getMealList(){
        ResultSet result = null;
        Collection<JSONObject> queryData = new ArrayList<>();
        try{
            String sql = "SELECT * FROM meal";
            result = con.createStatement().executeQuery(sql);
            System.out.println("idMeal\t\tname\t\tdescription\t\tprice\t\tmealType");
            while(result.next())
            {
                JSONObject subItem = new JSONObject();
                System.out.println(result.getInt("idMeal")+"\t\t"+
                        result.getString("name")+"\t\t"+result.getString("description")+"\t\t"+result.getString("price")+"\t\t"+result.getString("mealType"));
                subItem.put("idMeal",result.getInt("idMeal"));
                subItem.put("name",result.getString("name"));
                subItem.put("description",result.getString("description"));
                subItem.put("price",result.getInt("price"));
                subItem.put("mealType",result.getString("mealType"));
                queryData.add(subItem);
            }
            System.out.println(queryData.toString());

        }catch(SQLException e){
            System.out.println("DropDB Exception :" + e.toString());
        } catch (JSONException e) {
            System.out.println("Json Exception :" + e.toString());
        } finally{
            try{
                if (result != null) {
                    result.close();
                }
            }
            catch(SQLException e){
                System.out.println("Close Exception :" + e.toString());
            }
        }
        return queryData;
    }

    private void connectDB(){
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
