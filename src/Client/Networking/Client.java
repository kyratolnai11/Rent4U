package Client.Networking;

import Client.Model.*;
import Util.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface Client
{
  /**
   * This method is used to connect the Client to the Server
   */
    void startClient();
  /**
   * This method is used for adding a new vehicle into the system. The method is connected to the server.
   * @param vehicle
   * @throws SQLException
   * @throws RemoteException
   */
  void addVehicle(Vehicle vehicle) throws SQLException, RemoteException;
  /**
   * this method is used to return all the vehicles. The method is connected to the server.
   * @return Arraylist of Vehicles
   * @throws SQLException
   * @throws RemoteException
   */
    ArrayList<Vehicle> getListOfVehicles() throws SQLException, RemoteException;
  /**
   * This method si used to set the status of an existing vehicle in the system. The method is connected to the server.
   * @param vehicle
   * @param status
   * @throws RemoteException
   * @throws SQLException
   */
    void setStatus(Vehicle vehicle, Status status) throws RemoteException, SQLException;
  /**
   * This method is used to create bookings in the system. The method is connected to the server.
   * @param booking
   * @throws RemoteException
   * @throws SQLException
   */
    void createBooking(Booking booking) throws RemoteException, SQLException;
  /**
   * This method is used to edit information regarding vehicles in the system. The method is connected to the server.
   * @param vehicle
   * @param newVehicle
   * @throws RemoteException
   * @throws SQLException
   */
    void editVehicleInfo(Vehicle vehicle, Vehicle newVehicle) throws RemoteException, SQLException;
  /**
   * This method is used to return all the Bookings that are in the system. The method is connected to the server.
   * @return Arraylist of Booking
   * @throws SQLException
   * @throws RemoteException
   */
    ArrayList<Booking> getListOfBookings() throws SQLException, RemoteException;
  /**
   * This method is used to edit an existing booking in the system. The method is connected to the server.
   * @param booking
   * @param newBooking
   * @throws RemoteException
   * @throws SQLException
   */
    void editBookingInfo(Booking booking, Booking newBooking) throws RemoteException, SQLException;
  /**
   * This method is used to see al the available vehicles for a specific timeframe. The method is connected to the server.
   * @param startDate
   * @param endDate
   * @param type
   * @return Arraylist of Vecihles
   * @throws RemoteException
   * @throws SQLException
   */
    ArrayList<Vehicle> getFreeVehicles(GregorianCalendar startDate, GregorianCalendar endDate, String type) throws RemoteException, SQLException;
  /**
   * This method is used to create account in the system. The method is connected to the server.
   * @param customer
   * @throws RemoteException
   * @throws SQLException
   */
    void createCustomerAccount(Customer customer) throws RemoteException, SQLException;
  /**
   * This method is used to return all the customers that can be found in the system. The method is connected to the server.
   * @return Arraylist of Customers
   * @throws SQLException
   * @throws RemoteException
   */
    ArrayList<Customer> getCustomers() throws SQLException, RemoteException;
  /**
   * This method is used to edit existing Customers data in the system. The method is connected to the server.
   * @param customer
   * @param newCustomer
   * @throws RemoteException
   * @throws SQLException
   */
  void editCustomerInfo(Customer customer, Customer newCustomer)throws RemoteException, SQLException;
  /**
   * This method is used to create a Customer account in the system from the Customer's point of view. The method is connected to the server.
   * @param customer
   * @throws RemoteException
   * @throws SQLException
   */
  void createPersonalAccount(Customer customer) throws RemoteException, SQLException;
  /**
   * This method is used to delete Vehicles from the system. The method is connected to the server.
   * @param vehicle
   * @throws RemoteException
   * @throws SQLException
   */
  void deleteVehicle(Vehicle vehicle) throws RemoteException, SQLException;
  /**
   * This method is used to delete a booking from the system. The method is connected to the server.
   * @param booking
   * @throws RemoteException
   * @throws SQLException
   */
  void deleteBooking(Booking booking) throws RemoteException, SQLException;
  /**
   * This method is used to delete Customers from the system. The method is connected to the server.
   * @param customer
   * @throws RemoteException
   * @throws SQLException
   */
  void deleteCustomer(Customer customer) throws RemoteException, SQLException;
  /**
   * This method is used to check whether the password that the User inputted is correct or not. The method is connected to the server.
   * @param emailAddress
   * @param password
   * @return Customer
   * @throws RemoteException
   * @throws SQLException
   */
  Customer checkForPassword(String emailAddress, String password) throws RemoteException, SQLException;
  /**
   * This method is used to edit personal information of existing Customers. The method is connected to the system.
   * @param customer
   * @param newCustomer
   * @throws RemoteException
   * @throws SQLException
   */
  void editPersonalInfo(Customer customer,Customer newCustomer)throws RemoteException, SQLException;
  /**
   * This method is used to get all the bookings for a specific customer that are in the system. The method is connected to the server.
   * @param customer
   * @return Arraylist of Customer
   * @throws RemoteException
   * @throws SQLException
   */
  ArrayList<Booking> getPersonalBookings(Customer customer) throws RemoteException, SQLException;
  /**
   * This method is for editing Users information in the system with a Customer's point of view. The method si connected to the server.
   * @param booking
   * @param newBooking
   * @throws RemoteException
   * @throws SQLException
   */
  void editPersonalBooking(Booking booking, Booking newBooking) throws RemoteException, SQLException;
  /**
   * This method is for creating Employees in the system and can be only accessed by a Manager. The method is connected to the server.
   * @param employee
   * @throws RemoteException
   * @throws SQLException
   */
  void createEmployee(Employee employee) throws RemoteException, SQLException;
  /**
   * This method is for deleting booking from the system with a Customer's point of view. The method is connected to the server.
   * @param booking
   * @throws RemoteException
   * @throws SQLException
   */
  void deletePersonalBooking(Booking booking) throws RemoteException, SQLException;
  /**
   * This method is for editing employees information in the system. The method is connected to the server.
   * @param employee
   * @param newEmployee
   * @throws RemoteException
   * @throws SQLException
   */
  void editEmployeeInfo(Employee employee,Employee newEmployee) throws RemoteException, SQLException;
  /**
   * This method is for showing all the Employees in the system. The method is connected to the server
   * @return Arraylist of Employees
   * @throws RemoteException
   * @throws SQLException
   */
  ArrayList<Employee> getEmployees() throws RemoteException, SQLException;
  /**
   * This method is used to delete an Employee from the system. The method is connected to the server.
   * @param employee
   * @throws RemoteException
   * @throws SQLException
   */
  void deleteEmployee(Employee employee) throws RemoteException, SQLException;
}
