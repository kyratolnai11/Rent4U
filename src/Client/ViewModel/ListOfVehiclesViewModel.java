package Client.ViewModel;

import Client.Model.ClientModel;
import Util.Vehicle;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListOfVehiclesViewModel
{
  private ClientModel userModel;

  public ListOfVehiclesViewModel(ClientModel userModel)
  {
    this.userModel=userModel;
  }

  public ArrayList<Vehicle> getVehicles() throws SQLException, RemoteException {
    return userModel.getVehicles();
  }

  public void deleteVehicle(Vehicle vehicle) throws RemoteException, SQLException
  {
    userModel.deleteVehicle(vehicle);
  }

}
