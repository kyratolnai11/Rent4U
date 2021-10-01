package Client.Views.MenuCustomerView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Util.Customer;
import Client.ViewModel.MenuCustomerViewModel;
import Client.Views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class MenuCustomerViewController implements ViewController
{
  private ViewHandler viewHandler;
  private MenuCustomerViewModel menuCustomerViewModel;
  @FXML Label nameLabel;

  private Customer customer;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory) throws SQLException, RemoteException
  {
    this.viewHandler=viewHandler;
    this.menuCustomerViewModel=viewModelFactory.getMenuCustomerViewModel();
  }

  public void setCustomer(Customer customer)
  {
    this.customer = customer;
    nameLabel.setText(customer.getFirstName() + " " + customer.getLastName());
  }

  public void onCreateBooking() throws SQLException, RemoteException {
    viewHandler.openAddBookingCustomer(customer);
  }

  public void onMyBooking() throws SQLException, RemoteException
  {
    System.out.println(customer);
    viewHandler.openPersonalBookings(customer);
  }

  public void onMyAccount() throws SQLException, RemoteException {
    viewHandler.openEditPersonalInfo(customer);
  }

  public void onLogOutButton() throws SQLException, RemoteException {
    viewHandler.openLogInMenu();
  }
}
