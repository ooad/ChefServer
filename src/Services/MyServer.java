package Services;

import java.io.*;
import java.net.Socket;

/**
 * Created by hank9653 on 2016/5/5.
 */
public class MyServer {
    private Socket socket;
    private PrintWriter respondToClient;
    private BufferedReader requestFromClient;
    public MyServer(Socket socket){
        try {
            this.requestFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            this.respondToClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String requestFromClient(){
        try {
            return requestFromClient.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void changeStatus(int idUser, int idMeal, char mealStatus){
        respondToClient.println();
    }

    public void respondToClient(String message){
        respondToClient.println(message);
    }
    public void closeRespond(){
        respondToClient.println("CLOSECONNECT");
    }
    public void close(){
        try {
            if(socket != null){
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("使用者離開:關閉連線");;
        }
    }
}
