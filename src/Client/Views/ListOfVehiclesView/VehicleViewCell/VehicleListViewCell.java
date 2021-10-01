package Client.Views.ListOfVehiclesView.VehicleViewCell;

import Util.Vehicle;
import Client.Views.ListOfVehiclesView.ListOfVehiclesViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class VehicleListViewCell extends ListCell<Vehicle>
{
  private ListOfVehiclesViewController listOfVehiclesViewController;

  private FXMLLoader mLLoader;
  public Vehicle vehicle;
  @FXML AnchorPane anchorPane;
  @FXML Label makeLabel;
  @FXML Label modelLabel;
  @FXML Label yearLabel;
  @FXML Label gearboxTypeLabel;
  @FXML Label fuelTypeLabel;
  @FXML Label nbOfSeatsLabel;
  @FXML Label licensePlateLabel;
  @FXML Label enginesPowerLabel;
  @FXML Label priceLabel;
  @FXML Label typeLabel;

  public VehicleListViewCell(ListOfVehiclesViewController listOfVehiclesViewController)
  {
    super();
    this.listOfVehiclesViewController=listOfVehiclesViewController;
  }

  @Override protected void updateItem(Vehicle vehicle, boolean empty)
  {
    super.updateItem(vehicle, empty);
    this.vehicle = vehicle;

    if (empty || vehicle == null)
    {
      setText(null);
      setGraphic(null);
    }
    else
    {
      if (mLLoader == null)
      {
        mLLoader = new FXMLLoader(getClass().getResource("VehicleListViewCell.fxml"));
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
      typeLabel.setText(vehicle.getType());
      makeLabel.setText(vehicle.getMake());
      modelLabel.setText(vehicle.getModel());
      yearLabel.setText(String.valueOf(vehicle.getYear()));
      gearboxTypeLabel.setText(vehicle.getTypeOfGearbox());
      fuelTypeLabel.setText(vehicle.getTypeOfFuel());
      nbOfSeatsLabel.setText(String.valueOf(vehicle.getNumberOfSeats()));
      licensePlateLabel.setText(vehicle.getLicensePlate());
      enginesPowerLabel.setText(String.valueOf(vehicle.getEnginesPower()));
      priceLabel.setText(String.valueOf(vehicle.getPrice()));

      if (getIndex()%2==0){
        //OnOrange
        setStyle("-fx-background-color: FFFFFF");
      } else {
        //OnWhite
        setStyle("-fx-background-color: F4AF82");
      }

      setText(null);
      setGraphic(this.anchorPane);
    }

    }


  public void clickedEditButton() throws SQLException, RemoteException
  {
    listOfVehiclesViewController.onEdit(vehicle);
  }

  public void clickedDeleteButton() throws RemoteException, SQLException
  {
    listOfVehiclesViewController.onClickedDelete(vehicle);
  }

  public void clickedStatusButton() throws RemoteException, SQLException
  {
    listOfVehiclesViewController.onStatusButton(vehicle);
  }
}
