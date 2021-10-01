package Client.Views.EditEmployeeInfoView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Util.Employee;
import Client.ViewModel.EditEmployeeInfoViewModel;
import Client.Views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EditEmployeeInfoViewController implements ViewController
{
  private ViewHandler viewHandler;
  private EditEmployeeInfoViewModel editEmployeeInfoViewModel;

  private Employee employee;

  @FXML TextField firstNameField;
  @FXML TextField lastNameField;
  @FXML DatePicker dateOfBirthPicker;
  @FXML TextField eMailField;
  @FXML TextField cprFirstField;
  @FXML TextField cprSecondField;
  @FXML TextField phoneField;
  @FXML TextField position;
  @FXML TextField salaryField;

  private boolean manager;


  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory) throws SQLException, RemoteException
  {
    this.viewHandler = viewHandler;
    this.editEmployeeInfoViewModel = viewModelFactory.getEditEmployeeViewModel();
  }

  public void setEmployee(Employee employee)
  {
    this.employee = employee;
    loadData();
  }

  public void setManager(boolean manager)
  {
    this.manager = manager;
  }

  public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();
  }

  public void loadData()
  {
    firstNameField.setText(employee.getFirstName());
    lastNameField.setText(employee.getLastName());
    dateOfBirthPicker.setValue(convertToLocalDateViaInstant(employee.getDateOfBirth().getTime()));
    eMailField.setText(employee.getEmail());
    cprFirstField.setText(employee.getCpr().substring(0,6));
    cprSecondField.setText(employee.getCpr().substring(6,10));
    phoneField.setText(employee.getPhoneNumber());
    position.setText(employee.getPosition());
    String str = Double.toString(employee.getSalary());
    salaryField.setText(str);
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
    for(int i=0;i<editEmployeeInfoViewModel.getEmployees().size();i++)
    {
      if(!editEmployeeInfoViewModel.getEmployees().get(i).equals(employee))
        if(cpr.equals(editEmployeeInfoViewModel.getEmployees().get(i).getCpr()))
          setter=false;
    }
    if(setter)
      return cprFirstField.getText()+cprSecondField.getText();
    return null;
  }
  private String getEmail() throws RemoteException, SQLException
  {
    String email=eMailField.getText();
    for(int i=0;i<editEmployeeInfoViewModel.getEmployees().size();i++)
    {
      if(!editEmployeeInfoViewModel.getEmployees().get(i).equals(employee))
        if(editEmployeeInfoViewModel.getEmployees().get(i).getEmail().equals(email))
          return null;
    }
    return email;
  }

  private GregorianCalendar getDateOfBirth()
  {
    GregorianCalendar now=new GregorianCalendar();
    LocalDate date = dateOfBirthPicker.getValue();
    GregorianCalendar dateOfBirth = new GregorianCalendar(date.getYear(), date.getMonth().getValue()-1, date.getDayOfMonth());
    if(now.before(dateOfBirth))
      return null;
    return  dateOfBirth;
  }

  private double getSalary()
  {
    String salaryString=salaryField.getText();
    double salaryInt=0;
    try{
      salaryInt=Double.parseDouble(salaryString);
    }
    catch (NumberFormatException e)
    {
      return 0;
    }
    return salaryInt;
  }

  private String getPhoneNumber()
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

  private String getPosition()
  {
    String positionString=position.getText();
    if(positionString.equalsIgnoreCase("employee") || positionString.equalsIgnoreCase("manager"))
      return positionString;
    return null;
  }

  public void onSaveButton() throws RemoteException, SQLException
  {
    if (getDateOfBirth() != null && getCpr() != null && getDateOfBirth()!=null && getPhoneNumber()!=null && getSalary()!=0 && getEmail()!=null && getPosition()!=null)
    {
      editEmployeeInfoViewModel.editEmployeeInfo(
          employee,
          getCpr(),
          firstNameField.getText(),
          lastNameField.getText(),
          getDateOfBirth(),
          getPhoneNumber(),
          getEmail(),
          getSalary(),
          getPosition());
      viewHandler.openListOfEmployees(manager);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Employee information edited");
      alert.setContentText(
          "The employee information has been successfully edited!\nThank you!");
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
    if(getDateOfBirth()==null)
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

  public void onMenu()
  {
    viewHandler.openMainMenu(manager);
  }

}
