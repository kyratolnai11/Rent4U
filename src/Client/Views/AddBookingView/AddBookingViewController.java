package Client.Views.AddBookingView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Util.Vehicle;
import Client.ViewModel.AddBookingViewModel;
import Client.Views.AddBookingView.VehicleViewCell.VehicleListViewCell;
import Client.Views.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddBookingViewController implements ViewController {


  private ViewHandler viewHandler;
  private AddBookingViewModel viewModel;
  @FXML ListView<Vehicle> listView;
  @FXML DatePicker startDatePicker;
  @FXML DatePicker endDatePicker;
  @FXML ComboBox<String> customersID;
  @FXML TextField startHour;
  @FXML TextField startMinute;
  @FXML TextField endHour;
  @FXML TextField endMinute;
  @FXML ComboBox<String> type;
  @FXML Label totalPriceOfBooking;

  public final ObservableList<Vehicle> vehiclesObservableList = FXCollections.observableArrayList();
  private Vehicle chosenVehicle;
  private boolean manager;

  @Override
  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
      throws SQLException, RemoteException
  {
    this.viewHandler = viewHandler;
    viewModel = viewModelFactory.getAddBookingViewModel();
    getVehicleData(viewModel.getVehicles());
    listView.setItems(vehiclesObservableList);
    listView.setCellFactory(vehicleListView -> new VehicleListViewCell(this));
    listView.setFixedCellSize(125);
    listView.setVisible(false);
    customersID.getItems().addAll(viewModel.getAllCustomersID());
    type.getItems().addAll("Car", "Minibus", "Bus", "Motorcycle");
    listView.setFocusTraversable(false);
  }

  public void setManager(boolean manager)
  {
    this.manager = manager;
  }

  public ObservableList<Vehicle> getVehicleData(
      ArrayList<Vehicle> vehiclesArrayList)
  {
    for (int x = 0; x<vehiclesArrayList.size(); x++){
      vehiclesObservableList.add(vehiclesArrayList.get(x));
    }
    return vehiclesObservableList;
  }

  public GregorianCalendar getStartDate(){
    boolean setter=true;
    int startHour1 =0;
    int startMinute1 =0;
    try{
      startHour1 = Integer.parseInt(startHour.getText());
      startMinute1 = Integer.parseInt(startMinute.getText());
    }
    catch (NumberFormatException e)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid Input");
      alert.setContentText("Please enter a valid time\nPlease try again!");
      alert.showAndWait();
      setter=false;
    }
    if(startHour1>23 || startHour1<0)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid Input");
      alert.setContentText("Please enter a valid time\nPlease try again!");
      alert.showAndWait();
      setter=false;

    }
    if(startMinute1>59 ||startMinute1<0)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid Input");
      alert.setContentText("Please enter a valid time\nPlease try again!");
      alert.showAndWait();
      setter=false;
    }
    if(setter)
    {
      LocalDate date1 = startDatePicker.getValue();
      GregorianCalendar startDate = new GregorianCalendar(date1.getYear(),
          date1.getMonth().getValue() - 1, date1.getDayOfMonth(), startHour1,
          startMinute1);
      return startDate;
    }
    return null;
  }

  public GregorianCalendar getEndDate(){
    boolean setter=true;
    int endHour1 = 0;
    int endMinute1=0;

    try{
      endHour1 = Integer.parseInt(endHour.getText());
      endMinute1 = Integer.parseInt(endMinute.getText());
    }
    catch (NumberFormatException e)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid Input");
      alert.setContentText("Please enter a valid license plate number\nPlease try again!");
      alert.showAndWait();
      setter=false;
    }
    if(endHour1>23 || endHour1<0)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid Input");
      alert.setContentText("Please enter a valid time\nPlease try again!");
      alert.showAndWait();
      setter=false;

    }
    if(endMinute1>59 ||endMinute1<0)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid Input");
      alert.setContentText("Please enter a valid time\nPlease try again!");
      alert.showAndWait();
      setter=false;
    }
    if(setter)
    {
      LocalDate date2 = endDatePicker.getValue();
      GregorianCalendar endDate = new GregorianCalendar(date2.getYear(),
          date2.getMonth().getValue() - 1, date2.getDayOfMonth(), endHour1,
          endMinute1);
      return endDate;
    }
    return null;
  }

  public void onCreateBookingButton() throws RemoteException, SQLException
  {
    boolean setter=true;
    String licensePlate = chosenVehicle.getLicensePlate();
    GregorianCalendar now = new GregorianCalendar();

    if (licensePlate.length() > 7)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid Input");
      alert.setContentText(
          "Please enter a valid license plate number\nPlease try again!");
      alert.showAndWait();
      setter=false;
    }
    if (getStartDate().before(now) || getEndDate().before(getStartDate()) || getEndDate().before(now) || getEndDate()==null || getStartDate()==null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid Input");
      alert.setContentText("Please enter a valid end time\nTry again!");
      alert.showAndWait();
      setter=false;
    }
    if(setter)
    {
      viewModel.createBooking(customersID.getSelectionModel().getSelectedItem(),
          chosenVehicle.getLicensePlate(), getStartDate(), getEndDate(),
          getTotalPrice());
      viewHandler.openListOfBookingsView(manager);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Booking created");
      alert.setContentText("The booking has been created!\nThank you!");
      alert.showAndWait();
    }
  }

  public int daysBetween(Date d1, Date d2) {
    return (int) (d1.toInstant().until(d2.toInstant(), ChronoUnit.DAYS)+1);
  }

  public double getTotalPrice(){
    int daysBetween = daysBetween(getStartDate().getTime(),getEndDate().getTime());

    if (chosenVehicle!=null){
      return chosenVehicle.getPrice()*daysBetween;
    }
    else return 0;
  }

  public void setTotalPriceOfBooking(){
    totalPriceOfBooking.setText(String.valueOf(getTotalPrice()));
  }

  public void setVehicle(Vehicle vehicle){
    this.chosenVehicle=vehicle;
  }


  public void onChoseType(){
    if (!startDatePicker.getValue().equals(null) &&
        !endDatePicker.getValue().equals(null) &&
        !startHour.getText().equals(null) &&
        !startMinute.getText().equals(null) &&
        !endHour.getText().equals(null) &&
        !endMinute.getText().equals(null) &&
        !customersID.getSelectionModel().isEmpty()
        ){
      listView.setVisible(true);
    } else listView.setVisible(false);
  }

  public void onMainMenuButton(ActionEvent evt)
  {
    viewHandler.openMainMenu(manager);
  }
}
