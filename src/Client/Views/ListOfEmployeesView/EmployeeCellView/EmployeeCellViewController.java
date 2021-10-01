package Client.Views.ListOfEmployeesView.EmployeeCellView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Util.Customer;
import Util.Employee;
import Client.Views.ListOfEmployeesView.ListOfEmployeesViewController;
import Client.Views.ViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class EmployeeCellViewController extends ListCell<Employee>
{
  private FXMLLoader mLLoader;
  @FXML AnchorPane anchorPane;


  @FXML Label firstNameLabel;
  @FXML Label lastNameLabel;
  @FXML Label dateOfBirtLabel;
  @FXML Label eMailLabel;
  @FXML Label phoneLabel;
  @FXML Label positionLabel;
  @FXML Label cprLabel;
  @FXML Label salaryLabel;

  private ListOfEmployeesViewController listOfEmployeesViewController;
  private Employee employee;


  public EmployeeCellViewController(ListOfEmployeesViewController listOfEmployeesViewController)
  {
    this.listOfEmployeesViewController=listOfEmployeesViewController;
  }

  protected void updateItem(Employee employee, boolean empty)
  {
    super.updateItem(employee, empty);
    this.employee= employee;
    if (empty || employee == null)
    {
      setText(null);
      setGraphic(null);
    }
    else
    {
      if (mLLoader == null)
      {
        mLLoader = new FXMLLoader(getClass().getResource(
            "EmployeeCellView.fxml"));
        mLLoader.setController(this);

        try
        {
          anchorPane = mLLoader.load();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }

      }
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

      firstNameLabel.setText(employee.getFirstName());
      lastNameLabel.setText(employee.getLastName());
      dateOfBirtLabel.setText(dateFormat.format(employee.getDateOfBirth().getTime()));
      eMailLabel.setText(employee.getEmail());
      phoneLabel.setText(employee.getPhoneNumber());
      positionLabel.setText(employee.getPosition());
      cprLabel.setText(employee.getCpr());
      salaryLabel.setText(String.valueOf(employee.getSalary()));


      setText(null);
      setGraphic(this.anchorPane);

      if (getIndex()%2==0){
        //OnOrange
        setStyle("-fx-background-color: FFFFFF");
      } else {
        //OnWhite
        setStyle("-fx-background-color: F4AF82");
      }
    }
  }

  public void onClickedEditButton() throws SQLException, RemoteException
  {
    listOfEmployeesViewController.onEditEmployee(employee);
  }
  public void onClickedDeleteButton() throws SQLException, RemoteException
  {
    listOfEmployeesViewController.deleteEmployee(employee);
  }

}
