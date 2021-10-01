package Server.DatabaseAccess;

import Util.Booking;
import Util.Customer;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface ManageBookings
{
  /**
   * This method is for creating a Booking in the system. The method can be accessed in the ServerModelManager class.
   * @param booking
   * @throws SQLException
   */
  void createBooking(Booking booking) throws SQLException;
  /**
   * This method is for getting all the Bookings in the system. The method can be accessed in the ServerModelManager class.
   * @return Arraylist of Booking
   * @throws SQLException
   */
  ArrayList<Booking> viewAllBookings() throws SQLException;
  /**
   * This method is for editing a booking in the system. The method can be accessed in the ServerModelManager class.
   * @param booking
   * @param newBooking
   * @throws RemoteException
   * @throws SQLException
   */
  void editBookingInfo(Booking booking, Booking newBooking) throws RemoteException, SQLException;
  /**
   * This method is for deleting a Booking from the System. The method can be accessed in the ServerModelManager class.
   * @param booking
   * @throws RemoteException
   * @throws SQLException
   */
  void deleteBooking(Booking booking) throws RemoteException, SQLException;
  /**
   * This method is for getting all the bookings for a specific Customer. The method can be accessed in the ServerModelManager class.
   * @param customer
   * @return Arraylist of Booking
   * @throws RemoteException
   * @throws SQLException
   */
  ArrayList<Booking> getPersonalBookings(Customer customer) throws RemoteException, SQLException;
  /**
   * This method is for editing a booking from a Customer's point of view. The method can be accessed in the ServerModelManager class.
   * @param booking
   * @param newBooking
   * @throws RemoteException
   * @throws SQLException
   */
  void editPersonalBooking(Booking booking, Booking newBooking) throws RemoteException, SQLException;
  /**
   * This method is for deleteing a booking from the system from a Customer's point of view.
   *  The method can be accessed in the ServerModelManager class.
   * @param booking
   * @throws RemoteException
   * @throws SQLException
   */
  void deletePersonalBooking(Booking booking) throws RemoteException, SQLException;
}
