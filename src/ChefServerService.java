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
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }


    private static class Handler extends Thread {
        private String receiveMessage;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream(),"UTF-8"));
                OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
                out = new PrintWriter(osw, true);

                while(true){
                    receiveMessage = in.readLine();
                    System.out.println(receiveMessage);
                    if (receiveMessage.startsWith("ISCONNECTSERVER")) {
                        System.out.println("entry");
                        out.println("ISCONNECT");
                    }else if(receiveMessage.startsWith("GETMENU")){
                        sendMenu();
                    }
                }

                /*out.println("NAMEACCEPTED");
                writers.add(out);*/
            } catch (IOException e) {

            } finally {

            }
        }

        public void sendMenu(){
            System.out.println("getMenu");
            Menu menu = new Menu();
            out.println(menu.getMenu());
        }
    }
}
