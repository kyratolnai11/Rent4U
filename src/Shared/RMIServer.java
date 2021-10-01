package Shared;

import Client.Model.*;
import Util.*;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface RMIServer extends Remote
{
    /**
     * This method is to start the server. The method can be accessed in the ServerImpl class.
     * @throws RemoteException
     * @throws AlreadyBoundException
     */
    void startServer() throws RemoteException, AlreadyBoundException;
    /**
     * This method for adding new Vehicles in the system. The method can be accessed in the ServerImpl class.
     * @param vehicle
     * @throws SQLException
     * @throws RemoteException
     */
    void addVehicle(Vehicle vehicle) throws SQLException, RemoteException;
    /**
     * This method is for getting all the Vehicles in the system. The method can be accessed in the ServerImpl class.
     * @return Arraylist of Vehicles
     * @throws SQLException
     * @throws RemoteException
     */
    ArrayList<Vehicle> viewAllVehicles() throws SQLException, RemoteException;
    /**
     * This method is for setting the status of an existing Vehicle in the system. The method can be accessed in the ServerImpl class.
     * @param vehicle
     * @param status
     * @throws RemoteException
     * @throws SQLException
     */
    void setStatus(Vehicle vehicle, Status status) throws RemoteException, SQLException;
    /**
     * This method is for creating a new Booking in the system. The method can be accessed in the ServerImpl class.
     * @param booking
     * @throws RemoteException
     * @throws SQLException
     */
    void createBooking(Booking booking) throws RemoteException, SQLException;
    /**
     * This method is for editing an existing Vehicles information in the System. The method can be accessed in the ServerImpl class.
     * @param vehicle
     * @param newVehicle
     * @throws RemoteException
     * @throws SQLException
     */
    void editVehicleInfo(Vehicle vehicle, Vehicle newVehicle) throws RemoteException, SQLException;
    /**
     * This method is for getting all the Bookings in the system. The method can be accessed in the ServerImpl class.
     * @return Arraylist of Booking
     * @throws RemoteException
     * @throws SQLException
     */
    ArrayList<Booking> viewAllBookings() throws RemoteException, SQLException;
    /**
     * This method is for editing an existing Booking's information in the system. The method can be accessed in the ServerImpl class.
     * @param booking
     * @param newBooking
     * @throws RemoteException
     * @throws SQLException
     */
    void editBookingInfo(Booking booking, Booking newBooking) throws RemoteException, SQLException;
    /**
     * This method is for returning all the available Vehicles in a specific time period. The method can be accessed in the ServerImpl class.
     * @param startDate
     * @param endDate
     * @param type
     * @return Arraylist of Vehicles
     * @throws RemoteException
     * @throws SQLException
     */
    ArrayList<Vehicle> getFreeVehicles(GregorianCalendar startDate, GregorianCalendar endDate, String type) throws RemoteException, SQLException;
    /**
     * This method is for creating an account in the system from a Customer's point of view.
     *  The method can be accessed in the ServerImpl class.
     * @param customer
     * @throws RemoteException
     * @throws SQLException
     */
    void createCustomerAccount(Customer customer) throws RemoteException, SQLException;
    /**
     * This method is for getting all the Customer that are in the system. The method can be accessed in the ServerImpl class.
     * @return Arraylist Customer
     * @throws RemoteException
     * @throws SQLException
     */
    ArrayList<Customer> getCustomers() throws RemoteException, SQLException;
    /**
     * This method for editing an existing Customer's information in the system. The method can be accessed in the ServerImpl class.
     * @param customer
     * @param newCustomer
     * @throws RemoteException
     * @throws SQLException
     */
    void editCustomerInfo(Customer customer, Customer newCustomer)throws RemoteException, SQLException;
    /**
     * This method is for deleting Vehicles in the system. The method can be accessed in the ServerImpl class.
     * @param vehicle
     * @throws RemoteException
     * @throws SQLException
     */
    void deleteVehicle(Vehicle vehicle) throws RemoteException, SQLException;
    /**
     * This method is for creating a new account in the system, from the Customer's point of view.
     *  The method can be accessed in the ServerImpl class.
     * @param customer
     * @throws RemoteException
     * @throws SQLException
     */
    void createPersonalAccount(Customer customer) throws RemoteException, SQLException;
    /**
     * This method is for deleting booking in the system. The method can be accessed in the ServerImpl class.
     * @param booking
     * @throws RemoteException
     * @throws SQLException
     */
    void deleteBooking(Booking booking) throws RemoteException, SQLException;
    /**
     * This method is for deleting Customers in the system. The method can be accessed in the ServerImpl class.
     * @param customer
     * @throws RemoteException
     * @throws SQLException
     */
    void deleteCustomer(Customer customer) throws RemoteException, SQLException;
    /**
     * This method is for checking whether the user inputted a correct password or not. The method can be accessed in the ServerImpl class.
     * @param emailAddress
     * @param password
     * @return Customer
     * @throws RemoteException
     * @throws SQLException
     */
    Customer checkForPassword(String emailAddress, String password) throws RemoteException, SQLException;
    /**
     * This method is for editing Customer's information in the system, from the Customer's point of view.
     *  The method can be accessed in the ServerImpl class.
     * @param customer
     * @param newCustomer
     * @throws RemoteException
     * @throws SQLException
     */
    void editPersonalInfo(Customer customer, Customer newCustomer)throws RemoteException, SQLException;
    /**
     * This method is for returning all the bookings for one specific Customer.
     *  The method can be accessed in the ServerImpl class.
     * @param customer
     * @return Arraylist of Booking
     * @throws RemoteException
     * @throws SQLException
     */
    ArrayList<Booking> getPersonalBookings(Customer customer) throws RemoteException, SQLException;
    /**
     * This method is for editing a booking in the system, from a Customer's point of view.
     *  The method can be accessed in the ServerImpl class.
     * @param booking
     * @param newBooking
     * @throws RemoteException
     * @throws SQLException
     */
    void editPersonalBooking(Booking booking, Booking newBooking) throws RemoteException, SQLException;
    /**
     * This method is for creating an Employee in the system, only the Manager can access it.
     *  The method can be accessed in the ServerImpl class.
     * @param employee
     * @throws RemoteException
     * @throws SQLException
     */
    void createEmployee(Employee employee) throws RemoteException, SQLException;
    /**
     * This method is for deleting a booking from the system, from a Customer's point of view.
     *  The method can be accessed in the ServerImpl class.
     * @param booking
     * @throws RemoteException
     * @throws SQLException
     */
    void deletePersonalBooking(Booking booking) throws RemoteException, SQLException;
    /**
     * This method is for editing Employee's information in the system, only a Manager can access it.
     *  The method can be accessed in the ServerImpl class.
     * @param employee
     * @param newEmployee
     * @throws RemoteException
     * @throws SQLException
     */
    void editEmployeeInfo(Employee employee,Employee newEmployee) throws RemoteException, SQLException;
    /**
     * This method is for getting all the employees in the system. The method can be accessed in the ServerImpl class.
     * @return Arraylist of Employee
     * @throws RemoteException
     * @throws SQLException
     */
    ArrayList<Employee> getEmployees() throws RemoteException, SQLException;
    /**
     * This method is for deleting an Employee from the system, only a Manager can access this method.
     * @param employee
     * @throws RemoteException
     * @throws SQLException
     */
    void deleteEmployee(Employee employee) throws RemoteException, SQLException;
}
