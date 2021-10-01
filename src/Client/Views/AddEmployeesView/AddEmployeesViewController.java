package Client.Views.AddEmployeesView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Client.ViewModel.AddEmployeeViewModel;
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

public class AddEmployeesViewController implements ViewController
{
  @FXML TextField firstNameField;
  @FXML TextField lastNameField;
  @FXML DatePicker dateOfBirthPicker;
  @FXML TextField eMailField;
  @FXML TextField position;
  @FXML TextField cprFirstField;
  @FXML TextField cprSecondField;
  @FXML TextField salary;
  @FXML TextField phoneField;

  private ViewHandler viewHandler;
  private AddEmployeeViewModel addEmployeeViewModel;

  private boolean manager;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory) throws SQLException, RemoteException
  {
    this.viewHandler=viewHandler;
    addEmployeeViewModel=viewModelFactory.getAddEmployeeViewModel();
  }

  public void setManager(boolean manager)
  {
    this.manager = manager;
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
      alert.setTitle("Invalid Input");
      alert.setContentText(
          "Please enter a valid cpr!\nPlease try again!");
      alert.showAndWait();
      setter=false;
    }
    if(cprFirstField.getText().length()!=6 && cprSecondField.getText().length()!=4)
      setter=false;

    String cpr=cprFirstField.getText()+cprSecondField.getText();
    for(int i=0;i<addEmployeeViewModel.getEmployees().size();i++)
    {
      if(cpr.equals(addEmployeeViewModel.getEmployees().get(i).getCpr()))
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
    for(int i=0;i<addEmployeeViewModel.getEmployees().size();i++)
    {
      if(addEmployeeViewModel.getEmployees().get(i).getEmail().equals(email))
        return null;
    }
    return email;
  }

  public int getSalary()
  {
    String salaryString=salary.getText();
    int salaryInt=0;
    try{
        salaryInt=Integer.parseInt(salaryString);
    }
    catch (NumberFormatException e)
    {
      return 0;
    }
    return salaryInt;
  }

  private String getPosition()
  {
    String positionString=position.getText();
    if(positionString.equalsIgnoreCase("employee") || positionString.equalsIgnoreCase("manager"))
      return positionString;
    return null;
  }

  public void onCreateButton() throws RemoteException, SQLException, NumberFormatException{

    if(dateOfBirthPicker!=null && getCpr()!=null &&getDateBirth()!=null && getPhoneNumber()!=null && getSalary()!=0 && getEmail()!=null && getPosition()!=null)
    {
      addEmployeeViewModel.createEmployee(
          getCpr(),
          firstNameField.getText(),
          lastNameField.getText(),
          getDateBirth(),
          phoneField.getText(),
          getEmail(),
          getSalary(),
          getPosition()
      );
      viewHandler.openListOfEmployees(manager);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Employee created");
      alert.setContentText("The employee has been created!\nThank you!");
      alert.showAndWait();
    }
    if(getCpr()==null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid Input");
      alert.setContentText(
          "Please enter a valid CPR!\nPlease try again!");
      alert.showAndWait();
    }
    if(getDateBirth()==null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid Input");
      alert.setContentText(
          "Please enter a valid birth date!\nPlease try again!");
      alert.showAndWait();
    }
    if(getPhoneNumber()==null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid input");
      alert.setContentText("Please enter a phone number!");
      alert.showAndWait();
    }
    if(getSalary()==0)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid Input");
      alert.setContentText(
          "Please enter a valid salary!\nPlease try again!");
      alert.showAndWait();
    }
    if(getEmail()==null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid input");
      alert.setContentText("Please enter a unique email address!");
      alert.showAndWait();
    }
    if(getPosition()==null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Invalid input");
      alert.setContentText("Please enter a valid position!");
      alert.showAndWait();
    }
  }

  public void onMenuButton(){
    viewHandler.openMainMenu(manager);
  }

}
