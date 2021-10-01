package Server.DatabaseAccess;

import Util.Customer;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ManageCustomers
{
    /**
     * This method id for adding a Customer in the system. The method can be accessed in the ServerModelManager class.
     * @param customer
     * @throws RemoteException
     * @throws SQLException
     */
    void addCustomer(Customer customer) throws RemoteException, SQLException;
    /**
     * This method is for getting all the Customers in the system. The method can be accessed in the ServerModelManager class.
     * @return Arraylist of Customer
     * @throws RemoteException
     * @throws SQLException
     */
    ArrayList<Customer> viewAllCustomers() throws RemoteException, SQLException;
    /**
     * This method is for editing a Customer's information in the system. The method can be accessed in the ServerModelManager class.
     * @param customer
     * @param newCustomer
     * @throws RemoteException
     * @throws SQLException
     */
    void editCustomerInfo(Customer customer,Customer newCustomer) throws RemoteException, SQLException;
    /**
     * This method is for deleting a Customer from the System. The method can be accessed in the ServerModelManager class.
     * @param customer
     * @throws RemoteException
     * @throws SQLException
     */
    void deleteCustomer(Customer customer) throws RemoteException, SQLException;
    /**
     * This method is for creating an account in the system from the Customer's point of view.
     *  The method can be accessed in the ServerModelManager class.
     * @param customer
     * @throws RemoteException
     * @throws SQLException
     */
    void createPersonalAccount(Customer customer) throws RemoteException, SQLException;
    /**
     * This method is for checking wether the user inputted the correct password into the system.
     *  The method can be accessed in the ServerModelManager class.
     * @param emailAddress
     * @param password
     * @return
     * @throws RemoteException
     * @throws SQLException
     */
    Customer checkForPassword(String emailAddress, String password) throws RemoteException, SQLException;
    /**
     * This method id for editing a user's information in the System from a Customer's point of view.
     *  The method can be accessed in the ServerModelManager class.
     * @param customer
     * @param newCustomer
     * @throws RemoteException
     * @throws SQLException
     */
    void editPersonalInfo(Customer customer,Customer newCustomer)throws RemoteException, SQLException;
}
