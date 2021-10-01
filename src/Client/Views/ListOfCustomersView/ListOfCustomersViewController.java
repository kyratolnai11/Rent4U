package Client.Views.ListOfCustomersView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Util.Customer;
import Client.ViewModel.ListOfCustomersViewModel;
import Client.Views.ListOfCustomersView.CustomerCellView.CustomerListViewCell;
import Client.Views.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ListOfCustomersViewController implements ViewController
{
  private ViewHandler viewHandler;
  private ListOfCustomersViewModel listOfCustomersViewModel;

  @FXML ListView<Customer> listView;

  private boolean manager;

  public final ObservableList<Customer> customersObservableList = FXCollections.observableArrayList();

  /**
   * This method is from the ViewController interface, which class implements.
   * Whe set both of the parameters and access the data which shows all the customers.
   * In the list we have specific cells that show the data. These cells size is fixed to 40.
   * @param viewHandler
   * @param viewModelFactory
   * @throws SQLException
   * @throws RemoteException
   */
  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory) throws SQLException, RemoteException
  {
    this.viewHandler=viewHandler;
    this.listOfCustomersViewModel = viewModelFactory.getListOfCustomersViewModel();
    getCustomersData(listOfCustomersViewModel.getCustomers());
    listView.setItems(customersObservableList);
    listView.setCellFactory(customerListView -> new CustomerListViewCell(this));
    listView.setFixedCellSize(40);
    listView.setFocusTraversable(false);
  }

  public void setManager(boolean manager)
  {
    this.manager = manager;
  }

  public ObservableList<Customer> getCustomersData(
      ArrayList<Customer> customersArrayList)
  {
    for (int x = 0; x<customersArrayList.size(); x++){
      customersObservableList.add(customersArrayList.get(x));
    }
    return customersObservableList;
  }

  public void onAddCustomerButton() throws SQLException, RemoteException {
    viewHandler.openAddCustomer(manager);
  }

  public void onMenuButton(){
    viewHandler.openMainMenu(manager);
  }

  public void editCustomer(Customer customer) throws SQLException, RemoteException
  {
    viewHandler.openEditCustomerInfo(customer, manager);
  }

  public void deleteCustomer(Customer customer) throws RemoteException, SQLException
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Delete Customer");
    alert.setHeaderText("");
    alert.setContentText("Are you sure you would like to delete this customer?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
    listOfCustomersViewModel.deleteCustomer(customer);
    viewHandler.openListOfCustomers(manager);}
    else if(result.get()==ButtonType.CANCEL){
      viewHandler.openListOfCustomers(manager); }
  }
}
