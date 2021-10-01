package Client.Views.EditPersonalInfoView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Util.Customer;
import Client.ViewModel.EditPersonalInfoViewModel;
import Client.Views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

public class EditPersonalInfoViewController implements ViewController {
  private ViewHandler viewHandler;
  private EditPersonalInfoViewModel editPersonalInfoViewModel;

  private Customer customer;

  @FXML TextField firstNameField;
  @FXML TextField lastNameField;
  @FXML DatePicker dateOfBirthPicker;
  @FXML TextField eMailField;
  @FXML TextField drivingLicenseField;
  @FXML TextField cprFirstField;
  @FXML TextField cprSecondField;
  @FXML TextField passwordField;
  @FXML TextField rePasswordField;
  @FXML TextField phoneField;
  @FXML Label passwordCheckLabel;


  @Override
  public void init(ViewHandler viewHandler,
                   ViewModelFactory viewModelFactory) throws SQLException, RemoteException {
    this.viewHandler = viewHandler;
    this.editPersonalInfoViewModel = viewModelFactory.getEditPersonalInfoViewModelViewModel();

  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
    loadData();
  }

  public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
  }

  /**
   * @author Milan Tolnai
   * When the user open up this page we load all the existing data into the TextFields, DatePicker.
   * This method makes it easier to see what kind of data needs or doesn't need to be changed.
   */
  public void loadData() {
    firstNameField.setText(customer.getFirstName());
    lastNameField.setText(customer.getLastName());
    dateOfBirthPicker.setValue(convertToLocalDateViaInstant(customer.getDateOfBirth().getTime()));
    eMailField.setText(customer.getEmail());
    drivingLicenseField.setText(customer.getDrivingLicenseNumber());
    cprFirstField.setText(customer.getCpr_number().substring(0, 6));
    cprSecondField.setText(customer.getCpr_number().substring(6, 10));
    passwordField.setText(customer.getPassword());
    rePasswordField.setText(customer.getPassword());
    phoneField.setText(customer.getPhoneNumber());
    passwordCheckLabel.setVisible(false);
  }

  /**
   * In out GUI we have two TextFields to enter a CPR number. In order to store this data we needed to convert the Integers into String
   * than combine them into one String that we return.
   * Alerts have been implemented in order to insure that the data that is stored in the system is correct.
   * @author Radovan Santa & Kyra Tolnai
   * @return Returns the full CPR number of the user.
   */
  private String getCpr() throws RemoteException, SQLException
  {
    boolean setter=true;
    int firstPart=0;
    int secondPart=0;
    try{
      firstPart=Integer.parseInt(cprFirstField.getText());
      secondPart=Integer.parseInt(cprSecondField.getText());
    }
    catch (NumberFormatException e)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid Input");
      alert.setContentText(
              "Please enter a valid cpr!\nPlease try again!");
      alert.showAndWait();
      setter = false;
    }
    if(cprFirstField.getText().length()!=6 && cprSecondField.getText().length()!=4)
      setter=false;

    String cpr=cprFirstField.getText()+cprSecondField.getText();
    for(int i=0;i<editPersonalInfoViewModel.getCustomers().size();i++)
    {
      if (!editPersonalInfoViewModel.getCustomers().get(i).equals(customer))
        if (cpr.equals(editPersonalInfoViewModel.getCustomers().get(i).getCpr_number()))
          setter = false;
    }
    if(setter)
      return cprFirstField.getText()+cprSecondField.getText();
    return null;
  }

  private String getEmail() throws RemoteException, SQLException
  {
    String email=eMailField.getText();
    for(int i=0;i<editPersonalInfoViewModel.getCustomers().size();i++)
    {
      if (!editPersonalInfoViewModel.getCustomers().get(i).equals(customer))
        if(editPersonalInfoViewModel.getCustomers().get(i).getEmail().equals(email))
          return null;
    }
    return email;
  }

  public GregorianCalendar getDateBirth(){
    GregorianCalendar now=new GregorianCalendar();
    LocalDate date = dateOfBirthPicker.getValue();
    GregorianCalendar dateOfBirth = new GregorianCalendar(date.getYear(), date.getMonth().getValue(), date.getDayOfMonth());
    if (now.before(dateOfBirth))
      return null;
    return dateOfBirth;
  }

  public String getPhoneNumber() {
    String phoneNumberString = phoneField.getText();
    if (phoneNumberString.length() > 12 || phoneNumberString.length() < 6)
      return null;

    int phoneNumber = 0;
    try {
      phoneNumber = Integer.parseInt(phoneField.getText());
    } catch (NumberFormatException e) {
      return null;
    }
    return phoneNumberString;
  }

  /**
   * The main purpose of this method is to send all of the new information from this controller to the ViewModel
   * where we set the data input from the TextFields, DatePicker.
   * In order to avoid problems, error messages have been implemented that alerts the user if he/she wrote icorrect data into the TextFields.
   * An alert will confirm the user that the changes have been made in the system.
   * @author Kyra Tolnai & Milan Tolnai
   * @throws RemoteException
   * @throws SQLException
   */
  public void onUpdatePersonalAccount() throws RemoteException, SQLException
  {
    if(getCpr()!=null && getDateBirth()!=null && getPhoneNumber()!=null && getEmail()!=null)
    {
      editPersonalInfoViewModel.editPersonalInfo(
        customer,
        firstNameField.getText(),
        lastNameField.getText(),
        getDateBirth(),
        getEmail(),
        passwordField.getText(),
        getPhoneNumber(),
        drivingLicenseField.getText(),
        getCpr());
      viewHandler.openMenuCustomerView(customer);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Your information was edited");
      alert.setContentText(
              "Your personal information has been successfully edited!\nThank you!");
      alert.showAndWait();
    }
    else {
      if (getCpr() == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid input");
        alert.setContentText("Please enter a valid cpr number!");
        alert.showAndWait();
      }

      if (getDateBirth() == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid input");
        alert.setContentText("Please enter a valid birthday!");
        alert.showAndWait();
      }
      if (getPhoneNumber() == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid input");
        alert.setContentText("Please enter a phone number!");
        alert.showAndWait();
      }
      if (getEmail() == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid input");
        alert.setContentText("Please enter a unique email address!");
        alert.showAndWait();
      }
    }
  }

  public void onMenu(ActionEvent actionEvent) throws SQLException, RemoteException {
    viewHandler.openMenuCustomerView(customer);
  }

  public void onDeletePersonalAccount(ActionEvent actionEvent) throws RemoteException, SQLException {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Delete Account");
    alert.setHeaderText("");
    alert.setContentText("Are you sure you would like to delete this account?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
      editPersonalInfoViewModel.deleteCustomerAccount(customer);
      viewHandler.openLogInMenu();
    } else if (result.get() == ButtonType.CANCEL) {
      viewHandler.openMenuCustomerView(customer);
    }
  }
}


