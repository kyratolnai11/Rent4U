package Client.ViewModel;

import Client.Model.ClientModel;
import Util.Customer;
import Util.Employee;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListOfEmployeesViewModel
{
  private ClientModel userModel;

  public ListOfEmployeesViewModel(ClientModel userModel)
  {
    this.userModel=userModel;
  }

  public ArrayList<Employee> getEmployees() throws SQLException, RemoteException
  {
    return userModel.getEmployees();
  }

  public void deleteEmployee(Employee employee) throws RemoteException, SQLException
  {
    userModel.deleteEmployee(employee);
  }
}
