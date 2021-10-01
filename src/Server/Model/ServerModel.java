package Server.Model;

import Client.Model.*;
import Util.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface ServerModel
{
  /**
   * This method is for adding new Vehicles into the system. The method can be accessed in the ServerModelManager class.
   * @param vehicle
   * @throws SQLException
   */
  void addVehicle(Vehicle vehicle) throws SQLException;
  /**
   * This method is for returning all the Vehicles in the system. The method can be accessed from the ServerModelManager class.
   * @return Arraylist of Vehicles
   * @throws SQLException
   */
  ArrayList<Vehicle> viewAllVehicles() throws SQLException;
  /**
   * This method is to set the status of an existing Vehicle in the system. The method can be accessed in the ServerModelManager class.
   * @param vehicle
   * @param status
   * @throws SQLException
   */
  void setStatus(Vehicle vehicle, Status status) throws SQLException;
  /**
   * This method is to create a new booking in the system. The method can be accessed in the ServerModelManager class.
   * @param booking
   * @throws SQLException
   */
  void createBooking(Booking booking) throws SQLException;
  /**
   * This method is for editing data regarding Vehicles in the system. The method can be accessed in the ServerModelManager class.
   * @param vehicle
   * @param newVehicle
   * @throws RemoteException
   * @throws SQLException
   */
  void editVehicleInfo(Vehicle vehicle, Vehicle newVehicle) throws RemoteException, SQLException;
  /**
   * Theis method is to retun all the Bookings in the system. The method can be accessed in the ServerModelManager class.
   * @return Arraylist of Booking
   * @throws SQLException
   */
  ArrayList<Booking> viewAllBookings() throws SQLException;
  /**
   * This method is to edit data regarding Bookings in the system. The method can be accessed in the ServerModelManager class.
   * @param booking
   * @param newBooking
   * @throws RemoteException
   * @throws SQLException
   */
  void editBookingInfo(Booking booking, Booking newBooking) throws RemoteException, SQLException;
  /**
   * This method is to get all the free Vehicles for a specific time period. The method can be accessed in the ServerModelManager class.
   * @param startDate
   * @param endDate
   * @param type
   * @return Arraylist of Vehicles
   * @throws SQLException
   * @throws RemoteException
   */
  ArrayList<Vehicle> getFreeVehicles(GregorianCalendar startDate, GregorianCalendar endDate, String type) throws SQLException, RemoteException;
  /**
   * This method is to add a new Customer in the system. The method can be accessed in the ServerModelManager class.
   * @param customer
   * @throws RemoteException
   * @throws SQLException
   */
  void addCustomer(Customer customer) throws RemoteException, SQLException;
  /**
   * This method is for returning all the Customers that are in the system. The method can be accessed in the ServerModelManager class.
   * @return Arraylist of Customers
   * @throws RemoteException
   * @throws SQLException
   */
  ArrayList<Customer> viewAllCustomers() throws RemoteException, SQLException;
  /**
   * This method is to create an Account in the system from a Customer's point of view. The method can be accessed in the ServerModelManager class.
   * @param customer
   * @throws RemoteException
   * @throws SQLException
   */
  void createPersonalAccount(Customer customer) throws RemoteException, SQLException;
  /**
   * This method id for editing Customer's information in the system. The method can be accessed in the ServerModelManager class.
   * @param customer
   * @param newCustomer
   * @throws RemoteException
   * @throws SQLException
   */
  void editCustomerInfo(Customer customer, Customer newCustomer) throws RemoteException, SQLException;
  /**
   * This method is for deleting a Vehicle in the system. The method can be accessed in the ServerModelManager class.
   * @param vehicle
   * @throws RemoteException
   * @throws SQLException
   */
  void deleteVehicle(Vehicle vehicle) throws RemoteException, SQLException;
  /**
   * This method is for deleting booking in the system. The method can be accessed in the ServerModelManager class.
   * @param booking
   * @throws RemoteException
   * @throws SQLException
   */
  void deleteBooking(Booking booking) throws RemoteException, SQLException;
  /**
   * This method is for deleting Customers from the system. The method can be accessed in the ServerModelManager class.
   * @param customer
   * @throws RemoteException
   * @throws SQLException
   */
  void deleteCustomer(Customer customer) throws RemoteException, SQLException;
  /**
   * This method is for checking wether the user inputted the correct password or not. The method can be accessed in the ServerModelManager class.
   * @param emailAddress
   * @param password
   * @return Customer
   * @throws RemoteException
   * @throws SQLException
   */
  Customer checkForPassword(String emailAddress, String password) throws RemoteException, SQLException;
  /**
   * This method is to edit a Customer's information in the system from the Customer's point of view.
   *  The method can be accessed in the ServerModelManager class.
   * @param customer
   * @param newCustomer
   * @throws RemoteException
   * @throws SQLException
   */
  void editPersonalInfo(Customer customer,Customer newCustomer)throws RemoteException, SQLException;
  /**
   * This method is to edit a Customer's booking in the system from the Customer's point of view.
   *  The method can be accessed in the ServerModelManager class.
   * @param booking
   * @param newBooking
   * @throws RemoteException
   * @throws SQLException
   */
  void editPersonalBooking(Booking booking, Booking newBooking) throws RemoteException, SQLException;
  /**
   * This method is for getting all the booking for a Customer. The method can be accessed in the ServerModelManager class.
   * @param customer
   * @return Arraylist of Booking
   * @throws RemoteException
   * @throws SQLException
   */
  ArrayList<Booking> getPersonalBookings(Customer customer) throws RemoteException, SQLException;
  /**
   * This method is for create an Employee in the system, only a Manager can acces this method.
   *  The method can be accessed in the ServerModelManager class.
   * @param employee
   * @throws RemoteException
   * @throws SQLException
   */
  void createEmployee(Employee employee) throws RemoteException, SQLException;
  /**
   * This method is for deleting a booking from the system from the Customer's point of view.
   *  The method can be accessed in the ServerModelManager class.
   * @param booking
   * @throws RemoteException
   * @throws SQLException
   */
  void deletePersonalBooking(Booking booking) throws RemoteException, SQLException;
  /**
   * This method is to edit an Employee's information in the system, only a Manager can access this method.
   *  The method can be accessed in the ServerModelManager class.
   * @param employee
   * @param newEmployee
   * @throws RemoteException
   * @throws SQLException
   */
  void editEmployeeInfo(Employee employee, Employee newEmployee) throws RemoteException, SQLException;
  /**
   * This method is for getting all the Employees in the system. The method can be accessed in the ServerModelManager class.
   * @return Arraylist of Employees
   * @throws RemoteException
   * @throws SQLException
   */
  ArrayList<Employee> getEmployees() throws RemoteException, SQLException;
  /**
   * This method is for deleting an Employee from the system, only a Manager can access this method.
   *  The method can be accessed in the ServerModelManager class.
   * @param employee
   * @throws RemoteException
   * @throws SQLException
   */
  void deleteEmployee(Employee employee) throws RemoteException, SQLException;
}


