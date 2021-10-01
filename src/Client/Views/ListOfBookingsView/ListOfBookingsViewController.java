package Client.Views.ListOfBookingsView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Util.Booking;
import Client.ViewModel.ListOfBookingsViewModel;
import Client.Views.ListOfBookingsView.BookingViewCell.BookingListViewCell;
import Client.Views.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Optional;

public class ListOfBookingsViewController implements ViewController
{
  private ViewHandler viewHandler;
  private ListOfBookingsViewModel listOfBookingsViewModel;
  private boolean manager;

  @FXML ListView<Booking> listView;

  public final ObservableList<Booking> bookingsObservableList = FXCollections.observableArrayList();


  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory) throws SQLException, RemoteException
  {
    this.viewHandler = viewHandler;
    this.listOfBookingsViewModel = viewModelFactory.getListOfBookingsViewModel();
    getBookingData(listOfBookingsViewModel.getBookings());

    listView.setItems(bookingsObservableList);
    listView.setCellFactory(vehicleListView -> new BookingListViewCell(this));

    listView.setFixedCellSize(55);
    listView.setFocusTraversable(false);
  }

  public void onAddBookingButton(ActionEvent evt)
      throws SQLException, RemoteException
  {
    viewHandler.openAddBooking(manager);
  }

  public void onMenuButton(ActionEvent evt)
  {
    viewHandler.openMainMenu(manager);
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
    viewHandler.openEditBooking(booking,manager);
  }

  public void onDelete(Booking booking) throws RemoteException, SQLException
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Delete Booking");
    alert.setHeaderText("");
    alert.setContentText("Are you sure you would like to delete the booking?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
      listOfBookingsViewModel.deleteBooking(booking);
      viewHandler.openListOfBookingsView(manager);
    } else if(result.get()==ButtonType.CANCEL){
      viewHandler.openListOfBookingsView(manager);
    }

  }

  public void setManager(boolean manager) {
    this.manager = manager;
  }
}
