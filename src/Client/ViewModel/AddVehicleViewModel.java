package Client.ViewModel;

import Client.Model.ClientModel;
import Util.Vehicle;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class AddVehicleViewModel
{
  private ClientModel userModel;

  public AddVehicleViewModel(ClientModel userModel)
  {
    this.userModel=userModel;
  }

  public void addVehicle(String licensePlate, int enginePower, String type, String make, String model, int year,
      String gearBoxType, String fuelType, int numberOfSeats, double price) throws SQLException, RemoteException {
    userModel.addVehicle(new Vehicle(licensePlate,enginePower,type,make,model,year,gearBoxType,fuelType, numberOfSeats,price));
  }
}
