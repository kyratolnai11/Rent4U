package Client.Views.EditPersonalBookingView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Util.Booking;
import Util.Customer;
import Util.Vehicle;
import Client.ViewModel.EditPersonalBookingViewModel;
import Client.Views.EditPersonalBookingView.VehicleViewCell.VehicleListViewCell;
import Client.Views.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EditPersonalBookingViewController implements ViewController
{
  @FXML DatePicker startDatePicker;
  @FXML DatePicker endDatePicker;
  @FXML TextField startHour;
  @FXML TextField startMinute;
  @FXML TextField endHour;
  @FXML TextField endMinute;
  @FXML ListView listView;
  @FXML Label totalPriceOfBooking;
  @FXML ComboBox<String> type;
  private ViewHandler viewHandler;
  private Customer customer;
  private EditPersonalBookingViewModel editPersonalBookingViewModel;
  private Booking booking;
  public final ObservableList<Vehicle> vehiclesObservableList = FXCollections.observableArrayList();
  private Vehicle vehicle;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory) throws SQLException, RemoteException
  {
    this.viewHandler=viewHandler;
    editPersonalBookingViewModel=viewModelFactory.getEditPersonalBookingViewModel();
    getVehicleData(editPersonalBookingViewModel.getVehicles());
    listView.setItems(vehiclesObservableList);
    listView.setCellFactory(vehiclesObservableList-> new VehicleListViewCell(this));
    listView.setFixedCellSize(125);
    type.getItems().addAll("Car", "Minibus", "Bus", "Motorcycle");
    listView.setFocusTraversable(false);
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
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setContentText("Please enter a valid time\nPlease try again!");
      alert.showAndWait();
      setter=false;
    }
    if(startHour1>23 || startHour1<0)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setContentText("Please enter a valid time\nPlease try again!");
      alert.showAndWait();
      setter=false;

    }
    if(startMinute1>59 ||startMinute1<0)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
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

  public Vehicle getVehicle()
  {
    return vehicle;
  }

  public void setTotalPriceOfBooking(){
    totalPriceOfBooking.setText(String.valueOf(getTotalPrice()));
  }

  public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();
  }

  public void loadData(){
    startDatePicker.setValue(convertToLocalDateViaInstant(booking.getStartTime().getTime()));
    endDatePicker.setValue(convertToLocalDateViaInstant(booking.getEndTime().getTime()));
    startHour.setText(String.valueOf(booking.getStartTime().get(Calendar.HOUR_OF_DAY)));
    startMinute.setText(String.valueOf(booking.getStartTime().get(Calendar.MINUTE)));
    endHour.setText(String.valueOf(booking.getEndTime().get(Calendar.HOUR_OF_DAY)));
    endMinute.setText(String.valueOf(booking.getEndTime().get(Calendar.MINUTE)));
    totalPriceOfBooking.setText(String.valueOf(booking.getPrice()));
  }

  public void setCustomer(Customer customer)
  {
    this.customer=customer;
  }

  public void setBooking(Booking booking)
  {
    this.booking=booking;
    loadData();
  }

  public Booking getBooking()
  {
    return booking;
  }

  public void setVehicle(Vehicle vehicle)
  {
    this.vehicle=vehicle;
  }

  public void onUpdateBooking()
      throws RemoteException, SQLException
  {
    GregorianCalendar now=new GregorianCalendar();
    String idOfCustomer = String.valueOf(booking.getIdOfCustomer());
    String licensePlate = booking.getLicencePlate();
    if (vehicle!=null){
      licensePlate=vehicle.getLicensePlate();
    }
    double price=Double.valueOf(totalPriceOfBooking.getText());

    if(getEndDate()!=null && getStartDate()!=null && now.before(getStartDate()) && getStartDate().before(getEndDate()) && now.before(getEndDate()))
    {
      editPersonalBookingViewModel.editPersonalBooking(
          booking,
          idOfCustomer,
          licensePlate,
          getStartDate(),
          getEndDate(),
          price);

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("The booking is edited");
      alert.setContentText("Your booking has been successfully edited!\nThank you!");
      alert.showAndWait();
    }
    else
    {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Invalid Input");
    alert.setContentText(
        "Please enter a valid information to the fields!\nPlease try again!");
    alert.showAndWait();
    }

  }
  public int daysBetween(Date d1, Date d2) {
    return (int) (d1.toInstant().until(d2.toInstant(), ChronoUnit.DAYS)+1);
  }

  public double getTotalPrice(){
    int daysBetween = daysBetween(getStartDate().getTime(),getEndDate().getTime());

    if (vehicle!=null){
      return vehicle.getPrice()*daysBetween;
    }
    else return 0;
  }


  public void onExitButton() throws SQLException, RemoteException
  {
    viewHandler.openMenuCustomerView(customer);
  }


}
