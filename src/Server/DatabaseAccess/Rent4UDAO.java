package Server.DatabaseAccess;

import Util.*;

import java.rmi.RemoteException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public class Rent4UDAO implements ManageVehicles, ManageBookings, ManageCustomers, ManageEmployees
{
    //We are using singleton for this class because we want to have only one instance in our program
    private static Rent4UDAO instance;
    public static synchronized Rent4UDAO getInstance() throws SQLException
    {
        if(instance == null)
            instance = new Rent4UDAO();
        return instance;
    }

    public Rent4UDAO() throws SQLException
    {
        //registering the driver
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    private Connection getConnection() throws SQLException
    {
        //and making the connection with the database
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=rent4u",
                "postgres","Kyracska11");
    }

    @Override
    public void addNewVehicle(Vehicle vehicle) throws  SQLException
    {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Vehicle(licence_plate, type, make, model,"
                    + "year, engine_power, type_of_gearbox, number_of_seats, type_of_fuel, price)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, vehicle.getLicensePlate());
            statement.setString(2, vehicle.getType());
            statement.setString(3, vehicle.getMake());
            statement.setString(4, vehicle.getModel());
            statement.setInt(5, vehicle.getYear());
            statement.setInt(6, vehicle.getEnginesPower());
            if(vehicle.getTypeOfGearbox().equalsIgnoreCase("automatic"))
                statement.setString(7, "Automatic");
            else
                statement.setString(7, "Manual");
            statement.setInt(8, vehicle.getNumberOfSeats());
            if(vehicle.getTypeOfFuel().equalsIgnoreCase("petrol"))
                statement.setString(9, "Petrol");
            else if(vehicle.getTypeOfFuel().equalsIgnoreCase("diesel"))
                statement.setString(9, "Diesel");
            else if(vehicle.getTypeOfFuel().equalsIgnoreCase("electric"))
                statement.setString(9, "Electric");
            else
                statement.setString(9, "Hybrid");
            statement.setDouble(10, vehicle.getPrice());
            statement.executeUpdate();
        }
    }

    @Override
    public ArrayList<Vehicle> viewAllVehicles() throws SQLException
    {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM vehicle");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                vehicles.add(getVehicle(resultSet));
            }
        }
        return vehicles;
    }

    private Vehicle getVehicle(ResultSet resultSet) throws SQLException {
        String licencePlate = resultSet.getString(1);
        String type = resultSet.getString(2);
        String make = resultSet.getString(3);
        String model = resultSet.getString(4);
        int year = resultSet.getInt(5);
        int enginePower = resultSet.getInt(6);
        String typeOfGearBox = resultSet.getString(7);
        int numberOfSeats = resultSet.getInt(8);
        String typeOfFuel = resultSet.getString(9);
        double price = resultSet.getDouble(10);
        return new Vehicle(licencePlate, enginePower, type, make, model, year, typeOfGearBox, typeOfFuel, numberOfSeats, price);
    }

    @Override public void setStatus(Vehicle vehicle, Status status) throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO status(license_plate, " +
                    "start_time, end_time, status) VALUES (?, ?, ?, ?)");
            statement.setString(1, vehicle.getLicensePlate());
            Timestamp start_time = new Timestamp(status.getStartDate().getTimeInMillis());
            statement.setTimestamp(2, start_time);
            Timestamp end_time = new Timestamp(status.getEndDate().getTimeInMillis());
            statement.setTimestamp(3, end_time);
            statement.setString(4, status.getStatus());
            statement.executeUpdate();
        }
    }

    @Override public void editVehicleInfo(Vehicle vehicle, Vehicle newVehicle) throws SQLException {
        try(Connection connection = getConnection()){

            PreparedStatement statement = connection.prepareStatement("UPDATE vehicle SET licence_plate = ? WHERE licence_plate = ?");
            statement.setString(1, newVehicle.getLicensePlate());
            statement.setString(2, vehicle.getLicensePlate());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE Vehicle SET type = ? WHERE licence_plate = ?");
            statement.setString(1, newVehicle.getType());
            statement.setString(2, vehicle.getLicensePlate());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE Vehicle SET make = ? WHERE licence_plate = ?");
            statement.setString(1, newVehicle.getMake());
            statement.setString(2, vehicle.getLicensePlate());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE Vehicle SET model = ? WHERE licence_plate = ?");
            statement.setString(1, newVehicle.getModel());
            statement.setString(2, vehicle.getLicensePlate());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE Vehicle SET year = ? WHERE licence_plate = ?");
            statement.setInt(1, newVehicle.getYear());
            statement.setString(2, vehicle.getLicensePlate());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE Vehicle SET engine_power = ? WHERE licence_plate = ?");
            statement.setInt(1, newVehicle.getEnginesPower());
            statement.setString(2, vehicle.getLicensePlate());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE Vehicle SET type_of_gearbox = ? WHERE licence_plate = ?");
            if(newVehicle.getTypeOfGearbox().equalsIgnoreCase("automatic"))
                statement.setString(1, "Automatic");
            else
                statement.setString(1, "Manual");
            statement.setString(2, vehicle.getLicensePlate());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE Vehicle SET number_of_seats = ? WHERE licence_plate = ?");
            statement.setInt(1, newVehicle.getNumberOfSeats());
            statement.setString(2, vehicle.getLicensePlate());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE Vehicle SET type_of_fuel = ? WHERE licence_plate = ?");
            if(newVehicle.getTypeOfFuel().equalsIgnoreCase("petrol"))
                statement.setString(1, "Petrol");
            else if(newVehicle.getTypeOfFuel().equalsIgnoreCase("diesel"))
                statement.setString(1, "Diesel");
            else if(newVehicle.getTypeOfFuel().equalsIgnoreCase("electric"))
                statement.setString(1, "Electric");
            else
                statement.setString(1, "Hybrid");
            statement.setString(2, vehicle.getLicensePlate());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE Vehicle SET price = ? WHERE licence_plate = ?");
            statement.setDouble(1, newVehicle.getPrice());
            statement.setString(2, vehicle.getLicensePlate());
            statement.executeUpdate();
        }
    }

    @Override public void deleteVehicle(Vehicle vehicle) throws RemoteException, SQLException
    {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM vehicle WHERE licence_plate = ?");
            statement.setString(1, vehicle.getLicensePlate());
            statement.executeUpdate();
        }
    }

    @Override public void createBooking(Booking booking) throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO booking(start_time," +
                    "end_time, cpr_of_customer, licence_plate, total_price) VALUES (?, ?, ?, ?, ?)");
            Timestamp start_time = new Timestamp(booking.getStartTime().getTimeInMillis());
            statement.setTimestamp(1, start_time);
            Timestamp end_time = new Timestamp(booking.getEndTime().getTimeInMillis());
            statement.setTimestamp(2, end_time);
            statement.setString(3, booking.getIdOfCustomer());
            statement.setString(4, booking.getLicencePlate());
            statement.setDouble(5, booking.getPrice());
            statement.executeUpdate();
        }
    }

    @Override public ArrayList<Booking> viewAllBookings() throws SQLException
    {
        ArrayList<Booking> bookings = new ArrayList<>();
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM booking");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                bookings.add(getBooking(resultSet));
            }
        }
        return bookings;
    }

    private Booking getBooking(ResultSet resultSet) throws SQLException
    {
        int booking_id = resultSet.getInt(1);
        Timestamp start_time_timestamp = resultSet.getTimestamp(2);
        LocalDateTime start_time_local = start_time_timestamp.toLocalDateTime();
        GregorianCalendar start_time = new GregorianCalendar(start_time_local.getYear(), start_time_local.getMonthValue()-1,
                start_time_local.getDayOfMonth(), start_time_local.getHour(), start_time_local.getMinute());
        Timestamp end_time_timestamp = resultSet.getTimestamp(3);
        LocalDateTime end_time_local = end_time_timestamp.toLocalDateTime();
        GregorianCalendar end_time = new GregorianCalendar(end_time_local.getYear(), end_time_local.getMonthValue()-1,
                end_time_local.getDayOfMonth(), end_time_local.getHour(), end_time_local.getMinute());
        String id_of_customer = resultSet.getString(4);
        String license_plate = resultSet.getString(5);
        double price = resultSet.getDouble(6);
        return new Booking(booking_id, id_of_customer, license_plate, start_time, end_time, price);
    }

    @Override public void editBookingInfo(Booking booking, Booking newBooking) throws RemoteException, SQLException {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("UPDATE booking SET start_time = ? " +
                    "WHERE booking_id = ?");
            Timestamp start_time = new Timestamp(newBooking.getStartTime().getTimeInMillis());
            statement.setTimestamp(1, start_time);
            statement.setInt(2, booking.getBooking_id());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE booking SET end_time = ? WHERE booking_id = ?");
            Timestamp end_time = new Timestamp(newBooking.getEndTime().getTimeInMillis());
            statement.setTimestamp(1, end_time);
            statement.setInt(2, booking.getBooking_id());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE booking SET cpr_of_customer = ? WHERE booking_id = ?");
            statement.setString(1, newBooking.getIdOfCustomer());
            statement.setInt(2, booking.getBooking_id());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE booking SET licence_plate = ? WHERE booking_id = ?");
            statement.setString(1, newBooking.getLicencePlate());
            statement.setInt(2, booking.getBooking_id());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE booking SET total_price = ? WHERE booking_id = ?");
            statement.setDouble(1, newBooking.getPrice());
            statement.setInt(2, booking.getBooking_id());
            statement.executeUpdate();
        }
    }

    @Override
    public ArrayList<Vehicle> getFreeVehicles(GregorianCalendar startDate, GregorianCalendar endDate, String type) throws RemoteException, SQLException{
        ArrayList<Vehicle> freeVehicles = new ArrayList<>();
        try(Connection connection = getConnection()){

        }
        return null;
    }

    @Override public void deleteBooking(Booking booking)
        throws RemoteException, SQLException
    {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM booking WHERE booking_id = ?");
            statement.setInt(1, booking.getBooking_id());
            statement.executeUpdate();
        }
    }

    @Override public ArrayList<Booking> getPersonalBookings(Customer customer)
        throws RemoteException, SQLException
    {
        ArrayList<Booking> bookings = new ArrayList<>();
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM booking WHERE cpr_of_customer = ?");
            statement.setString(1, customer.getCpr_number());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                bookings.add(getBooking(resultSet));
            }
        }
        return bookings;
    }

    @Override public void editPersonalBooking(Booking booking,
        Booking newBooking) throws RemoteException, SQLException
    {
        editBookingInfo(booking, newBooking);
    }

    @Override public void deletePersonalBooking(Booking booking)
        throws RemoteException, SQLException
    {
        deleteBooking(booking);
    }

    @Override public void deleteCustomer(Customer customer)
        throws RemoteException, SQLException
    {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM customer WHERE cpr = ?");
            statement.setString(1, customer.getCpr_number());
            statement.executeUpdate();
        }
    }

    @Override public Customer checkForPassword(String emailAddress, String password) throws RemoteException, SQLException
    {
        Customer customer = new Customer();
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customer WHERE email = ?");
            statement.setString(1, emailAddress);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                customer = getCustomer(resultSet);
            }
        }
        return customer;
    }

    @Override public void editPersonalInfo(Customer customer,
        Customer newCustomer) throws RemoteException, SQLException
    {
        editCustomerInfo(customer, newCustomer);
    }

    @Override
    public void addCustomer(Customer customer) throws RemoteException, SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO customer(cpr, first_name, " +
                    "last_name, date_of_birth, phone_number, email, driving_licence, password) VALUES (?, ?, ?, ?," +
                    "?, ?, ?, ?)");
            statement.setString(1, customer.getCpr_number());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            Timestamp date_of_birth = new Timestamp(customer.getDateOfBirth().getTimeInMillis());
            statement.setTimestamp(4, date_of_birth);
            statement.setInt(5, Integer.parseInt(customer.getPhoneNumber()));
            statement.setString(6, customer.getEmail());
            statement.setString(7, customer.getDrivingLicenseNumber());
            statement.setString(8, customer.getPassword());
            statement.executeUpdate();
        }
    }

    @Override
    public ArrayList<Customer> viewAllCustomers() throws RemoteException, SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM customer");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                customers.add(getCustomer(resultSet));
            }
        }
        return customers;
    }

    private Customer getCustomer(ResultSet resultset) throws SQLException{
        String cpr = resultset.getString(1);
        String firstName = resultset.getString(2);
        String lastName = resultset.getString(3);
        Timestamp date_of_birthStamp = resultset.getTimestamp(4);
        LocalDateTime date_of_birthTime = date_of_birthStamp.toLocalDateTime();
        GregorianCalendar date_of_birth = new GregorianCalendar(date_of_birthTime.getYear(),
                date_of_birthTime.getMonthValue()-1, date_of_birthTime.getDayOfMonth());
        String phoneNumber = resultset.getString(5);
        String email = resultset.getString(6);
        String drivingLicense = resultset.getString(7);
        String password = resultset.getString(8);
        Customer customer = new Customer(firstName, lastName, date_of_birth,
                email, password, phoneNumber, drivingLicense, cpr);
        return customer;
    }


    @Override public void editCustomerInfo(Customer customer, Customer newCustomer)
        throws RemoteException, SQLException
    {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("UPDATE customer SET cpr = ? WHERE cpr = ?");
            statement.setString(1, newCustomer.getCpr_number());
            statement.setString(2, customer.getCpr_number());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE customer SET first_name = ? WHERE cpr = ?");
            statement.setString(1, newCustomer.getFirstName());
            statement.setString(2, customer.getCpr_number());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE customer SET last_name = ? WHERE cpr = ?");
            statement.setString(1, newCustomer.getLastName());
            statement.setString(2, customer.getCpr_number());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE customer SET date_of_birth = ? WHERE cpr = ?");
            Timestamp birthday = new Timestamp(newCustomer.getDateOfBirth().getTimeInMillis());
            statement.setTimestamp(1, birthday);
            statement.setString(2, customer.getCpr_number());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE customer SET phone_number = ? WHERE cpr = ?");
            statement.setInt(1, Integer.parseInt(newCustomer.getPhoneNumber()));
            statement.setString(2, customer.getCpr_number());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE customer SET email = ? WHERE cpr = ?");
            statement.setString(1, newCustomer.getEmail());
            statement.setString(2, customer.getCpr_number());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE customer SET driving_licence = ? WHERE cpr = ?");
            statement.setString(1, newCustomer.getDrivingLicenseNumber());
            statement.setString(2, customer.getCpr_number());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE customer SET password = ? WHERE cpr = ?");
            statement.setString(1, newCustomer.getPassword());
            statement.setString(2, customer.getCpr_number());
            statement.executeUpdate();
        }
    }

    @Override public void createPersonalAccount(Customer customer) throws RemoteException, SQLException {
        addCustomer(customer);
    }

    @Override
    public void createEmployee(Employee employee) throws RemoteException, SQLException {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO employee(cpr, first_name, last_name," +
                    "date_of_birth, phone_number, email, position, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ");
            statement.setString(1, employee.getCpr());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            Timestamp date_of_birth = new Timestamp(employee.getDateOfBirth().getTimeInMillis());
            statement.setTimestamp(4, date_of_birth);
            statement.setString(5, employee.getPhoneNumber());
            statement.setString(6, employee.getEmail());
            if(employee.getPosition().equalsIgnoreCase("manager"))
                statement.setString(7, "MNG");
            else if (employee.getPosition().equalsIgnoreCase("employee"))
                statement.setString(7, "EMP");
            statement.setDouble(8, employee.getSalary());
            statement.executeUpdate();
        }
    }

    @Override
    public void editEmployeeInfo(Employee employee, Employee newEmployee) throws RemoteException, SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("UPDATE employee SET cpr = ? WHERE cpr = ?");
            statement.setString(1, newEmployee.getCpr());
            statement.setString(2, employee.getCpr());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE employee SET first_name = ? WHERE cpr = ?");
            statement.setString(1, newEmployee.getFirstName());
            statement.setString(2, employee.getCpr());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE employee SET last_name = ? WHERE cpr = ?");
            statement.setString(1, newEmployee.getLastName());
            statement.setString(2, employee.getCpr());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE employee SET date_of_birth = ? WHERE cpr = ?");
            Timestamp date_of_birth = new Timestamp(newEmployee.getDateOfBirth().getTimeInMillis());
            statement.setTimestamp(1, date_of_birth);
            statement.setString(2, employee.getCpr());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE employee SET phone_number = ? WHERE cpr = ?");
            statement.setString(1, newEmployee.getPhoneNumber());
            statement.setString(2, employee.getCpr());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE employee SET email = ? WHERE cpr = ?");
            statement.setString(1, newEmployee.getEmail());
            statement.setString(2, employee.getCpr());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE employee SET position = ? WHERE cpr = ?");
            if(newEmployee.getPosition().equalsIgnoreCase("manager"))
                statement.setString(1, "MNG");
            else if (newEmployee.getPosition().equalsIgnoreCase("employee"))
                statement.setString(1, "EMP");
            statement.setString(2, employee.getCpr());
            statement.executeUpdate();
            statement = connection.prepareStatement("UPDATE employee SET salary = ? WHERE cpr = ?");
            statement.setDouble(1, newEmployee.getSalary());
            statement.setString(2, employee.getCpr());
            statement.executeUpdate();
        }

    }

    @Override public ArrayList<Employee> getEmployees()
            throws RemoteException, SQLException
    {
        ArrayList<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
            {
                employees.add(getEmployee(resultSet));
            }
        }
        return employees;
    }

    private Employee getEmployee(ResultSet resultSet) throws SQLException, RemoteException {
        String cpr = resultSet.getString(1);
        String firstName = resultSet.getString(2);
        String lastName = resultSet.getString(3);
        Timestamp date_of_birth = resultSet.getTimestamp(4);
        LocalDateTime date_of_birth_local = date_of_birth.toLocalDateTime();
        GregorianCalendar dateOfBirth = new GregorianCalendar(date_of_birth_local.getYear(), date_of_birth_local.getMonthValue()-1,
                date_of_birth_local.getDayOfMonth());
        String phoneNumber = resultSet.getString(5);
        String email = resultSet.getString(6);
        String position = resultSet.getString(7);
        if(position.equals("MNG"))
            position = "Manager";
        else if(position.equals("EMP"))
            position = "Employee";
        double salary = resultSet.getDouble(8);
        return new Employee(cpr, firstName, lastName, dateOfBirth, phoneNumber, email, salary, position);
    }

    @Override public void deleteEmployee(Employee employee)
        throws RemoteException, SQLException
    {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM employee WHERE cpr = ?");
            statement.setString(1, employee.getCpr());
            statement.executeUpdate();
        }
    }
}
