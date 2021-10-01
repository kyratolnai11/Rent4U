package Client.Views.ListOfPersonalBookings;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Util.Booking;
import Util.Customer;
import Client.ViewModel.ListOfPersonalBookingsViewModel;
import Client.Views.ListOfPersonalBookings.PersonalBookingCell.PersonalBookingCellViewController;
import Client.Views.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ListOfPersonalBookingsViewController implements ViewController
{
  private ViewHandler viewHandler;
  private ListOfPersonalBookingsViewModel listOfPersonalBookingsViewModel;
  @FXML ListView<Booking> listView;
  private Customer customer;

  public final ObservableList<Booking> bookingsObservableList = FXCollections.observableArrayList();


  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    this.listOfPersonalBookingsViewModel = viewModelFactory.getListOfPersonalBookingViewModel();
  }

  public void setCustomer(Customer customer) throws SQLException, RemoteException {
    this.customer = customer;
    getBookingData(listOfPersonalBookingsViewModel.getPersonalBookings(customer));
    listView.setItems(bookingsObservableList);
    listView.setCellFactory(vehicleListView -> new PersonalBookingCellViewController(this));

    listView.setFixedCellSize(55);
    listView.setFocusTraversable(false);
  }

  public void onAddBookingButton(ActionEvent evt) throws SQLException, RemoteException
  {
    viewHandler.openAddBookingCustomer(customer);
  }

  public void onMenuButton(javafx.event.ActionEvent evt)
      throws SQLException, RemoteException
  {
    viewHandler.openMenuCustomerView(customer);
  }

  public ObservableList<Booking> getBookingData(
      ArrayList<Booking> bookingsArrayList)
  {
    for (int x = 0; x<bookingsArrayList.size(); x++){
      bookingsObservableList.add(bookingsArrayList.get(x));
    }
    return bookingsObservableList;
  }

  public void onEdit(Booking booking) throws SQLException, RemoteException
  {
    viewHandler.openEditPersonalBooking(customer, booking);
  }

  /**
   * @author Peter Blasko
   * When the "Delete" button is clicked a warning messsage pops up to confirm the action.
   * If the warning is confirmed the booking is deleted from the system and the user is redericted to list of bookings.
   * @param booking
   * @throws RemoteException
   * @throws SQLException
   */
  public void onDelete(Booking booking) throws RemoteException, SQLException
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Delete your booking");
    alert.setHeaderText("");
    alert.setContentText("Are you sure you would like to delete your booking?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
    listOfPersonalBookingsViewModel.deletePersonalBooking(booking);
    viewHandler.openPersonalBookings(customer);}
    else if(result.get()==ButtonType.CANCEL)
      viewHandler.openPersonalBookings(customer);
  }
}
