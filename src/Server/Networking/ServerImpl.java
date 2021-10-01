package Server.Networking;

import Client.Model.*;
import Server.Model.ServerModelManager;
import Shared.ClientCallBack;
import Shared.RMIServer;
import Util.*;

import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class ServerImpl implements RMIServer
{
    private ServerModelManager serverModelManager;
    private Map<ClientCallBack, PropertyChangeListener> listeners = new HashMap<>();

    public ServerImpl(ServerModelManager serverModelManager) throws RemoteException
    {
        this.serverModelManager = serverModelManager;
        UnicastRemoteObject.exportObject(this, 0);
    }

    @Override
    public void startServer() throws RemoteException, AlreadyBoundException
    {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("Server", this);
    }

    @Override
    public void addVehicle(Vehicle vehicle) throws SQLException {
        serverModelManager.addVehicle(vehicle);
    }

    @Override public ArrayList<Vehicle> viewAllVehicles() throws SQLException {
        return serverModelManager.viewAllVehicles();
    }

    @Override public void setStatus(Vehicle vehicle, Status status) throws SQLException {
        serverModelManager.setStatus(vehicle,status);
    }

    @Override public void createBooking(Booking booking) throws SQLException {
        serverModelManager.createBooking(booking);
    }

    @Override public void editVehicleInfo(Vehicle vehicle, Vehicle newVehicle) throws RemoteException, SQLException {
        serverModelManager.editVehicleInfo(vehicle,newVehicle);

    }

    @Override public ArrayList<Booking> viewAllBookings() throws SQLException
    {
        return serverModelManager.viewAllBookings();
    }

    @Override public void editBookingInfo(Booking booking, Booking newBooking) throws RemoteException, SQLException {
        serverModelManager.editBookingInfo(booking, newBooking);
    }

    @Override public void createCustomerAccount(Customer customer) throws RemoteException, SQLException {
        serverModelManager.addCustomer(customer);
    }

    @Override public ArrayList<Customer> getCustomers()
        throws RemoteException, SQLException
    {
        return serverModelManager.viewAllCustomers();
    }

    @Override
    public ArrayList<Vehicle> getFreeVehicles(GregorianCalendar startDate, GregorianCalendar endDate, String type) throws RemoteException, SQLException {
        return serverModelManager.getFreeVehicles(startDate, endDate, type);
    }

    @Override public void editCustomerInfo(Customer customer, Customer newCustomer)
        throws RemoteException, SQLException
    {
        serverModelManager.editCustomerInfo(customer, newCustomer);
    }

    @Override public void deleteVehicle(Vehicle vehicle)
        throws RemoteException, SQLException
    {
        serverModelManager.deleteVehicle(vehicle);
    }

    @Override public void createPersonalAccount(Customer customer) throws RemoteException, SQLException {
        serverModelManager.createPersonalAccount(customer);
    }
    @Override public void deleteBooking(Booking booking)
        throws RemoteException, SQLException
    {
        serverModelManager.deleteBooking(booking);
    }

    @Override public void deleteCustomer(Customer customer)
        throws RemoteException, SQLException
    {
        serverModelManager.deleteCustomer(customer);
    }

    @Override public Customer checkForPassword(String emailAddress,
                                               String password) throws RemoteException, SQLException
    {
        return serverModelManager.checkForPassword(emailAddress,password);
    }

    @Override public void editPersonalInfo(Customer customer,
        Customer newCustomer) throws RemoteException, SQLException
    {
        serverModelManager.editPersonalInfo(customer,newCustomer);
    }

    @Override public void editPersonalBooking(Booking booking,
        Booking newBooking) throws RemoteException, SQLException
    {
        serverModelManager.editPersonalBooking(booking,newBooking);
    }

    @Override public void createEmployee(Employee employee)
        throws RemoteException, SQLException
    {
        serverModelManager.createEmployee(employee);
    }

    @Override public void deletePersonalBooking(Booking booking)
        throws RemoteException, SQLException
    {
        serverModelManager.deletePersonalBooking(booking);
    }

    @Override public void editEmployeeInfo(Employee employee, Employee newEmployee) throws RemoteException, SQLException
    {
        serverModelManager.editEmployeeInfo(employee,newEmployee);
    }

    @Override public ArrayList<Employee> getEmployees()
        throws RemoteException, SQLException
    {
        return serverModelManager.getEmployees();
    }

    @Override public void deleteEmployee(Employee employee)
        throws RemoteException, SQLException
    {
        serverModelManager.deleteEmployee(employee);
    }

    @Override public ArrayList<Booking> getPersonalBookings(Customer customer)
        throws RemoteException, SQLException
    {
        return serverModelManager.getPersonalBookings(customer);
    }
}
