import Main.ChefHandler;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Created by hank9653 on 2016/5/10.
 */
public class Maintain {
    private static final int PORT = 9001;
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
                    chefHandler.addUser(user);
                    Thread thread1 = new Thread(chefHandler);
                    thread1.start();
                }
            }
        } finally {
            listener.close();
        }
    }
}
