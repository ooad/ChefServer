package Models;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by hank9653 on 2016/4/29.
 */
public class DB {
    private Connection con = null;
    private String sql = null;
    private ResultSet result = null;
    Collection<JSONObject> queryData = new ArrayList<>();
    public DB(){
        connectDB();
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

    public ResultSet query(String sql){
        try {
            result = con.createStatement().executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void close(){
        try {
            result.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("DB SQL error :"+e.toString());
        }
    }
}
