import java.io.*;
import java.net.Socket;

/**
 * Created by hank9653 on 2016/4/28.
 */
public class ChefHandler extends Thread {
    private String receiveMessage;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ChefHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(),"UTF-8"));
            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            out = new PrintWriter(osw, true);

            Service service = new Service(this);

            while(true){
                receiveMessage = in.readLine();
                switch (receiveMessage){
                    case "1":
                    break;
                    default:
                        System.out.println(receiveMessage);
                        service.SelectService(receiveMessage);
                }
            }
                /*out.println("NAMEACCEPTED");
                writers.add(out);*/
        } catch (IOException e) {

        } finally {

        }
    }

    public void FeedbackToClient(String message) {
        out.println(message);
    }
}
