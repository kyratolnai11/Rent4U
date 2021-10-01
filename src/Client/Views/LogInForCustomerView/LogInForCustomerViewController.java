package Client.Views.LogInForCustomerView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Util.Customer;
import Client.ViewModel.LogInCustomerViewModel;
import Client.Views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class LogInForCustomerViewController implements ViewController
{
  @FXML TextField emailField;
  @FXML TextField passwordField;
  @FXML Label incorrectPasswordLabel;

  private ViewHandler viewHandler;
  private LogInCustomerViewModel logInCustomerViewModel;

  private Customer customer;

  public void setCustomer(Customer customer)
  {
    this.customer=customer;
  }

  private String[] splitCpr(String cprFull){
    String[] parts = cprFull.split("/");
    return parts;
  }
  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory) throws SQLException, RemoteException
  {
    this.viewHandler=viewHandler;
    this.logInCustomerViewModel = viewModelFactory.getLogInCustomerViewModel();
  }

  public void logIn() throws RemoteException, SQLException
  {
    incorrectPasswordLabel.setVisible(false);
    customer = logInCustomerViewModel.checkForPassword(emailField.getText(), passwordField.getText());
    if (customer.getPassword().equals(passwordField.getText()))
      viewHandler.openMenuCustomerView(customer);//We are missing function to set Customer
    else incorrectPasswordLabel.setVisible(true);
    emailField.clear();
    passwordField.clear();
  }

  public void onBackButton() throws SQLException, RemoteException
  {
    viewHandler.openLogInMenu();
  }

  public void signUp() throws SQLException, RemoteException
  {
    viewHandler.openAddPersonalAccount();
  }
}
