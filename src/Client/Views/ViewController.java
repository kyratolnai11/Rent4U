package Client.Views;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ViewController
{
  /**
   * This metdof is for setting the viewHandler and the viewModelFactory. The method si implemented in every controller class.
   * @param viewHandler
   * @param viewModelFactory
   * @throws SQLException
   * @throws RemoteException
   */
  void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory) throws SQLException, RemoteException;
}
