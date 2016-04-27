/**
 * Created by hank9653 on 2016/4/27.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.HashMap;
import java.io.OutputStreamWriter;


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
