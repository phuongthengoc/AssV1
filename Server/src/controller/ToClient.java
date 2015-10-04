package controller;

import lib.Server;
import lib.Status;
import model.ConnectDB;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by panda on 27/09/2015
 */
public class ToClient extends UnicastRemoteObject implements Server {
    private ResultSet rsUser;
    ConnectDB connectDB;
    protected ToClient() throws RemoteException {
        connectDB = new ConnectDB();
    }

    @Override
    public boolean checkUser(String Username, String Password) throws RemoteException {
        try {
            this.rsUser = connectDB.getUser();
            while (rsUser.next()){
                String username = rsUser.getString("Username");
                String password = rsUser.getString("Password");
                return (Username.equals(username) && Password.equals(password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkLockUser(String Username) throws RemoteException {
        try {
            this.rsUser = connectDB.getUser();
            while (rsUser.next()){
                String username = rsUser.getString("Username");
                Boolean lock = rsUser.getBoolean("Lock");
                return (Username.equals(username) && lock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ResultSet getStatus(int id) throws RemoteException {
        return connectDB.getStatus(id);
    }

    @Override
    public List getAllFriendsStatus(int id) throws RemoteException {
        String query = null;
        List<Status> list =  new ArrayList<>();
        try {
            this.rsUser = connectDB.getUser();
            rsUser.first();
            do {
                int Id = rsUser.getInt("Id");
                if(id == Id) {
                    String str = rsUser.getString("Friends");
                    String string = " ";
                    String Friends[] = str.split(",");
                    for(Object obj : Friends){
                        str = (String) obj;
                        string = string + " Or Id = " + str;
                    }
                    query = "select * from Status WHERE IdUser = " + id + string;
                    break;
                }
            } while (rsUser.next());
            rsUser = connectDB.runQuery(query);
            rsUser.first();
            do {
                int int1 = rsUser.getInt("Id");
                int int2 = rsUser.getInt("IdUser");
                String content = rsUser.getString("Content");
//                System.out.println(int1 +"  "+ int2 +"  "+ content);
                list.add(new Status(int1, int2, content));
            } while (rsUser.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getIdFromName(String name) throws RemoteException {
        try {
            this.rsUser = connectDB.getUser();
            rsUser.first();
            do {
                String username = rsUser.getString("Username");
                if(username.equals(name)){
                    return rsUser.getInt("Id");
                }
            } while (rsUser.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public String getFirstNameFromId(int Id) throws RemoteException {
        try {
            this.rsUser = connectDB.getUser();
            rsUser.first();
            do {
                int id = rsUser.getInt("Id");
                if(id == Id){
                    return rsUser.getString("Firstname");
                }
            } while (rsUser.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getLastNameFromId(int Id) throws RemoteException {
        try {
            this.rsUser = connectDB.getUser();
            rsUser.first();
            do {
                int id = rsUser.getInt("Id");
                if(id == Id){
                    return rsUser.getString("Lastname");
                }
            } while (rsUser.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void postStatus(int id, String status) throws RemoteException {
        String query = "INSERT INTO Status(IdUser,Content) VALUES(" + id + ",'"+status+"')";
        connectDB.runQueryUpdate(query);
    }


    public static void main(String[] args) throws RemoteException {
        ToClient toClient = new ToClient();
//        System.out.println(toClient.checkUser("panda", "123"));
//        System.out.println(toClient.checkUser("panda", "123"));
//        System.out.println(toClient.checkLockUser("panda"));
//        System.out.println(toClient.getIdFromName("panda01"));
//        System.out.println(toClient.getAllFriendsStatus(1));
//        toClient.postStatus(1,"TEST 004");
        toClient.getAllFriendsStatus(1);

    }
}
