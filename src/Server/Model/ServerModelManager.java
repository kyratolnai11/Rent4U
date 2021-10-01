package Server.Model;

import Server.DatabaseAccess.*;
import Util.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ServerModelManager implements ServerModel
{
    private ManageVehicles manageVehicles;
    private ManageBookings manageBookings;
    private ManageCustomers manageCustomers;
    private ManageEmployees manageEmployees;

    public ServerModelManager() throws SQLException {
        manageVehicles = new Rent4UDAO();
        manageBookings = new Rent4UDAO();
        manageCustomers = new Rent4UDAO();
        manageEmployees = new Rent4UDAO();
    }

    @Override
    public void addVehicle(Vehicle vehicle) throws SQLException {
        manageVehicles.addNewVehicle(vehicle);
    }

    @Override public ArrayList<Vehicle> viewAllVehicles() throws SQLException {
        return manageVehicles.viewAllVehicles();
    }

    @Override public void setStatus(Vehicle vehicle, Status status) throws SQLException {
        manageVehicles.setStatus(vehicle,status);
    }

    @Override public void createBooking(Booking booking) throws SQLException {
        manageBookings.createBooking(booking);
    }

    @Override public void editVehicleInfo(Vehicle vehicle, Vehicle newVehicle) throws
            RemoteException, SQLException {
        manageVehicles.editVehicleInfo(vehicle, newVehicle);
    }

    public ArrayList<Booking> viewAllBookings() throws SQLException
    {
        return manageBookings.viewAllBookings();
    }

    @Override public void editBookingInfo(Booking booking, Booking newBooking) throws RemoteException, SQLException {
        manageBookings.editBookingInfo(booking, newBooking);
    }



    @Override
    public ArrayList<Vehicle> getFreeVehicles(GregorianCalendar startDate, GregorianCalendar endDate, String type) throws RemoteException, SQLException {
        return manageVehicles.getFreeVehicles(startDate, endDate, type);
    }

    @Override
    public void addCustomer(Customer customer) throws RemoteException, SQLException {
        manageCustomers.addCustomer(customer);
    }

    @Override
    public ArrayList<Customer> viewAllCustomers() throws RemoteException, SQLException {
        return manageCustomers.viewAllCustomers();
    }


    @Override public void editCustomerInfo(Customer customer, Customer newCustomer)
        throws RemoteException, SQLException
    {
        manageCustomers.editCustomerInfo(customer, newCustomer);
    }

    @Override public void deleteVehicle(Vehicle vehicle)
        throws RemoteException, SQLException
    {
        manageVehicles.deleteVehicle(vehicle);
    }

    @Override public void deleteBooking(Booking booking)
        throws RemoteException, SQLException
    {
        manageBookings.deleteBooking(booking);
    }

    @Override public void deleteCustomer(Customer customer)
        throws RemoteException, SQLException
    {
        manageCustomers.deleteCustomer(customer);
    }

    @Override public void createPersonalAccount(Customer customer) throws RemoteException, SQLException {
        manageCustomers.createPersonalAccount(customer);
    }

    @Override public Customer checkForPassword(String emailAddress,
                                               String password) throws RemoteException, SQLException
    {
        return manageCustomers.checkForPassword(emailAddress,password);
    }

    @Override public void editPersonalInfo(Customer customer,
        Customer newCustomer) throws RemoteException, SQLException
    {
        manageCustomers.editPersonalInfo(customer,newCustomer);
    }

    @Override public void editPersonalBooking(Booking booking,
        Booking newBooking) throws RemoteException, SQLException
    {
        manageBookings.editPersonalBooking(booking,newBooking);
    }

    @Override public ArrayList<Booking> getPersonalBookings(Customer customer)
        throws RemoteException, SQLException
    {
        return manageBookings.getPersonalBookings(customer);
    }

    @Override public void createEmployee(Employee employee)
        throws RemoteException, SQLException
    {
        manageEmployees.createEmployee(employee);
    }

    @Override public void deletePersonalBooking(Booking booking)
        throws RemoteException, SQLException
    {
        manageBookings.deletePersonalBooking(booking);
    }

    @Override public void editEmployeeInfo(Employee employee,
        Employee newEmployee) throws RemoteException, SQLException
    {
        manageEmployees.editEmployeeInfo(employee,newEmployee);
    }

    @Override public ArrayList<Employee> getEmployees()
        throws RemoteException, SQLException
    {
        return manageEmployees.getEmployees();
    }

    @Override public void deleteEmployee(Employee employee)
        throws RemoteException, SQLException
    {
        manageEmployees.deleteEmployee(employee);
    }
}
