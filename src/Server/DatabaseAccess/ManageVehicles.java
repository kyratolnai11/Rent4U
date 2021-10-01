package Server.DatabaseAccess;

import Util.Status;
import Util.Vehicle;


import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface ManageVehicles
{
    /**
     * This method is for adding a new Vehicle in the system. The method can be accessed in the ServerModelManager class.
     * @param vehicle
     * @throws SQLException
     */
    void addNewVehicle(Vehicle vehicle) throws SQLException;
    /**
     * This method is for returning all the Vehicles in the system. The method can be accessed in the ServerModelManager class.
     * @return Arraylist of Vehicles
     * @throws SQLException
     */
    ArrayList<Vehicle> viewAllVehicles() throws SQLException;
    /**
     * This method is for setting the Status of an existing Vehicle in the system. The method can be accessed in the ServerModelManager class.
     * @param vehicle
     * @param status
     * @throws SQLException
     */
    void setStatus(Vehicle vehicle, Status status) throws SQLException;
    /**
     * This method is for editing a Vehicle's information in te system. The method can be accessed in the ServerModelManager class.
     * @param vehicle
     * @param newVehicle
     * @throws SQLException
     */
    void editVehicleInfo(Vehicle vehicle, Vehicle newVehicle) throws SQLException;
    /**
     * This method is to return all the available vehicles for a specific time period. The method can be accessed in the ServerModelManager class.
     * @param startDate
     * @param endDate
     * @param type
     * @return Arraylist of Vehicles
     * @throws RemoteException
     * @throws SQLException
     */
    ArrayList<Vehicle> getFreeVehicles(GregorianCalendar startDate, GregorianCalendar endDate, String type) throws RemoteException, SQLException;
    /**
     * This method is for deleting a Vehicle from the system. The method can be accessed in the ServerModelManager class.
     * @param vehicle
     * @throws RemoteException
     * @throws SQLException
     */
  void deleteVehicle(Vehicle vehicle) throws RemoteException, SQLException;
}
