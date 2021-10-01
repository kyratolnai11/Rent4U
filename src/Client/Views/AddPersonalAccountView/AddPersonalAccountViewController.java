package Client.Views.AddPersonalAccountView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Util.Customer;
import Client.ViewModel.AddPersonalAccountViewModel;
import Client.Views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.GregorianCalendar;

public class AddPersonalAccountViewController implements ViewController
{
  private ViewHandler viewHandler;
  private AddPersonalAccountViewModel addPersonalAccountViewModel;

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

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory) throws SQLException, RemoteException
  {
    this.viewHandler=viewHandler;
    this.addPersonalAccountViewModel = viewModelFactory.getAddPersonalAccountViewModelViewModel();
    passwordCheckLabel.setVisible(false);
  }

  private boolean checkPassword(){
    if (passwordField.getText().equals(rePasswordField.getText())){
      passwordCheckLabel.setVisible(false);
      return true;
    } else {
      passwordCheckLabel.setVisible(true);
      return false;
    }
  }

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
      alert.setTitle("Invalid input");
      alert.setContentText(
          "Please enter a valid cpr!\nPlease try again!");
      alert.showAndWait();
      setter=false;
    }
    if(cprFirstField.getText().length()!=6 && cprSecondField.getText().length()!=4)
      setter=false;
    String cpr=cprFirstField.getText()+cprSecondField.getText();
    for(int i=0;i<addPersonalAccountViewModel.getCustomers().size();i++)
    {
      if(cpr.equals(addPersonalAccountViewModel.getCustomers().get(i).getCpr_number()))
        setter=false;
    }
    if(setter)
      return cprFirstField.getText()+cprSecondField.getText();
    return null;
  }

  public GregorianCalendar getDateBirth(){
    GregorianCalendar now=new GregorianCalendar();
    LocalDate date = dateOfBirthPicker.getValue();
    GregorianCalendar dateOfBirth = new GregorianCalendar(date.getYear(), date.getMonth().getValue()-1, date.getDayOfMonth());
    if(now.before(dateOfBirth))
      return null;
    return  dateOfBirth;
  }
  public String getPhoneNumber()
  {
    String phoneNumberString=phoneField.getText();
    if(phoneNumberString.length()>12 ||phoneNumberString.length()<6)
      return null;

    int phoneNumber=0;
    try {
      phoneNumber=Integer.parseInt(phoneField.getText());
    }
    catch (NumberFormatException e){
      return null;
    }
    return phoneNumberString;
  }

  private String getEmail() throws RemoteException, SQLException
  {
    String email=eMailField.getText();
    for(int i=0;i<addPersonalAccountViewModel.getCustomers().size();i++)
    {
      if(addPersonalAccountViewModel.getCustomers().get(i).getEmail().equals(email))
        return null;
    }
    return email;
  }

  public void onCreateButton() throws RemoteException, SQLException {

      if (checkPassword() && getDateBirth()!=null && getCpr()!=null && getEmail()!=null){
        Customer customer =
            addPersonalAccountViewModel.createPersonalAccount(
                firstNameField.getText(),
                lastNameField.getText(),
                getDateBirth(),
                getEmail(),
                passwordField.getText(),
                phoneField.getText(),
                drivingLicenseField.getText(),
                getCpr()
            );
        viewHandler.openMenuCustomerView(customer);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Your profile was created");
        alert.setContentText("Your personal profile is ready!\nThank you!");
        alert.showAndWait();
    }
    if(getCpr()==null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid input");
      alert.setContentText(
          "Please enter a valid CPR!\nPlease try again!");
      alert.showAndWait();
    }
    if(getDateBirth()==null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid input");
      alert.setContentText(
          "Please enter a date of birth!\nPlease try again!");
      alert.showAndWait();
    }
    if(getPhoneNumber()==null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid input");
      alert.setContentText("Please enter a phone number!");
      alert.showAndWait();
    }
    if(getEmail()==null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid input");
      alert.setContentText("Please enter a unique email address!");
      alert.showAndWait();
    }
  }

  public void onMenuButton() throws SQLException, RemoteException {
    viewHandler.openLogInCustomer();
  }

}
