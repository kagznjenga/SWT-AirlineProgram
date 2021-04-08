import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class searchCustomerTest{

  searchCustomer myCustomer = new searchCustomer();

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @Test
  void testCustomerSearchById() {
    JTextField myCustId = new JTextField("CS006");
    myCustomer.setTxtcustid(myCustId);
    myCustomer.getSearchCustomer().doClick();

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
    assertEquals("Charles", firstName);
    assertEquals("Miles", lastName);
    assertEquals("HDJ343", nic);
    assertEquals("ABD344666", passport);
    assertEquals("3452 sir dink blvd", address);
    assertEquals("23456788", contact);
    assertEquals("2021-04-08", strDate);
    //assertEquals("Jones", photo);
  }

  @Test
  void testCustomerSearchMock() {
    searchCustomer myCustSearchMock = mock(myCustomer.getClass());
    //doCallRealMethod().when(myCustSearchMock).initComponents();
    //myCustSearchMock.initComponents();

    doAnswer((i) -> {
      assertTrue("Jim".equals(i.getArguments()[0]));
      return null;
            }).when(myCustSearchMock).setFirstname(anyString());

    when(myCustSearchMock.getFirstname()).thenReturn("Jim");

    myCustSearchMock.setFirstname("Jim");
    assertEquals("Jim", myCustSearchMock.getFirstname());

    //verify(myCustSearchMock, times(1)).initComponents();
  }

}