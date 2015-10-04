package controller;

import lib.Server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Created by panda on 27/09/2015
 */
public class Start {
    public Start() {
        try {
            Server server = new ToClient();
            LocateRegistry.createRegistry(4000);
            Naming.rebind("rmi://localhost:4000/server",server);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Start();
    }
}
