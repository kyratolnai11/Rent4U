package Client.ViewModel;

import Client.Model.ClientModel;
import Util.Vehicle;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class EditVehicleInfoViewModel
{
  private ClientModel userModel;

  public EditVehicleInfoViewModel(ClientModel userModel)
  {
    this.userModel=userModel;
  }

  public void editVehicleInfo(Vehicle vehicle,String licensePlate, int enginePower, String type, String make, String model, int year,
      String gearBoxType, String fuelType, int numberOfSeats, double price)
          throws RemoteException, SQLException {
    Vehicle newVehicle = new Vehicle(licensePlate, enginePower, type, make, model, year, gearBoxType, fuelType, numberOfSeats, price);
    userModel.editVehicleInfo(vehicle, newVehicle);
  }
}
