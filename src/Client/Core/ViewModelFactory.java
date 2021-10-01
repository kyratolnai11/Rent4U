package Client.Core;

import Client.ViewModel.*;
import Client.Views.AddBookingCustomerView.AddBookingCustomerViewController;
import Client.Views.EditPersonalBookingView.EditPersonalBookingViewController;
import Client.Views.ListOfEmployeesView.ListOfEmployeesViewController;

public class ViewModelFactory
{
  private static ViewModelFactory instance = new ViewModelFactory();

  public static ViewModelFactory getInstance(){
    if(instance == null)
      instance = new ViewModelFactory();
    return instance;
  }

  private EditPersonalBookingViewModel editPersonalBookingViewModel;
  private LogInViewModel logInViewModel;
  private AddVehicleViewModel addVehicleViewModel;
  private MenuViewModel menuViewModel;
  private ListOfVehiclesViewModel listOfVehiclesViewModel;
  private AddBookingViewModel addBookingViewModel;
  private SetStatusViewModel setStatusViewModel;
  private EditVehicleInfoViewModel editVehicleInfoVewModel;
  private EditBookingInfoViewModel editBookingInfoViewModel;
  private ListOfBookingsViewModel listOfBookingsViewModel;
  private AddCustomerAccountViewModel addCustomerAccountViewModel;
  private EditCustomerInfoViewModel editCustomerInfoViewModel;
  private ListOfCustomersViewModel listOfCustomersViewModel;
  private AddPersonalAccountViewModel addpersonalAccountViewModel;
  private MenuForLogInViewModel menuForLogInViewModel;
  private EditPersonalInfoViewModel editPersonalInfoViewModel;
  private AddBookingCustomerViewModel addBookingCustomerViewModel;
  private LogInCustomerViewModel logInCustomerViewModel;
  private MenuCustomerViewModel menuCustomerViewModel;
  private ListOfPersonalBookingsViewModel listOfPersonalBookingsViewModel;
  private AddEmployeeViewModel addEmployeeViewModel;
  private ListOfEmployeesViewModel listOfEmployeesViewModel;
  private EditEmployeeInfoViewModel editEmployeeInfoViewModel;
  private ViewModelFactory() { }

  public LogInViewModel getLogInViewModel() {
    if (logInViewModel == null)
      logInViewModel = new LogInViewModel(ModelFactory.getInstance().getClientModel());
    return logInViewModel;
  }

  public AddVehicleViewModel getAddVehicleViewModel() {
    if(addVehicleViewModel==null)
      addVehicleViewModel=new AddVehicleViewModel(ModelFactory.getInstance().getClientModel());
    return addVehicleViewModel;
  }

  public MenuViewModel getMenuViewModel() {
    if(menuViewModel==null)
      menuViewModel=new MenuViewModel(ModelFactory.getInstance().getClientModel());
    return menuViewModel;
  }

  public ListOfVehiclesViewModel getListOfVehiclesViewModel() {
    if(listOfVehiclesViewModel==null)
      listOfVehiclesViewModel=new ListOfVehiclesViewModel(ModelFactory.getInstance().getClientModel());
    return listOfVehiclesViewModel;
  }

  public AddBookingViewModel getAddBookingViewModel() {
    if (addBookingViewModel == null)
      addBookingViewModel = new AddBookingViewModel(ModelFactory.getInstance().getClientModel());
    return addBookingViewModel;
  }

  public SetStatusViewModel getSetStatusViewModel() {
    if (setStatusViewModel == null)
      setStatusViewModel = new SetStatusViewModel(ModelFactory.getInstance().getClientModel());
    return setStatusViewModel;
  }

  public EditVehicleInfoViewModel getEditVehicleInfoViewModel() {
    if(editVehicleInfoVewModel==null)
      editVehicleInfoVewModel=new EditVehicleInfoViewModel(ModelFactory.getInstance().getClientModel());
    return editVehicleInfoVewModel;
  }

  public EditBookingInfoViewModel getEditBookingInfoViewModel() {
    if(editBookingInfoViewModel==null)
      editBookingInfoViewModel=new EditBookingInfoViewModel(ModelFactory.getInstance().getClientModel());
    return editBookingInfoViewModel;
  }

  public ListOfBookingsViewModel getListOfBookingsViewModel() {
    if(listOfBookingsViewModel==null)
      listOfBookingsViewModel=new ListOfBookingsViewModel(ModelFactory.getInstance().getClientModel());
    return listOfBookingsViewModel;
  }

  public AddCustomerAccountViewModel getAddCustomerAccountViewModelViewModel() {
    if(addCustomerAccountViewModel==null)
      addCustomerAccountViewModel=new AddCustomerAccountViewModel(ModelFactory.getInstance().getClientModel());
    return addCustomerAccountViewModel;
  }

  public ListOfCustomersViewModel getListOfCustomersViewModel() {
    if(listOfCustomersViewModel==null)
      listOfCustomersViewModel=new ListOfCustomersViewModel(ModelFactory.getInstance().getClientModel());
    return listOfCustomersViewModel;
  }

  public EditCustomerInfoViewModel getEditCustomerInfoViewModelViewModel() {
    if(editCustomerInfoViewModel==null)
      editCustomerInfoViewModel=new EditCustomerInfoViewModel(ModelFactory.getInstance().getClientModel());
    return editCustomerInfoViewModel;
  }

  public AddPersonalAccountViewModel getAddPersonalAccountViewModelViewModel() {
    if(addpersonalAccountViewModel==null)
      addpersonalAccountViewModel=new AddPersonalAccountViewModel(ModelFactory.getInstance().getClientModel());
    return addpersonalAccountViewModel;
  }

  public EditPersonalInfoViewModel getEditPersonalInfoViewModelViewModel() {
    if(editPersonalInfoViewModel==null)
      editPersonalInfoViewModel=new EditPersonalInfoViewModel(ModelFactory.getInstance().getClientModel());
    return editPersonalInfoViewModel;
  }

  public MenuForLogInViewModel getMenuForLogInViewModel() {
    if(menuForLogInViewModel==null)
      menuForLogInViewModel=new MenuForLogInViewModel(ModelFactory.getInstance().getClientModel());
    return menuForLogInViewModel;
  }

  public LogInCustomerViewModel getLogInCustomerViewModel() {
    if(logInCustomerViewModel==null)
      logInCustomerViewModel=new LogInCustomerViewModel(ModelFactory.getInstance().getClientModel());
    return logInCustomerViewModel;
  }

  public AddBookingCustomerViewModel getAddBookingCustomerViewModel() {
    if (addBookingCustomerViewModel == null)
    {
      addBookingCustomerViewModel = new AddBookingCustomerViewModel(ModelFactory.getInstance().getClientModel());
    }
    return addBookingCustomerViewModel;
  }

  public MenuCustomerViewModel getMenuCustomerViewModel(){
    if(menuCustomerViewModel==null)
      menuCustomerViewModel=new MenuCustomerViewModel(ModelFactory.getInstance().getClientModel());
    return menuCustomerViewModel;
  }

  public EditPersonalBookingViewModel getEditPersonalBookingViewModel() {
    if(editPersonalBookingViewModel==null)
      editPersonalBookingViewModel=new EditPersonalBookingViewModel(ModelFactory.getInstance().getClientModel());
    return editPersonalBookingViewModel;
  }

  public ListOfPersonalBookingsViewModel getListOfPersonalBookingViewModel() {
    if (listOfVehiclesViewModel == null)
    {
      listOfPersonalBookingsViewModel = new ListOfPersonalBookingsViewModel(ModelFactory.getInstance()
          .getClientModel());
    }
    return listOfPersonalBookingsViewModel;
  }

  public AddEmployeeViewModel getAddEmployeeViewModel() {
    if (addEmployeeViewModel == null)
      addEmployeeViewModel = new AddEmployeeViewModel(ModelFactory.getInstance().getClientModel());
    return addEmployeeViewModel;
  }

  public EditEmployeeInfoViewModel getEditEmployeeViewModel() {
    if (editEmployeeInfoViewModel == null)
      editEmployeeInfoViewModel = new EditEmployeeInfoViewModel(ModelFactory.getInstance().getClientModel());
    return editEmployeeInfoViewModel;
  }

  public ListOfEmployeesViewModel getListOfEmployeesViewModel() {
    if(listOfEmployeesViewModel==null)
      listOfEmployeesViewModel=new ListOfEmployeesViewModel(ModelFactory.getInstance().getClientModel());
    return listOfEmployeesViewModel;
  }
}
