package Entities;

import Services.MyServer;

import java.util.HashMap;

/**
 * Created by hank9653 on 2016/5/24.
 */
public class Users {
    private static HashMap<String, MyServer> users = new HashMap<String, MyServer>();
    public void add(String user, MyServer server) {
        synchronized(users){
            System.out.println("add "+user);
            users.put(user,server);
        }
    }

    public void remove(String user) {
        synchronized(users){
            System.out.println("remove "+user);
            users.remove(user);
        }
    }

    public void talkToUser(String user) {
        for (Object key : users.keySet()) {
            System.out.println("talkToUser "+user);
            if(key.equals(user)){
                MyServer myServer = users.get(key);
                if(myServer == null){
                    System.out.println("NULL");
                }else{

                    myServer.respondToClient("needToUpdate");
                }
                System.out.println("MESSAGE IS SEND");
                break;
            }
        }
    }

    public HashMap<String, MyServer> getOnlineUser() {
        return users;
    }
}
