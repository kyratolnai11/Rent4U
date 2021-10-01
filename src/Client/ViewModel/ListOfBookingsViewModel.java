package Client.ViewModel;

import Util.Booking;
import Client.Model.ClientModel;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListOfBookingsViewModel
{
  private ClientModel userModel;

  public ListOfBookingsViewModel(ClientModel userModel)
  {
    this.userModel=userModel;
  }

  public ArrayList<Booking> getBookings() throws SQLException, RemoteException
  {
    return userModel.getBookings();
  }

  public void deleteBooking(Booking booking) throws RemoteException, SQLException
  {
    userModel.deleteBooking(booking);
  }

}

