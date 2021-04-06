import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class searchCustomerTest{

  searchCustomer myCustomer = new searchCustomer();

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @Test
  void testCustomerSearchById() {
    JTextField myCustId = new JTextField("CS002");
    myCustomer.setTxtcustid(myCustId);
    myCustomer.getjButton4().doClick();

    String firstName = myCustomer.getTxtfirstname().getText();
    String lastName = myCustomer.getTxtlastname().getText();
    String nic = myCustomer.getTxtnic().getText();
    String passport = myCustomer.getTxtpassport().getText();
    String address = myCustomer.getTxtaddress().getText();
    String contact = myCustomer.getTxtcontact().getText();
    Date dob = (myCustomer.getTxtdob().getDate());
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    String strDate = dateFormat.format(dob);
    //String photo = myCustomer.getTxtphoto();
    //System.out.println(lastName);
    assertEquals("Jim", firstName);
    assertEquals("Jones", lastName);
    assertEquals("34324", nic);
    assertEquals("433", passport);
    assertEquals("Africa", address);
    assertEquals("3432423", contact);
    assertEquals("2019-00-14", strDate);
    //assertEquals("Jones", photo);
  }

}