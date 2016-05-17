/**
 * Created by hank9653 on 2016/4/27.
 */

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class ChefServerService {
    private static final int PORT = 9002;
    private static HashMap<String, Socket> user = new HashMap<String, Socket>();
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        int i=0;
        try {
            while (true) {
                Socket socket = listener.accept();
                if(socket != null){
                    int num=i++;
                    ChefHandler chefHandler = new ChefHandler(socket);
                    user.put("user"+String.valueOf(num),socket);
                    chefHandler.setUsers(ChefServerService.user);
                    chefHandler.setUser("user"+String.valueOf(num));
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
