package Server.DatabaseAccess;

import Util.Employee;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ManageEmployees
{
  /**
   * This method is for creating an Employee in the system, only can be accessed by a Manager.
   *  The method can be accessed in the ServerModelManager class.
   * @param employee
   * @throws RemoteException
   * @throws SQLException
   */
  void createEmployee(Employee employee) throws RemoteException, SQLException;
  /**
   * This method is for editing an Employee's information in the system, only can be accessed by a Manager.
   * The method can be accessed in the ServerModelManager class.
   * @param employee
   * @param newEmployee
   * @throws RemoteException
   * @throws SQLException
   */
  void editEmployeeInfo(Employee employee,Employee newEmployee) throws RemoteException, SQLException;
  /**
   * This method is for returning all the Employees in the system.
   *  The method can be accessed in the ServerModelManager class.
   * @return
   * @throws RemoteException
   * @throws SQLException
   */
  ArrayList<Employee> getEmployees() throws RemoteException, SQLException;
  /**
   * This method is for deleting an Employee from the system, only can be accessed by a Manager.
   *  The method can be accessed in the ServerModelManager class.
   * @param employee
   * @throws RemoteException
   * @throws SQLException
   */
  void deleteEmployee(Employee employee) throws RemoteException, SQLException;
}
