package Client.Views.AddVehicleView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Client.ViewModel.AddVehicleViewModel;
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

public class AddVehicleViewController implements ViewController
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
    private AddVehicleViewModel addVehicleViewModel;
    private boolean manager;

    @Override public void init(ViewHandler vh, ViewModelFactory vmf)
    {
        this.viewHandler = vh;
        this.addVehicleViewModel = vmf.getAddVehicleViewModel();

        types.getItems().addAll("Car", "Minibus", "Van", "Motorcycle");

    }

    public void checkRadioButton(RadioButton radioButton){
        if (radioButton.isSelected()){
            radioButton.setSelected(false);
        }
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

    /**
     * @author Maria Bianca Militaru
     * When the "Add" button is clicked the this method.
     * Within the lamdba expression we try to saves all the information from the TextFileds, RadioButtons, and catch the exceptions.
     * Alerts were implemented to insure a user friendly interface.
     */
    public void onAddButton(){
        Platform.runLater(() -> {
            try {
                if(getLicensePlate()!=null && getEnginePower()!=0 && getYear()!=0 && getNumberOfSeats()!=0 && getPrice()!=0)
                {
                addVehicleViewModel.addVehicle(
                    getLicensePlate(),
                    getEnginePower(),
                    types.getSelectionModel().getSelectedItem(),
                    makeField.getText(),
                    modelField.getText(),
                    getYear(),
                    getGearBoxType(),
                    getFuelType(),
                    getNumberOfSeats(),
                    getPrice()
                    );
                viewHandler.openListOfVehicleView(manager);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("The vehicle was added");
                alert.setContentText("The vehicle was created in the system!\nThank you!");
                alert.showAndWait();
                }

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
                    alert.setContentText("Please enter a valid license!\nTry again!");
                    alert.showAndWait();
                }
                if(getEnginePower()==0)
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Invalid input");
                    alert.setContentText("Please enter a valid engine power!\nTry again!");
                    alert.showAndWait();
                }

            }
            catch (SQLException | RemoteException throwables)
            {
                throwables.printStackTrace();
            }
        });
  }

  public void onExitButton(){
    viewHandler.openMainMenu(manager);
  }

  public void setManager(boolean manager) {
        this.manager = manager;
    }
}