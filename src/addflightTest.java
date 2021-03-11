import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class addflightTest {

  addflight myFlight = new addflight();

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

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
      System.out.println("Wrong Input please input correct values2");
    }
  }

  @Test
  void testFlightNameInputWithRegex() {
    String regexFormatFlightName = "^[a-zA-Z]+$";
    myFlight.setFlightname("Delta");
    String flightNameInput = myFlight.getFlightname();
    boolean testPattern = Pattern.matches(regexFormatFlightName, flightNameInput);
    assertTrue(testPattern);
  }

  @Test
  void testSourceInputWithRegex() {
    String regexFormatSource = "^[a-zA-Z]+$";
    myFlight.setSource("India");
    String sourceInput = myFlight.getSource();
    boolean testPattern = Pattern.matches(regexFormatSource, sourceInput);
    assertTrue(testPattern);
  }

  @Test
  void testDepartInputWithRegex() {
    String regexFormatDepart = "^[a-zA-Z]+$";
    myFlight.setDepart("Delta");
    String departInput = myFlight.getDepart();
    boolean testPattern = Pattern.matches(regexFormatDepart, departInput);
    assertTrue(testPattern);
  }

//  @Test
//  void testDaInputWithRegex() {
//    Pattern regexFormatDa = Pattern.compile("^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
//            + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
//            + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
//            + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$");
//    DateFormat testDate = DateFormat.getDateInstance();
//    String parseToString;
//
//    myFlight.setDa(testDate);
//    DateFormat dateInput = myFlight.getDa();
//    boolean testPattern = regexFormatDa.matcher(parseToString.).matches(dateInput);
//    assertTrue(testPattern);
//  }

  @Test
  void setDa() {}

  @Test
  void getDate() {}

  @Test
  void setDate() {}

  @Test
  void getDeparttime() {}

  @Test
  void setDeparttime() {}

  @Test
  void getArrtime() {}

  @Test
  void setArrtime() {}

  @Test
  void getFlightcharge() {}

  @Test
  void setFlightcharge() {}
}