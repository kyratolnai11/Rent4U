package Client.ViewModel;

import Client.Model.ClientModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.beans.PropertyChangeEvent;

public class LogInViewModel
{
  private ClientModel userModel;
  private StringProperty password;
  private String employeePassword;
  private String managerPassword;

  public LogInViewModel(ClientModel userModel)
  {
    this.userModel= userModel;
    password = new SimpleStringProperty();
    employeePassword = "EMP2021";
    managerPassword = "MNG2021";
  }

  public StringProperty passwordProperty()
  {
    return password;
  }

  public int logIn()
  {
    if(password.getValue().equals(employeePassword)) {
      return 0;
    }
    else if (password.getValue().equals(managerPassword))
      return 1;
    else return -1;
  }


}
