package Main; /**
 * Created by hank9653 on 2016/4/27.
 */

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.HashMap;


public class ChefServerService {
    private static final int PORT = 9001;
    private static HashMap<String, PrintWriter> test = new HashMap<String, PrintWriter>() ;
    private static HashSet<String> names = new HashSet<String>();
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new ChefHandler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }
}
