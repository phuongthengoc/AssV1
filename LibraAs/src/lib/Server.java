package lib;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by panda on 27/09/2015
 */
public interface Server extends Remote {
    public boolean checkUser(String username, String password) throws RemoteException;
    public boolean checkLockUser(String Username) throws RemoteException;
    public ResultSet getStatus(int id) throws RemoteException;
    public List getAllFriendsStatus(int id) throws RemoteException;
    public int getIdFromName(String name) throws RemoteException;
    public String getFirstNameFromId(int Id) throws RemoteException;
    public String getLastNameFromId(int Id) throws RemoteException;
    public void postStatus(int id, String status) throws RemoteException;

}
