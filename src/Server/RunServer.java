package Server;

import Server.Model.ServerModelManager;
import Server.Networking.ServerImpl;
import Shared.RMIServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class RunServer
{
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, SQLException {
        RMIServer server = new ServerImpl(new ServerModelManager());
        server.startServer();
    }
}
