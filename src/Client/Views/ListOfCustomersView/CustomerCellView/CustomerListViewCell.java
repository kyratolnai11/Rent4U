package Client.Views.ListOfCustomersView.CustomerCellView;

import Util.Customer;
import Client.Views.ListOfBookingsView.ListOfBookingsViewController;
import Client.Views.ListOfCustomersView.ListOfCustomersViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class CustomerListViewCell extends ListCell<Customer>
{
  private FXMLLoader mLLoader;
  @FXML AnchorPane anchorPane;

  @FXML Label firstNameLabel;
  @FXML Label lastNameLabel;
  @FXML Label dateOfBirtLabel;
  @FXML Label eMailLabel;
  @FXML Label phoneLabel;
  @FXML Label drivingLicenseLabel;
  @FXML Label cprLabel;

  private ListOfCustomersViewController listOfCustomersViewController;
  private Customer customer;

  public CustomerListViewCell(ListOfCustomersViewController listOfCustomersViewController){
    this.listOfCustomersViewController=listOfCustomersViewController;
  }

  @Override protected void updateItem(Customer customer, boolean empty)
  {
    super.updateItem(customer, empty);
    this.customer=customer;
    if (empty || customer == null)
    {
      setText(null);
      setGraphic(null);
    }
    else
    {
      if (mLLoader == null)
      {
        mLLoader = new FXMLLoader(getClass().getResource("CustomerViewCell.fxml"));
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

      firstNameLabel.setText(customer.getFirstName());
      lastNameLabel.setText(customer.getLastName());
      dateOfBirtLabel.setText(dateFormat.format(customer.getDateOfBirth().getTime()));
      eMailLabel.setText(customer.getEmail());
      phoneLabel.setText(customer.getPhoneNumber());
      drivingLicenseLabel.setText(customer.getDrivingLicenseNumber());
      cprLabel.setText(customer.getCpr_number());

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
    listOfCustomersViewController.editCustomer(customer);
  }

  public void onClickedDeleteButton() throws RemoteException, SQLException
  {
    listOfCustomersViewController.deleteCustomer(customer);
  }

}

