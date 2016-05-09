package client;

import Main.Menu;

import java.io.*;
import java.net.Socket;


public class ChefClientService {
    BufferedReader receiveFromChefServer;
    OutputStreamWriter osw;
    PrintWriter sendToChefServer;
    Socket socket;
    boolean isConnectSerer=false;
    final String serverAddress = "140.124.42.169";
    final int port = 9001;
    public ChefClientService() {
        try {
            socket = new Socket(serverAddress, port);
            receiveFromChefServer = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(),"UTF-8"));
            osw = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            sendToChefServer = new PrintWriter(osw, true);

            while (true) {
                sendToChefServer.println("{'requestServiceType':'ChefClientService','requestService':'connectToServer','requestInt':123}");
                String receiveMessage = receiveFromChefServer.readLine();
                if (receiveMessage.startsWith("You are connect to server.")) {
                    System.out.println("You are connect to server.");
                    break;
                }
            }
            socket.close();
            System.out.println("Close connect.");
        } catch (IOException e) {
        }
    }

    private Menu getMenu() {
        //String menu;
        try {
            sendToChefServer.println("{'requestServiceType':'MenuManager','requestService':'getMenu'}");
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Menu menu = null;
            try {
                menu = (Menu) in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            // menu = receiveFromChefServer.readLine();
            return menu;
        } catch (IOException e) {
            System.out.println("NULL");
            return null;
        }

    }

    private String orderMeal() {
        return "orderMeal";
    }

    private String updateMeal() {
        return "updateMeal";
    }

    private String deleteMeal() {
        return "deleteMeal";
    }

    private boolean isConnectSerer() {
        return true;
    }

    public static void main(String[] args){
        System.out.println("run");
        ChefClientService sss=new ChefClientService();
        /*Menu meal = sss.getMenu();
        for(Meal v : meal.getMealList()){
            System.out.println(v.name);
        }*/
       //System.out.println(sss.getMenu());

    }
}