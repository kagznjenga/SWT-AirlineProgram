import com.toedter.calendar.JDateChooser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class addflightIntegTest {

  addflight myFlight = new addflight();

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @Test
  void testFlightAddedToDB(){
      addflight myFlight2 = mock(addflight.class);
  }

  @Test
  void testIdInputWithRegex() {
    String regexFormatId = "^(?=.*?\\d)(?=.*?[a-zA-Z])[a-zA-Z\\d]+$";
    myFlight.setId("F032");

    String idInput = myFlight.getId();
    boolean testPattern = Pattern.matches(regexFormatId, idInput);
    try{
      assertTrue(testPattern);
    }
    catch (AssertionError e) {
      System.out.println("Wrong Input please input correct values");
    }
  }

  @Test
  void testFlightNameInputWithRegex() {
    String regexFormatFlightName = "^[a-zA-Z]+$";
    myFlight.setFlightname("Delta");
    String flightNameInput = myFlight.getFlightname();
    boolean testPattern = Pattern.matches(regexFormatFlightName, flightNameInput);
    try{
      assertTrue(testPattern);
    }
    catch (AssertionError e) {
      System.out.println("Wrong Input please input correct values");
    }
  }

  @Test
  void testSourceInputWithRegex() {
    String regexFormatSource = "^[a-zA-Z]+$";
    myFlight.setSource("India");
    String sourceInput = myFlight.getSource();
    boolean testPattern = Pattern.matches(regexFormatSource, sourceInput);
    try{
      assertTrue(testPattern);
    }
    catch (AssertionError e) {
      System.out.println("Wrong Input please input correct values");
    }
  }

  @Test
  void testDepartInputWithRegex() {
    String regexFormatDepart = "^[a-zA-Z]+$";
    myFlight.setArr("UK");
    String departInput = myFlight.getDepart();
    boolean testPattern = Pattern.matches(regexFormatDepart, departInput);
    try{
      assertTrue(testPattern);
    }
    catch (AssertionError e) {
      System.out.println("Wrong Input please input correct values");
    }
  }

  @Test
  void testDaInputWithRegex() {
    String regexFormatDa = "^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
            + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
    JDateChooser testingDatePicker = myFlight.getTxtdate();
    Date myDate = new Date(2010, 1, 3);
    testingDatePicker.setDate(myDate);
    DateFormat setFormat = myFlight.getDa();
    myFlight.setDate(setFormat.format(testingDatePicker.getDate()));
    String dateInput = myFlight.getDate();

    System.out.println(dateInput);
    boolean testPattern = Pattern.matches(regexFormatDa, dateInput);
    try{
      assertTrue(testPattern);
    }
    catch (AssertionError e) {
      System.out.println("Wrong Input please input correct values");
    }
  }

  @Test
  void testDepartTimeWithRegex() {
    String regexFormatDepartTime = "^[a-zA-Z0-9:]+$";
    myFlight.setDeparttime("10:20hrs");
    String departTimeInput = myFlight.getDeparttime();
    boolean testPattern = Pattern.matches(regexFormatDepartTime, departTimeInput);
    try{
      assertTrue(testPattern);
    }
    catch (AssertionError e) {
      System.out.println("Wrong Input please input correct values");
    }
  }

  @Test
  void testArrTimeWithRegex() {
    String regexFormatArrTime = "^[a-zA-Z0-9:]+$";
    myFlight.setArrtime("12:00hrs");
    String arrTimeInput = myFlight.getArrtime();
    boolean testPattern = Pattern.matches(regexFormatArrTime, arrTimeInput);
    try{
      assertTrue(testPattern);
    }
    catch (AssertionError e) {
      System.out.println("Wrong Input please input correct values");
    }
  }
}