import com.toedter.calendar.JDateChooser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.Console;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class addCustIntegTest {

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

    System.out.println("Customer has been added successfully");
  }
}