package Client.Model;

import Util.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface ClientModel
{
  /**
   * This method is used for adding a new vehicle into the system. The method is accessed from the AddVehicleViewController
   * and passed through the AddVehicleViewModel.
   * The Vehicle parameter is created in the ViewModel from the data that is inputted in the ViewController.
   * @param vehicle
   * @throws SQLException
   * @throws RemoteException
   */
  void addVehicle(Vehicle vehicle) throws SQLException, RemoteException;
  /**
   * This method is used for returning all of the vehicles within the system. This method is used multiple times for example: EditBookingInfoViewModel,
   * EditPersonalBookingViewModel.
   * @return Arraylist of Vehicles
   * @throws SQLException
   * @throws RemoteException
   */
  ArrayList<Vehicle> getVehicles() throws SQLException, RemoteException;
  /**
   * This method is used to set the status of a specific vehicle in the system. The method is accessed in the SetStatusViewController
   * and passed through the SetStatusViewModel.
   *The new status is inputted in a TextFiled within the GUI and paired in the ViewController.
   * @param vehicle
   * @param status
   * @throws RemoteException
   * @throws SQLException
   */
  void setStatus(Vehicle vehicle, Status status) throws RemoteException, SQLException;
  /**
   * This method is used to create bookings in the system. The data for the booking is accessed from the AddBookingCustomerViewController
   * and passed to the ViewModel where the booking is created.
   * @param booking
   * @throws RemoteException
   * @throws SQLException
   */
  void createBooking(Booking booking) throws RemoteException, SQLException;
  /**
   * This method is used for editing existing Vehicles within the system. The data is collected from the EditVehicleInfoViewController and sent
   * to the EditVehicleInfoViewModel. Within the ViewModel the a newVehicle is created and paired with the old one.
   * @param vehicle
   * @param newVehicle
   * @throws RemoteException
   * @throws SQLException
   */
  void editVehicleInfo(Vehicle vehicle,Vehicle newVehicle) throws RemoteException, SQLException;
  /**
   * This method is used to access all the bookings within thy system. This is crucial to create a list of bookings.
   * @return Arraylist of Bookings
   * @throws RemoteException
   * @throws SQLException
   */
  ArrayList<Booking> getBookings() throws RemoteException, SQLException;
  /**
   * This method is used to edit existing Bookings within the system. It is used both in the EditBookingViewModel and EditPersonalViewModel.
   * The data is collected from both of the ViewControllers and passed to the correct ViewModel where the newBooking is created. Than
   * the new one is paired with the old one in order to change the data.
   * @param booking
   * @param newBooking
   * @throws RemoteException
   * @throws SQLException
   */
  void editBookingInfo(Booking booking, Booking newBooking) throws RemoteException, SQLException;
  /**
   * This method is used to create a customer account in the system. The data is collected from the AddPersonalAccountViewController and passed to the
   * AddPersonalViewModel where the new booking is created and added to the system.
   * @param customer
   * @throws RemoteException
   * @throws SQLException
   */
  void createCustomerAccount(Customer customer) throws RemoteException, SQLException;
  /**
   * This method is used to return all the Customers within the system. Is is used to create a list of customers and also
   * for employees when they are making a booking for a customer.
   * @return Arraylist of Customers
   * @throws RemoteException
   * @throws SQLException
   */
  ArrayList<Customer> getCustomers() throws RemoteException, SQLException;
  /**
   * This method is used to show all the available Vehicles within the selected time period. It is used for both the customer and the
   * employee side. The start and end date is collected from when bookings when they are added to the system.
   * @param startDate
   * @param endDate
   * @param type
   * @return Arraylist of Vehicles
   * @throws RemoteException
   * @throws SQLException
   */
  ArrayList<Vehicle> getFreeVehicles(GregorianCalendar startDate, GregorianCalendar endDate, String type) throws RemoteException, SQLException;
  /**
   * This method is used the edit a Customer's information in the system. The new data is collected from TextFields, DatePickers in the
   * EditCustomerInfoViewController and sent to the EditCustomerViewModel where the newCustomer is created. Than it is paired with the old one
   * in order to change the data in the system.
   * @param customer
   * @param newCustomer
   * @throws RemoteException
   * @throws SQLException
   */
  void editCustomerInfo(Customer customer, Customer newCustomer) throws RemoteException, SQLException;
  /**
   * This method is used to delete a vehicle from the system. This functionality is accessed from the ListOfVehiclesView where a button can be found
   * to delete a vehicle. The signal is sent from the ViewController to the ViewModel where the selected Vehicle is deleted.
   * @param vehicle
   * @throws RemoteException
   * @throws SQLException
   */
  void deleteVehicle(Vehicle vehicle) throws RemoteException, SQLException;
  /**
   * This method is used to delete an existing booking from the system. This functionality is used both in the Customer and Employee side and can
   * be accessed with a button in the ListOfBookingsView. When the delete button is pressed the signal is sent from the ViewController to
   * the ViewModel where the booking is deleted from the system.
   * @param booking
   * @throws RemoteException
   * @throws SQLException
   */
  void deleteBooking(Booking booking) throws RemoteException, SQLException;
  /**
   * This method is used to delete customers from the system. This functionality can be used from the Employee and Customer side and can be
   * accessed with a button. When the button is pressed and the conformation is success the signal is sent from the ViewController to the
   * correct ViewModel where the action is finished and the Customer is deleted from the system.
   * @param customer
   * @throws RemoteException
   * @throws SQLException
   */
  void deleteCustomer(Customer customer) throws RemoteException, SQLException;
  /**
   * This method is used when a new customer would like to create an account. The data is accessed from the AddPersonalAccountViewController and
   * sent to the AddPersonalAccountViewModel where the customer parameter is created and added to the system.
   * @param customer
   * @throws RemoteException
   * @throws SQLException
   */
  void createPersonalAccount(Customer customer) throws RemoteException, SQLException;
  /**
   * This method is used when a customer would like to log into the system. Both the emailAddress and the password is accessed from the GUI in
   * the Controller. In the Controller the password and email address is confirmed and if it's matching than an action to log the user in is sent
   * to the ViewModel.
   * @param emailAddress
   * @param password
   * @return Customer
   * @throws RemoteException
   * @throws SQLException
   */
  Customer checkForPassword(String emailAddress, String password) throws RemoteException, SQLException;
  /**
   * This method is used to edit a customer's data in the system. The new data is collected from TextFields and DataPicker from the GUI and processed
   * in the ViewController to be sent to the ViewModel where the newCustomer is created. Than it is pared to the old one to update the data
   * in the system.
   * @param customer
   * @param newCustomer
   * @throws RemoteException
   * @throws SQLException
   */
  void editPersonalInfo(Customer customer,Customer newCustomer) throws RemoteException, SQLException;
  /**
   * This method is used to show all the bookings that a customer has made. This is used to create a list for easy accessibility.
   * @param customer
   * @return Arraylist of Bookings
   * @throws RemoteException
   * @throws SQLException
   */
  ArrayList<Booking> getPersonalBookings(Customer customer) throws RemoteException, SQLException;
  /**
   * This is method is used to edit a booking that is in the system. The new data is collected from the GUI and processed in the ViewController
   * to be sent in the ViewModel where the newBooking is created. Later it is paired with the old one to update the data in the system.
   * @param booking
   * @param newBooking
   * @throws RemoteException
   * @throws SQLException
   */
  void editPersonalBooking(Booking booking, Booking newBooking) throws RemoteException, SQLException;
  /**
   * This method is used to create Employees in the system and can be only accessed by a Manager. The data is collected from the GUI and processed
   * in the AddEmployeeViewController to be sent to the AddEmployeeViewModel where the employee parameter is created and added to the system.
   * @param employee
   * @throws RemoteException
   * @throws SQLException
   */
  void createEmployee(Employee employee) throws RemoteException, SQLException;
  /**
   * This method is used to delete a booking from the system be a Customer. The action is started from a button in the ListOfPersonalBookingsView.
   * If the button is pressed and the confirmation is approved the signal is sent from the ViewController to the ViewModel where the selected
   * Booking is deleted from the booking.
   * @param booking
   * @throws RemoteException
   * @throws SQLException
   */
  void deletePersonalBooking(Booking booking) throws RemoteException, SQLException;
  /**
   * This method is used for editing employees data in the system and can be only accessed by a Manager. The data is collected from the GUI
   * and processed in the EditEmployeeInfoViewController to be sent to the EditEmployeeViewModel where the newEmployee object is created.
   * That is later paired with the old one to update the data in the system.
   * @param employee
   * @param newEmployee
   * @throws RemoteException
   * @throws SQLException
   */
  void editEmployeeInfo(Employee employee,Employee newEmployee) throws RemoteException, SQLException;
  /**
   * This method is used to get all the Employees that are in thy system. This is used for creating a list.
   * @return Arraylist of Employees
   * @throws RemoteException
   * @throws SQLException
   */
  ArrayList<Employee> getEmployees() throws RemoteException, SQLException;
  /**
   * This method is used to delete an Employee from the system and can be accessed by only a Manager. The action is started from a button in the GUI
   * and after confirming is sent through to ViewController into the ViewModel where the
   * @param employee
   * @throws RemoteException
   * @throws SQLException
   */
  void deleteEmployee(Employee employee) throws RemoteException, SQLException;
}
