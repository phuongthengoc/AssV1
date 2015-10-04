package controller;

import lib.Server;
import lib.Status;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by panda on 27/09/2015
 */
public class FromServer {
    private Server server;
    public FromServer() {
        try {
            this.server = (Server) Naming.lookup("rmi://localhost:4000/server");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean checkUserLogin(String username, String password){
        try {
            if(server.checkUser(username,password)){
                return true;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean checkLockUser(String Username){
        try {
            if(server.checkLockUser(Username)){
                return true;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List getAllFriendStatus(int id) {
        List<Status> list = null;
        try {
            list = server.getAllFriendsStatus(id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getFirstNameFromId(int id) {
        String str = null;
        try {
            str = server.getFirstNameFromId(id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return str;
    }

    public String getLastNameFromId(int id){
        String str = null;
        try {
            str = server.getLastNameFromId(id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return str;
    }

    public int getIdFromUsername(String Username) {
        int id = 0;
        try {
            id = server.getIdFromName(Username);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void posStatus(int id, String status){
        try {
            server.postStatus(id,status);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FromServer fromServer = new FromServer();
//        System.out.println(fromServer.checkUserLogin("panda", "123"));
//        System.out.println(fromServer.checkUserLogin("panda", "123"));
//        System.out.println(fromServer.getAllFriendStatus(1));
        System.out.println(fromServer.getIdFromUsername("panda"));
        fromServer.posStatus(1,"test 005");

    }

}
