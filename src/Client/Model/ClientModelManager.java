package Client.Model;

import Client.Networking.Client;
import Util.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ClientModelManager implements ClientModel
{

    private Client client;

    public ClientModelManager(Client client)
    {
        this.client = client;
        client.startClient();
    }

    @Override public void addVehicle(Vehicle vehicle) throws SQLException, RemoteException {
        client.addVehicle(vehicle);
    }

    @Override public ArrayList<Vehicle> getVehicles() throws SQLException, RemoteException {
        return client.getListOfVehicles();
    }

    @Override public void setStatus(Vehicle vehicle, Status status) throws RemoteException, SQLException {
        client.setStatus(vehicle,status);
    }

    @Override public void createBooking(Booking booking) throws RemoteException, SQLException {
        client.createBooking(booking);
    }

    @Override public void editVehicleInfo(Vehicle vehicle, Vehicle newVehicle)
            throws RemoteException, SQLException {
        client.editVehicleInfo(vehicle, newVehicle);
    }

    @Override public ArrayList<Booking> getBookings()
            throws RemoteException, SQLException
    {
        return client.getListOfBookings();
    }

    @Override public void editBookingInfo(Booking booking, Booking newBooking) throws RemoteException, SQLException {
         client.editBookingInfo(booking,newBooking);
    }

    @Override public void createCustomerAccount(Customer customer) throws RemoteException, SQLException {
        client.createCustomerAccount(customer);
    }

    @Override public ArrayList<Customer> getCustomers() throws RemoteException, SQLException
    {
        return client.getCustomers();
    }

    @Override
    public ArrayList<Vehicle> getFreeVehicles(GregorianCalendar startDate, GregorianCalendar endDate, String type) throws RemoteException, SQLException
    {
        return client.getFreeVehicles(startDate, endDate, type);
    }
    @Override public void editCustomerInfo(Customer customer, Customer newCustomer)
        throws RemoteException, SQLException
    {
        client.editCustomerInfo(customer, newCustomer);

    }

    @Override public void deleteVehicle(Vehicle vehicle)
        throws RemoteException, SQLException
    {
        client.deleteVehicle(vehicle);
    }

    @Override public void deleteBooking(Booking booking)
        throws RemoteException, SQLException
    {
        client.deleteBooking(booking);
    }

    @Override public void deleteCustomer(Customer customer)
        throws RemoteException, SQLException
    {
        client.deleteCustomer(customer);
    }

    @Override
    public void createPersonalAccount(Customer customer)
        throws RemoteException, SQLException
    {
        client.createPersonalAccount(customer);
    }

    @Override public Customer checkForPassword(String emailAddress,
        String password) throws RemoteException, SQLException
    {
        return client.checkForPassword(emailAddress,password);
    }

    @Override public void editPersonalInfo(Customer customer,
        Customer newCustomer) throws RemoteException, SQLException
    {
        client.editPersonalInfo(customer,newCustomer);
    }

    @Override public ArrayList<Booking> getPersonalBookings(Customer customer)
        throws RemoteException, SQLException
    {
        return client.getPersonalBookings(customer);
    }

    @Override public void editPersonalBooking(Booking booking,
        Booking newBooking) throws RemoteException, SQLException
    {
        client.editPersonalBooking(booking,newBooking);
    }

    @Override public void createEmployee(Employee employee)
        throws RemoteException, SQLException
    {
        client.createEmployee(employee);
    }

    @Override public void deletePersonalBooking(Booking booking)
        throws RemoteException, SQLException
    {
        client.deletePersonalBooking(booking);
    }

    @Override public void editEmployeeInfo(Employee employee, Employee newEmployee)
        throws RemoteException, SQLException
    {
        client.editEmployeeInfo(employee,newEmployee);
    }

    @Override public ArrayList<Employee> getEmployees()
        throws RemoteException, SQLException
    {
        return client.getEmployees();
    }

    @Override public void deleteEmployee(Employee employee)
        throws RemoteException, SQLException
    {
        client.deleteEmployee(employee);
    }

}
