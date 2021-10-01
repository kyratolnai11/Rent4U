package Client.Views.MenuView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Client.ViewModel.MenuViewModel;
import Client.Views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class MenuViewController implements ViewController
{

  private ViewHandler viewHandler;
  private MenuViewModel menuViewModel;
  private boolean manager;

  @FXML private Button openAddEmployeeButton;
  @FXML private Button listOfEmployeesButton;
  @FXML private Label welcomeLabel;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    this.menuViewModel = viewModelFactory.getMenuViewModel();
  }

  public void onAddVehicleButton(ActionEvent evt)
  {
    viewHandler.openAddVehicle(manager);
  }

  public void onListOfVehicleButton(ActionEvent evt) throws SQLException, RemoteException {
    viewHandler.openListOfVehicleView(manager);
  }
  public void onListOfBookingButton(ActionEvent evt) throws SQLException, RemoteException {
    viewHandler.openListOfBookingsView(manager);
  }

  public void onLogOutButton(ActionEvent evt) throws SQLException, RemoteException {
    viewHandler.openLogInMenu();
  }

  public void onAddBookingButton(ActionEvent evt) throws SQLException, RemoteException {
    viewHandler.openAddBooking(manager);
  }

  public void onAddNewCustomerButton(ActionEvent evt) throws SQLException, RemoteException {
    viewHandler.openAddCustomer(manager);
  }

  public void onListOfAllCustomersButton(ActionEvent evt ) throws SQLException, RemoteException {
    viewHandler.openListOfCustomers(manager);
  }

  public void onAddNewEmployeeButton(ActionEvent evt) throws SQLException, RemoteException {
    viewHandler.openAddEmployee(manager);
  }

  public void onListOfAllEmployeesButton(ActionEvent event) throws SQLException, RemoteException {
    viewHandler.openListOfEmployees(manager);
  }

  public void setManager(boolean manager)
  {
    this.manager = manager;
    setVisible(manager);
  }

  public void setVisible(boolean arg)
  {
    listOfEmployeesButton.setVisible(arg);
    openAddEmployeeButton.setVisible(arg);
    if(arg)
      welcomeLabel.setText("Welcome Manager!");
  }
}
