package Client.ViewModel;

import Util.Booking;
import Client.Model.ClientModel;
import Util.Customer;
import Util.Vehicle;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AddBookingViewModel
{
  private ClientModel userModel;


  public AddBookingViewModel(ClientModel userModel){
    this.userModel= userModel;

  }
  public void createBooking(String idOfCustomer, String licencePlate, GregorianCalendar startTime, GregorianCalendar endTime, double price) throws RemoteException, SQLException {

    userModel.createBooking(new Booking(idOfCustomer,licencePlate,startTime,endTime,price));
  }

  public ArrayList<Vehicle> getVehicles() throws SQLException, RemoteException {
    return userModel.getVehicles();
  }

  public ArrayList<Vehicle> getFreeVehicles(GregorianCalendar startDate, GregorianCalendar endDate, String type) throws SQLException, RemoteException
  {
    return userModel.getFreeVehicles(startDate, endDate, type);
  }

  public ArrayList<String> getAllCustomersID() throws RemoteException, SQLException {
    ArrayList<String> IDs = new ArrayList<>();
    ArrayList<Customer> customers = userModel.getCustomers();
    for (int i = 0; i < customers.size(); i++)
    {
      IDs.add(customers.get(i).getCpr_number());
    }
    return IDs;
  }
}
