package Client.Views.EditCustomerAccountInfoView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Util.Customer;
import Client.ViewModel.EditCustomerInfoViewModel;
import Client.Views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EditCustomerAccountInfoViewController implements ViewController
{
  private ViewHandler viewHandler;
  private EditCustomerInfoViewModel editCustomerInfoViewModel;

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

  private boolean manager;


  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory) throws SQLException, RemoteException
  {
    this.viewHandler=viewHandler;
    this.editCustomerInfoViewModel=viewModelFactory.getEditCustomerInfoViewModelViewModel();

  }

  public void setManager(boolean manager)
  {
    this.manager = manager;
  }

  public void setCustomer(Customer customer)
  {
    this.customer=customer;
    loadData();
  }

  public static final LocalDate dateConvertor (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
  }

  public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();
  }


  public void loadData(){
    firstNameField.setText(customer.getFirstName());
    lastNameField.setText(customer.getLastName());
    dateOfBirthPicker.setValue(convertToLocalDateViaInstant(customer.getDateOfBirth().getTime()));
    eMailField.setText(customer.getEmail());
    drivingLicenseField.setText(customer.getDrivingLicenseNumber());
    System.out.println(customer.getCpr_number());
    cprFirstField.setText(customer.getCpr_number().substring(0,6));
    cprSecondField.setText(customer.getCpr_number().substring(6,10));
    passwordField.setText(customer.getPassword());
    rePasswordField.setText(customer.getPassword());
    phoneField.setText(customer.getPhoneNumber());
    passwordCheckLabel.setVisible(false);
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
    for(int i=0;i<editCustomerInfoViewModel.getCustomers().size();i++)
    {
      if(!editCustomerInfoViewModel.getCustomers().get(i).equals(customer))
        if(cpr.equals(editCustomerInfoViewModel.getCustomers().get(i).getCpr_number()))
          setter=false;
    }
    if(setter)
      return cprFirstField.getText()+cprSecondField.getText();
    return null;
  }

  private String getEmail() throws RemoteException, SQLException
  {
    String email=eMailField.getText();
    for(int i=0;i<editCustomerInfoViewModel.getCustomers().size();i++)
    {
      if(!editCustomerInfoViewModel.getCustomers().get(i).equals(customer))
        if(editCustomerInfoViewModel.getCustomers().get(i).getEmail().equals(email))
          return null;
    }
    return email;
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

  public void onUpdateCustomer() throws RemoteException, SQLException
  {
    if(getDateBirth()!=null && getCpr()!=null && getPhoneNumber()!=null && getEmail()!=null)
    {
    editCustomerInfoViewModel.editCustomerInfo(
        customer,
        firstNameField.getText(),
        lastNameField.getText(),
        getDateBirth(),
        getEmail(),
        passwordField.getText(),
        getPhoneNumber(),
        drivingLicenseField.getText(),
        getCpr());
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Customer information edited");
      alert.setContentText("The customer information has been successfully edited!\nThank you!");
      alert.showAndWait();
      viewHandler.openListOfCustomers(manager);
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

  public void onMenu(){
    viewHandler.openMainMenu(manager);
  }

  public void onDeleteButton() throws RemoteException, SQLException
  {
    editCustomerInfoViewModel.deleteCustomer(customer);
    viewHandler.openLogInCustomer();
  }
}

