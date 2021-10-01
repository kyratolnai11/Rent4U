package Client.Views.EditVehicleInfoView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Util.Vehicle;
import Client.ViewModel.EditVehicleInfoViewModel;
import Client.Views.ViewController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;
import java.sql.SQLException;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class EditVehicleInfoViewController implements ViewController
{
  @FXML private TextField licensePlateField;
  @FXML private TextField enginePowerField;
  @FXML private TextField makeField;
  @FXML private TextField modelField;
  @FXML private TextField yearField;
  @FXML private TextField nbOfSeatsField;
  @FXML private TextField priceField;

  @FXML private RadioButton automaticGearBoxButton;
  @FXML private RadioButton manualGearBoxButton;

  @FXML private RadioButton petrolTypeButton;
  @FXML private RadioButton dieselTypeButton;
  @FXML private RadioButton hybridTypeButton;
  @FXML private RadioButton electricTypeButton;

  @FXML private ComboBox<String> types;

  private ViewHandler viewHandler;
  private Vehicle vehicle;
  private EditVehicleInfoViewModel editVehicleInfoViewModel;
  private boolean manager;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory) throws SQLException, RemoteException
  {
    this.viewHandler=viewHandler;
    editVehicleInfoViewModel =viewModelFactory.getEditVehicleInfoViewModel();
    types.getItems().addAll("Car", "Minibus", "Van", "Motorcycle");
  }

  public void setManager(boolean manager)
  {
    this.manager = manager;
  }

  private String getFuelType(){
    if (petrolTypeButton.isSelected()){
      return  "petrol";
    }
    if (dieselTypeButton.isSelected()){
      return "diesel";
    }
    if (hybridTypeButton.isSelected()){
      return "hybrid";
    }

    if (electricTypeButton.isSelected()){
      return "electric";
    }

    else return "Not selected";
  }

  private String getGearBoxType(){
    if (automaticGearBoxButton.isSelected()){
      return "automatic";
    }
    if (manualGearBoxButton.isSelected()){
      return "manual";
    }
    else return "Not selected";
  }

  public void setVehicle(Vehicle vehicle)
  {
    this.vehicle=vehicle;
    makeField.setText(vehicle.getMake());
    modelField.setText(vehicle.getModel());
    yearField.setText(String.valueOf(vehicle.getYear()));
    nbOfSeatsField.setText(String.valueOf(vehicle.getNumberOfSeats()));
    priceField.setText(String.valueOf(vehicle.getPrice()));
    licensePlateField.setText(vehicle.getLicensePlate());
    enginePowerField.setText(String.valueOf(vehicle.getEnginesPower()));

    if(vehicle.getType().equalsIgnoreCase("Car"))
      types.getSelectionModel().select("Car");
    else if(vehicle.getType().equalsIgnoreCase("Minibus"))
      types.getSelectionModel().select("Minibus");
    else if(vehicle.getType().equalsIgnoreCase("Motorcycle"))
      types.getSelectionModel().select("Motorcycle");
    else if(vehicle.getType().equalsIgnoreCase("Van"))
      types.getSelectionModel().select("Van");

    if (vehicle.getTypeOfGearbox().equalsIgnoreCase("automatic"))
      automaticGearBoxButton.fire();
    else
      manualGearBoxButton.fire();

    if (vehicle.getTypeOfFuel().equalsIgnoreCase("petrol"))
      petrolTypeButton.fire();
    else if (vehicle.getTypeOfFuel().equalsIgnoreCase("diesel"))
      dieselTypeButton.fire();
    else if (vehicle.getTypeOfFuel().equalsIgnoreCase("hybrid"))
      hybridTypeButton.fire();
    else if (vehicle.getTypeOfFuel().equalsIgnoreCase("electric"))
      electricTypeButton.fire();
  }
  public String getLicensePlate()
  {
    String licenseP=licensePlateField.getText();
    if(licenseP.length()!=7)
      return null;
    return licenseP;
  }

  public int getEnginePower()
  {
    int enginePow=0;
    String enginePower=enginePowerField.getText();
    try{
      enginePow=parseInt(enginePower);
    }
    catch(NumberFormatException e){
      return 0;
    }
    return enginePow;
  }

  public int getYear()
  {
    int year=0;
    String yearString=yearField.getText();
    try{
      year=parseInt(yearString);
    }
    catch(NumberFormatException e){
      return 0;
    }
    return year;
  }

  public int getNumberOfSeats()
  {
    String numberOfSeatsString=nbOfSeatsField.getText();
    int numberOfSeats=0;
    try{
      numberOfSeats=parseInt(numberOfSeatsString);
    }
    catch (NumberFormatException e)
    {
      return 0;
    }
    return numberOfSeats;
  }

  public double getPrice()
  {
    String priceString=priceField.getText();
    double price=0;
    try{
      price=parseDouble(priceString);
    }
    catch (NumberFormatException e)
    {
      return 0;
    }
    return price;
  }

  public void onUpdateVehicleButton() throws SQLException, RemoteException {
    Platform.runLater(()->{
      try{
        if(getLicensePlate()!=null && getEnginePower()!=0 && getYear()!=0 && getNumberOfSeats()!=0 && getPrice()!=0)
        {
          editVehicleInfoViewModel.editVehicleInfo(
            vehicle,
            getLicensePlate(),
            getEnginePower(),
            types.getSelectionModel().getSelectedItem(),
            makeField.getText(),
            modelField.getText(),
            getYear(),
            getGearBoxType(),
            getFuelType(),
            getNumberOfSeats(),
            getPrice());
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Car information edited");
          alert.setContentText(
              "The car information has been successfully edited!\nThank you!");
          alert.showAndWait();
          viewHandler.openListOfVehicleView(manager);}

          if(getPrice()==0)
          {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid input");
            alert.setContentText("Please enter a valid price!\nTry again!");
            alert.showAndWait();
          }
          if(getNumberOfSeats()==0)
          {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid input");
            alert.setContentText("Please enter a valid number of seats!\nTry again!");
            alert.showAndWait();
          }
          if(getYear()==0)
          {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid input");
            alert.setContentText("Please enter a valid year!\nTry again!");
            alert.showAndWait();
          }
          if(getLicensePlate()==null)
          {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid input");
            alert.setContentText("Please enter a valid year!\nTry again!");
            alert.showAndWait();
          }
          if(getEnginePower()==0)
          {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid input");
            alert.setContentText("Please enter a valid year!\nTry again!");
            alert.showAndWait();
        }
      }
      catch (NumberFormatException | RemoteException | SQLException e)
      {
        e.printStackTrace();
      }

    });
  }

  public void checkRadioButton(RadioButton radioButton){
    if (radioButton.isSelected()){
      radioButton.setSelected(false);
    }
  }

  public void selectAutomatic(){
    checkRadioButton(manualGearBoxButton);
  }

  public void selectManual(){
    checkRadioButton(automaticGearBoxButton);
  }

  public void selectPetrol(){
    checkRadioButton(dieselTypeButton);
    checkRadioButton(hybridTypeButton);
    checkRadioButton(electricTypeButton);
  }

  public void selectDiesel(){
    checkRadioButton(petrolTypeButton);
    checkRadioButton(hybridTypeButton);
    checkRadioButton(electricTypeButton);
  }

  public void selectHybrid(){
    checkRadioButton(petrolTypeButton);
    checkRadioButton(dieselTypeButton);
    checkRadioButton(electricTypeButton);
  }

  public void selectElectric(){
    checkRadioButton(petrolTypeButton);
    checkRadioButton(dieselTypeButton);
    checkRadioButton(hybridTypeButton);
  }

  public void onExitButton(){
    viewHandler.openMainMenu(manager);
  }

}
