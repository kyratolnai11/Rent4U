package Client.Views.MenuForLogInView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Client.ViewModel.MenuForLogInViewModel;
import Client.Views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class MenuForLogInViewController implements ViewController
{
  private ViewHandler viewHandler;
  private MenuForLogInViewModel menuForLogInViewModel;

  @FXML private Button openLoginForEmployee;
  @FXML private Button openLogInForCustomer;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory) throws SQLException, RemoteException
  {
    this.viewHandler=viewHandler;
    this.menuForLogInViewModel=viewModelFactory.getMenuForLogInViewModel();
  }

  public void onLogInForEmployeeButton(ActionEvent evt)
  {
    viewHandler.openLogInEmployee();
  }

  public void onLogInForCustomerButton(ActionEvent evt) throws SQLException, RemoteException
  {
    viewHandler.openLogInCustomer();
  }

}
