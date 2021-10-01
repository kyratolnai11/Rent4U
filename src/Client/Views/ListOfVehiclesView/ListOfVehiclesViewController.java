package Client.Views.ListOfVehiclesView;

import Client.Core.ViewHandler;
import Client.Core.ViewModelFactory;
import Util.Vehicle;
import Client.ViewModel.ListOfVehiclesViewModel;
import Client.Views.ListOfVehiclesView.VehicleViewCell.VehicleListViewCell;
import Client.Views.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ListOfVehiclesViewController implements ViewController
{
  private ViewHandler viewHandler;
  private ListOfVehiclesViewModel listOfVehiclesViewModel;
  private boolean manager;

  @FXML ListView<Vehicle> listView;


  public final ObservableList<Vehicle> tableObservableList = FXCollections.observableArrayList();

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory) throws SQLException, RemoteException {
    this.viewHandler = viewHandler;
    this.listOfVehiclesViewModel = viewModelFactory.getListOfVehiclesViewModel();
    getVehicleData(listOfVehiclesViewModel.getVehicles());

    listView.setItems(tableObservableList);
    listView.setCellFactory(vehicleListView -> new VehicleListViewCell(this));
    listView.setFixedCellSize(50);

    listView.setFocusTraversable( false );
  }

  public void onAddVehicleButton(ActionEvent evt)
  {
    viewHandler.openAddVehicle(manager);
  }

  public void onMenuButton(ActionEvent evt)
  {
    viewHandler.openMainMenu(manager);
  }

  public void onEdit(Vehicle vehicle) throws SQLException, RemoteException
  {
    viewHandler.openEditVehicle(vehicle, manager);
  }

  public void onClickedDelete(Vehicle vehicle)
      throws RemoteException, SQLException
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Delete Vehicle");
    alert.setHeaderText("");
    alert.setContentText("Are you sure you would like to delete this vehicle?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
    listOfVehiclesViewModel.deleteVehicle(vehicle);
    viewHandler.openListOfVehicleView(manager);}
    else if(result.get()==ButtonType.CANCEL)
      viewHandler.openListOfVehicleView(manager);}



  public ObservableList<Vehicle> getVehicleData(ArrayList<Vehicle> vehiclesArrayList) {
    for (int x = 0; x < vehiclesArrayList.size(); x++) {
      tableObservableList.add(vehiclesArrayList.get(x));
    }
    return tableObservableList;
  }

  public void setManager(boolean manager)
  {
    this.manager = manager;
  }

  public void onStatusButton(Vehicle vehicle)
  {
    viewHandler.openSetStatusView(vehicle, manager);
  }
}
