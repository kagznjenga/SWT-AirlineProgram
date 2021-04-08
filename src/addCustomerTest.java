import com.toedter.calendar.JDateChooser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class addCustomerTest {

  addCustomer myCustomer = new addCustomer();

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @Test
  void testAddingCustomerToDB() throws ParseException {
    JDateChooser dobChooser = new JDateChooser();
    JRadioButton radioButton = new JRadioButton("male");
    radioButton.setSelected(true);
    JTextArea myAddress = new JTextArea("3452 sir dink blvd");
    JTextField myContact = new JTextField("23456788");
    JTextField myfName = new JTextField("Charles");
    JTextField mylName = new JTextField("Miles");
    JTextField myNic = new JTextField("HDJ343");
    JTextField myPassport = new JTextField("ABD344666");
    Date myDob = new Date();
//    SimpleDateFormat dobFormat = new SimpleDateFormat("dd MM yyyy");
//    Date myDob = dobFormat.parse(date);
    dobChooser.setDate(myDob);
    myCustomer.setDateChooser(dobChooser);
    myCustomer.setR1(radioButton);
    myCustomer.setTxtaddress(myAddress);
    myCustomer.setTxtcontact(myContact);
    myCustomer.setTxtfirstname(myfName);
    myCustomer.setTxtlastname(mylName);
    myCustomer.setTxtnic(myNic);
    myCustomer.setTxtpassport(myPassport);
    myCustomer.getBrowsePic().doClick();

    myCustomer.getAddCustomer().doClick();

    assertEquals("CS006", myCustomer.getId());
    assertEquals("Charles", myCustomer.getFirstname());
    assertEquals("Miles", myCustomer.getLastname());
    assertEquals("HDJ343", myCustomer.getNic());
    assertEquals("ABD344666", myCustomer.getPassport());
    assertEquals("3452 sir dink blvd", myCustomer.getAddress());
    assertEquals("2021-04-08", myCustomer.getDate());
    assertEquals("Male", myCustomer.getGender());
    assertEquals("23456788", myCustomer.getContact());
  }



  @Test
  void testIdInputWithRegex() {
    String regexFormatId = "^(?=.*?\\d)(?=.*?[a-zA-Z])[a-zA-Z\\d]+$";
    myCustomer.setId("CS005");

    String idInput = myCustomer.getId();
    boolean testPattern = Pattern.matches(regexFormatId, idInput);
    try{
      assertTrue(testPattern);
    }
    catch (AssertionError e) {
      System.out.println("Wrong Input please input correct values");
    }
  }

  @Test
  void testFirstNameInputWithRegex() {
    String regexFormatId = "^[a-zA-Z]+$";
    myCustomer.setFirstname("Harry");

    String idInput = myCustomer.getFirstname();
    boolean testPattern = Pattern.matches(regexFormatId, idInput);
    try{
      assertTrue(testPattern);
    }
    catch (AssertionError e) {
      System.out.println("Wrong Input please input correct values");
    }
  }

  @Test
  void testLastNameInputWithRegex() {
    String regexFormatId = "^[a-zA-Z]+$";
    myCustomer.setLastname("Harry");

    String idInput = myCustomer.getLastname();
    boolean testPattern = Pattern.matches(regexFormatId, idInput);
    try{
      assertTrue(testPattern);
    }
    catch (AssertionError e) {
      System.out.println("Wrong Input please input correct values");
    }
  }

  @Test
  void testNicInputWithRegex() {
    String regexFormatId = "^[a-zA-Z0-9]+$";
    myCustomer.setNic("738G");

    String idInput = myCustomer.getNic();
    boolean testPattern = Pattern.matches(regexFormatId, idInput);
    try{
      assertTrue(testPattern);
    }
    catch (AssertionError e) {
      System.out.println("Wrong Input please input correct values");
    }
  }

  @Test
  void testPassportInputWithRegex() {
    String regexFormatId = "^[a-zA-Z0-9]+$";
    myCustomer.setPassport("JKD6373");

    String idInput = myCustomer.getPassport();
    boolean testPattern = Pattern.matches(regexFormatId, idInput);
    try{
      assertTrue(testPattern);
    }
    catch (AssertionError e) {
      System.out.println("Wrong Input please input correct values");
    }
  }

  @Test
  void testAddressInputWithRegex() {
    String regexFormatId = "^[a-zA-Z0-9 _]+$";
    myCustomer.setAddress("3563 Hair Do");

    String idInput = myCustomer.getAddress();
    boolean testPattern = Pattern.matches(regexFormatId, idInput);
    try{
      assertTrue(testPattern);
    }
    catch (AssertionError e) {
      System.out.println("Wrong Input please input correct values");
    }
  }

  @Test
  void testDobInputWithRegex() {
    String regexFormatId = "^[a-zA-Z0-9-]+$";
    myCustomer.setDate("06-05-2020");

    String idInput = myCustomer.getDate();
    boolean testPattern = Pattern.matches(regexFormatId, idInput);
    try{
      assertTrue(testPattern);
    }
    catch (AssertionError e) {
      System.out.println("Wrong Input please input correct values");
    }
  }

  @Test
  void testGenderInputWithRegex() {
    String regexFormatId = "^[a-zA-Z]+$";
    myCustomer.setGender("Male");

    String idInput = myCustomer.getGender();
    boolean testPattern = Pattern.matches(regexFormatId, idInput);
    try{
      assertTrue(testPattern);
    }
    catch (AssertionError e) {
      System.out.println("Wrong Input please input correct values");
    }
  }

  @Test
  void testContactInputWithRegex() {
    String regexFormatId = "^[a-zA-Z0-9]+$";
    myCustomer.setContact("3563566");

    String idInput = myCustomer.getContact();
    boolean testPattern = Pattern.matches(regexFormatId, idInput);
    try{
      assertTrue(testPattern);
    }
    catch (AssertionError e) {
      System.out.println("Wrong Input please input correct values");
    }
  }
}