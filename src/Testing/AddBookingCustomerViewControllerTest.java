package Testing;

import Client.Views.AddBookingCustomerView.AddBookingCustomerViewController;
import org.junit.jupiter.api.DisplayName;
import java.util.Calendar;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddBookingCustomerViewControllerTest
{
  AddBookingCustomerViewController addBookingCustomerViewController = new AddBookingCustomerViewController();

  @org.junit.jupiter.api.Test
  @DisplayName("Calculation days between same dates")
  public void testSameDates() {
    assertEquals(1, addBookingCustomerViewController.daysBetween(
        new Date(2021,Calendar.JUNE,2),
        new Date(2021,Calendar.JUNE,2)));
  }

  @org.junit.jupiter.api.Test()
  @DisplayName("Calculation days between different dates")
  public void testDifferentDays() {
    assertEquals(9, addBookingCustomerViewController.daysBetween(
        new Date(2021,Calendar.JUNE,2),
        new Date(2021,Calendar.JUNE,10)));
  }

  @org.junit.jupiter.api.Test()
  @DisplayName("Days between if first is null")
  public void testNullDate() {
    assertThrows(NullPointerException.class,()->
            addBookingCustomerViewController.daysBetween(
                null,
                new Date(2021,Calendar.JUNE,2)));
  }

  @org.junit.jupiter.api.Test()
  @DisplayName("Days between if second is null")
  public void testNullDate2() {
    assertThrows(NullPointerException.class,()->
            addBookingCustomerViewController.daysBetween(
                new Date(2021,Calendar.JUNE,2),
                null));
  }

  @org.junit.jupiter.api.Test()
  @DisplayName("During leap-year")
  public void testLeapYear() {
    assertEquals(4,addBookingCustomerViewController.daysBetween(
        new Date(2020,Calendar.FEBRUARY,27),
        new Date(2020,Calendar.MARCH,1)));
  }

  @org.junit.jupiter.api.Test()
  @DisplayName("During not leap-year")
  public void testNotLeapYear() {
    assertEquals(3,addBookingCustomerViewController.daysBetween(
        new Date(2021, Calendar.FEBRUARY,27),
        new Date(2021,Calendar.MARCH,1)));
  }

  @org.junit.jupiter.api.Test()
  @DisplayName("End of month with 31 days")
  public void testDays31() {
    assertEquals(4,addBookingCustomerViewController.daysBetween(
        new Date(2021,Calendar.MAY,29),
        new Date(2021,Calendar.JUNE,1)));
  }

  @org.junit.jupiter.api.Test()
  @DisplayName("End of month with 30 days")
  public void testDays30() {
    assertEquals(3,addBookingCustomerViewController.daysBetween(
        new Date(2021,Calendar.JUNE,29),
        new Date(2021,Calendar.JULY,1)));
  }
}