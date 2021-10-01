package Client.ViewModel;

import Client.Model.ClientModel;
import Util.Customer;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AddPersonalAccountViewModel
{
  private ClientModel userModel;

  public AddPersonalAccountViewModel(ClientModel userModel)
  {
    this.userModel=userModel;
  }

  public Customer createPersonalAccount(String firstName,String lastName, GregorianCalendar dateOfBirth, String email,String password,String phoneNumber,String
      drivingLicenseNumber,String cpr_number) throws RemoteException,
      SQLException
  {
    Customer customer = new Customer(firstName, lastName, dateOfBirth, email, password, phoneNumber, drivingLicenseNumber, cpr_number);
    userModel.createPersonalAccount(customer);
    return customer;
  }

  public ArrayList<Customer> getCustomers() throws RemoteException, SQLException
  {
    return userModel.getCustomers();
  }
}
