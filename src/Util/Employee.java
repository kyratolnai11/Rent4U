package Util;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Employee implements Serializable {
  private String cpr;
  private String firstName;
  private String lastName;
  private GregorianCalendar dateOfBirth;
  private String phoneNumber;
  private String email;
  private double salary;
  private String position;

  public Employee(String cpr, String firstName, String lastName, GregorianCalendar dateOfBirth, String phoneNumber,
                  String email, double salary, String position) {
    this.cpr = cpr;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.salary = salary;
    this.position = position;
  }

  public void setCpr(String cpr) {
    this.cpr = cpr;
  }

  public String getCpr() {
    return cpr;
  }

  public void setDateOfBirth(GregorianCalendar dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public GregorianCalendar getDateOfBirth() {
    return dateOfBirth;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getPosition() {
    return position;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public double getSalary() {
    return salary;
  }

  @Override
  public String toString() {
    return "Employee{" +
            "cpr='" + cpr + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", dateOfBirth=" + dateOfBirth +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", email='" + email + '\'' +
            ", salary=" + salary +
            ", position='" + position + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Employee))
      return false;
    Employee temp = (Employee) obj;
    return temp.getSalary() == salary &&
            temp.getCpr().equals(cpr) &&
            temp.getFirstName().equals(firstName) &&
            temp.getLastName().equals(lastName) &&
            temp.getDateOfBirth().equals(dateOfBirth) &&
            temp.getPhoneNumber().equals(phoneNumber) &&
            temp.getEmail().equals(email) &&
            temp.getPosition().equals(position);
  }
}
