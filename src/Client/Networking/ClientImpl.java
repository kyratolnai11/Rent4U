package Client.Networking;

import Client.Model.*;
import Shared.ClientCallBack;
import Shared.RMIServer;
import Util.*;

import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ClientImpl implements Client, ClientCallBack

{

    private RMIServer server;

    public ClientImpl()
    {
        try
        {
            UnicastRemoteObject.exportObject(this, 0);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override public void startClient()
    {
        try
        {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            server = (RMIServer) registry.lookup("Server");
        }
        catch (RemoteException | NotBoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override public void addVehicle(Vehicle vehicle)
        throws SQLException, RemoteException
    {
        server.addVehicle(vehicle);
    }

    @Override public ArrayList<Vehicle> getListOfVehicles() throws SQLException, RemoteException {
       return server.viewAllVehicles();
    }

    @Override public void setStatus(Vehicle vehicle, Status status) throws RemoteException, SQLException {
        server.setStatus(vehicle,status);
    }

    @Override public void createBooking(Booking booking) throws RemoteException, SQLException {
        server.createBooking(booking);
    }

    @Override public void editVehicleInfo(Vehicle vehicle, Vehicle newVehivle)
            throws RemoteException, SQLException {
        server.editVehicleInfo(vehicle,newVehivle);
    }

    @Override public ArrayList<Booking> getListOfBookings()
        throws SQLException, RemoteException
    {
        return server.viewAllBookings();
    }

    @Override public void editBookingInfo(Booking booking, Booking newBooking) throws RemoteException, SQLException {
        server.editBookingInfo(booking,newBooking);
    }

    @Override public void createCustomerAccount(Customer customer) throws RemoteException, SQLException {
        server.createCustomerAccount(customer);
    }

    @Override public ArrayList<Customer> getCustomers()
        throws SQLException, RemoteException
    {
        return server.getCustomers();
    }

    @Override public void editCustomerInfo(Customer customer, Customer newCustomer)
        throws RemoteException, SQLException
    {
        server.editCustomerInfo(customer, newCustomer);
    }

    @Override public void createPersonalAccount(Customer customer) throws RemoteException, SQLException {
        server.createPersonalAccount(customer);
    }

    @Override
    public ArrayList<Vehicle> getFreeVehicles(GregorianCalendar startDate, GregorianCalendar endDate, String type) throws RemoteException, SQLException {
        return server.getFreeVehicles(startDate, endDate, type);
    }

    @Override public Customer checkForPassword(String emailAddress,
                                               String password) throws RemoteException, SQLException
    {
        return server.checkForPassword(emailAddress,password);
    }

    @Override public void editPersonalInfo(Customer customer,
        Customer newCustomer) throws RemoteException, SQLException
    {
        server.editPersonalInfo(customer,newCustomer);
    }

    @Override public ArrayList<Booking> getPersonalBookings(Customer customer) throws RemoteException, SQLException
    {
        return server.getPersonalBookings(customer);
    }

    @Override public void editPersonalBooking(Booking booking,
        Booking newBooking) throws RemoteException, SQLException
    {
        server.editPersonalBooking(booking,newBooking);
    }

    @Override public void createEmployee(Employee employee)
        throws RemoteException, SQLException
    {
        server.createEmployee(employee);
    }

    @Override public void deletePersonalBooking(Booking booking)
        throws RemoteException, SQLException
    {
        server.deletePersonalBooking(booking);
    }

    @Override public void editEmployeeInfo(Employee employee,
        Employee newEmployee) throws RemoteException, SQLException
    {
        server.editEmployeeInfo(employee,newEmployee);
    }

    @Override public ArrayList<Employee> getEmployees()
        throws RemoteException, SQLException
    {
        return server.getEmployees();
    }

    @Override public void deleteEmployee(Employee employee)
        throws RemoteException, SQLException
    {
        server.deleteEmployee(employee);
    }

    @Override public void deleteVehicle(Vehicle vehicle) throws RemoteException, SQLException
    {
        server.deleteVehicle(vehicle);
    }

    @Override public void deleteBooking(Booking booking)
        throws RemoteException, SQLException
    {
        server.deleteBooking(booking);
    }

    @Override public void deleteCustomer(Customer customer)
        throws RemoteException, SQLException
    {
        server.deleteCustomer(customer);
    }
}
