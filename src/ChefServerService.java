/**
 * Created by hank9653 on 2016/4/27.
 */

import Main.ChefHandler;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class ChefServerService {
    private static final int PORT = 9002;
    private static HashMap<String, Socket> user = new HashMap<String, Socket>();
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                Socket socket = listener.accept();
                if(socket != null){
                    ChefHandler chefHandler = new ChefHandler(socket);
                    user.put("user",socket);
                    chefHandler.updateUser(user);
                    Thread thread1 = new Thread(chefHandler);
                    thread1.start();
                }
            }
        } finally {
            listener.close();
        }
    }
}
//test
