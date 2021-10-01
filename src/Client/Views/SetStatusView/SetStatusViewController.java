package Client.Views.SetStatusView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Util.Status;
import Util.Vehicle;
import Client.ViewModel.SetStatusViewModel;
import Client.Views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.GregorianCalendar;

public class SetStatusViewController implements ViewController
{

  private ViewHandler viewHandler;
  private SetStatusViewModel setStatusViewModel;
  private boolean manager;

  @FXML Button setStatusButton;
  @FXML Button goToMenuButton;
  @FXML TextField statusTextField;
  private Vehicle vehicle;
  @FXML Label makeAndModelLabel;
  @FXML Label licensePlateLabel;
  @FXML DatePicker startDatePicker;
  @FXML DatePicker endDatePicker;
  @FXML TextField startHour;
  @FXML TextField startMinute;
  @FXML TextField endHour;
  @FXML TextField endMinute;



  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    setStatusViewModel = viewModelFactory.getSetStatusViewModel();
  }


  public void setVehicle(Vehicle vehicle)
  {
    this.vehicle = vehicle;
    makeAndModelLabel.setText(vehicle.getMake()+" + "+vehicle.getModel());
    licensePlateLabel.setText(vehicle.getLicensePlate());
  }

  public void setManager(boolean manager)
  {
    this.manager = manager;
  }

  public void onMenuButton(ActionEvent evt)
  {
    viewHandler.openMainMenu(manager);
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
  public String getStatus()
  {
    String status=statusTextField.getText();
    if(status.length()>20)
      return null;
    else
      return status;
  }

  public void setStatusButton(ActionEvent evt)
      throws RemoteException, SQLException
  {
    GregorianCalendar now = new GregorianCalendar();
    boolean setter = true;

    if (getStartDate().before(now) || getEndDate().before(getStartDate()) || getEndDate().before(now) || getEndDate()==null || getStartDate()==null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid Input");
      alert.setContentText("Please enter a valid time\nTry again!");
      alert.showAndWait();
      setter = false;
    }
    if(getStatus()==null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid Input");
      alert.setContentText("Please enter a valid status!\nTry again!");
      alert.showAndWait();
      setter = false;
    }
    if(getEndDate()!=null && getStartDate()!=null && getStatus()!=null && setter)
    {
      Status status = new Status(getStartDate(), getEndDate(), getStatus());
      setStatusViewModel.setStatus(vehicle, status);
      viewHandler.openListOfVehicleView(manager);

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Status created");
      alert.setContentText("The status has been created!\nThank you!");
      alert.showAndWait();
    }

  }


}
