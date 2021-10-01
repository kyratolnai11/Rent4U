package Util;

import java.io.Serializable;
import java.util.ArrayList;

public class Vehicle implements Serializable
{
  private String licensePlate;
  private int enginesPower;
  private String type;
  private String make;
  private String model;
  private int year;
  private String typeOfGearbox;
  private String typeOfFuel;
  private int numberOfSeats;
  private double price;
  private ArrayList<Status> statuses;

  public Vehicle(String licensePlate, int enginesPower, String type, String make, String model, int year,
                 String typeOfGearbox, String typeOfFuel, int numberOfSeats, double price){

    this.licensePlate = licensePlate;
    this.enginesPower = enginesPower;
    this.type = type;
    this.make = make;
    this.model = model;
    this.year = year;
    this.typeOfGearbox = typeOfGearbox;
    this.typeOfFuel = typeOfFuel;
    this.numberOfSeats = numberOfSeats;
    this.price = price;
    statuses=new ArrayList<>();

  }



  public void addStatus(Status status)
  {
    statuses.add(status);
  }

  public Status getStatuses()
  {
    return statuses.get(0);
  }

  public void setLicensePlate(String licensePlate)
  {
    this.licensePlate = licensePlate;
  }



  public String getLicensePlate()
  {
    return licensePlate;
  }

  public void setEnginesPower(int enginesPower)
  {
    this.enginesPower = enginesPower;
  }

  public int getEnginesPower()
  {
    return enginesPower;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getType()
  {
    return type;
  }

  public void setMake(String make)
  {
    this.make = make;
  }

  public String getMake()
  {
    return make;
  }

  public void setModel(String model)
  {
    this.model = model;
  }

  public String getModel()
  {
    return model;
  }

  public void setYear(int year)
  {
    this.year = year;
  }

  public int getYear()
  {
    return year;
  }

  public void setPrice(int price)
  {
    this.price = price;
  }

  public double getPrice()
  {
    return price;
  }

  public void setTypeOfFuel(String typeOfFuel)
  {
    this.typeOfFuel = typeOfFuel;
  }

  public String getTypeOfFuel()
  {
    return typeOfFuel;
  }

  public void setTypeOfGearbox(String typeOfGearbox)
  {
    this.typeOfGearbox = typeOfGearbox;
  }

  public String getTypeOfGearbox()
  {
    return typeOfGearbox;
  }

  public int getNumberOfSeats()
  {
    return numberOfSeats;
  }

  public void setNumberOfSeats(int numberOfSeats)
  {
    this.numberOfSeats = numberOfSeats;
  }

  public String toString()
  {
    return licensePlate + " " + type + " " + make + " " + model + " " + year + " "
            + enginesPower + " " + typeOfGearbox + " " + numberOfSeats + " " +
            numberOfSeats + " " + typeOfFuel + " " + price;
  }
}
