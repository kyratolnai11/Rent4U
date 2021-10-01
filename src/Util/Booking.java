package Util;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Booking implements Serializable
{
  private int booking_id;
  private String idOfCustomer;
  private String licencePlate;
  private GregorianCalendar startTime;
  private GregorianCalendar endTime;
  private double price;

  public Booking(String idOfCustomer, String licencePlate, GregorianCalendar startTime, GregorianCalendar endTime, double price){
    this.idOfCustomer = idOfCustomer;
    this.licencePlate = licencePlate;
    this.startTime = startTime;
    this.endTime = endTime;
    this.price = price;
  }

  public Booking(int booking_id, String idOfCustomer, String licencePlate, GregorianCalendar startTime, GregorianCalendar endTime, double price){
    this.booking_id = booking_id;
    this.idOfCustomer = idOfCustomer;
    this.licencePlate = licencePlate;
    this.startTime = startTime;
    this.endTime = endTime;
    this.price = price;
  }

  public void setBooking_id(int booking_id) {
    this.booking_id = booking_id;
  }

  public int getBooking_id() {
    return booking_id;
  }

  public void setPrice(double price)
  {
    this.price = price;
  }

  public double getPrice()
  {
    return price;
  }

  public void setEndTime(GregorianCalendar endTime)
  {
    this.endTime = endTime;
  }

  public GregorianCalendar getEndTime()
  {
    return endTime;
  }

  public void setIdOfCustomer(String idOfCustomer)
  {
    this.idOfCustomer = idOfCustomer;
  }

  public String getIdOfCustomer()
  {
    return idOfCustomer;
  }

  public void setLicencePlate(String licencePlate)
  {
    this.licencePlate = licencePlate;
  }

  public String getLicencePlate()
  {
    return licencePlate;
  }

  public void setStartTime(GregorianCalendar startTime)
  {
    this.startTime = startTime;
  }

  public GregorianCalendar getStartTime()
  {
    return startTime;
  }

  @Override
  public String toString() {
    String str =  booking_id + ", " +idOfCustomer +", "+ licencePlate;
    SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy hh:mm");
    fmt.setCalendar(startTime);
    String startTimeDateFormatted = fmt.format(startTime.getTime());
    fmt.setCalendar(endTime);
    String endTimeDateFormatted = fmt.format(endTime.getTime());
    str += ", startTime = " + startTimeDateFormatted +
            ", endTime = " + endTimeDateFormatted + ", " + price;
    return str;
  }
}
