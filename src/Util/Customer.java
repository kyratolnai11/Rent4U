package Util;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Customer implements Serializable
{
  private String firstName;
  private String lastName;
  private GregorianCalendar dateOfBirth;
  private String email;
  private String password;
  private String phoneNumber;
  private String drivingLicenseNumber;
  private String cpr_number;

  public Customer(){};

  public Customer(String firstName, String lastName, GregorianCalendar dateOfBirth, String email, String password, String phoneNumber, String drivingLicenseNumber, String cpr_number)
  {
    this.firstName=firstName;
    this.lastName=lastName;
    this.dateOfBirth=dateOfBirth;
    this.email=email;
    this.password=password;
    this.phoneNumber=phoneNumber;
    this.drivingLicenseNumber= drivingLicenseNumber;
    this.cpr_number=cpr_number;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public void setDateOfBirth(GregorianCalendar dateOfBirth)
  {
    this.dateOfBirth = dateOfBirth;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  public void setDrivingLicenseNumber(String drivingLicenseNumber)
  {
    this.drivingLicenseNumber = drivingLicenseNumber;
  }

  public void setCpr_number(String cpr_number)
  {
    this.cpr_number = cpr_number;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public GregorianCalendar getDateOfBirth()
  {
    return dateOfBirth;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPassword()
  {
    return password;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getDrivingLicenseNumber()
  {
    return drivingLicenseNumber;
  }

  public String getCpr_number()
  {
    return cpr_number;
  }

  @Override
  public String toString() {
    return "Customer{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", dateOfBirth=" + dateOfBirth +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", drivingLicenseNumber='" + drivingLicenseNumber + '\'' +
            ", cpr_number='" + cpr_number + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Customer)) {
      System.out.println("!!!!!!");
      return false;
    }
    Customer temp = (Customer)obj;
    return temp.getFirstName().equals(firstName) &&
            temp.getLastName().equals(lastName) &&
            temp.getDateOfBirth().equals(dateOfBirth) &&
            temp.getEmail().equals(email) &&
            temp.getPassword().equals(password) &&
            temp.getPhoneNumber().equals(phoneNumber)&&
            temp.getDrivingLicenseNumber().equals(drivingLicenseNumber)&&
            temp.getCpr_number().equals(cpr_number);
  }

}
