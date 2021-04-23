import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class searchCustUnitTest{

  searchCustomer myCustomer;
  Connection con;

  @BeforeEach
  void setUp() throws SQLException {
    myCustomer = new searchCustomer();
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinedb","DBManager","1234");
  }

  @AfterEach
  void tearDown() throws SQLException {
    myCustomer.dispose();
    con.close();
  }

  @Test
  void testImageBrowser(){
    myCustomer.getjButton1().doClick();
    try {
      assertNotEquals(null, myCustomer.getTxtphoto().getIcon());
    } catch (Exception e){
      assertTrue(false);
    }
  }

  @Test
  void testUpdateButton() throws SQLException {
    String id = "CS001";
    String firstname = "joe";
    String lastname = "doe";
    String nic = "123456789";
    String passport = "1234";
    String address = "USA";

    SimpleDateFormat da = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date(1998,06,1);
    String expectedDate = da.format(date);
    String gender = "Male";
    String contact = "1234567890";
    myCustomer.getTxtcustid().setText(id);
    myCustomer.getTxtfirstname().setText(firstname);
    myCustomer.getTxtlastname().setText(lastname);
    myCustomer.getTxtnic().setText(nic);
    myCustomer.getTxtpassport().setText(passport);
    myCustomer.getTxtaddress().setText(address);
    myCustomer.getTxtdob().setDate(date);
    myCustomer.getR1().setSelected(true);
    myCustomer.getTxtcontact().setText(contact);
    myCustomer.getjButton2().doClick();
    PreparedStatement ps = con.prepareStatement("Select * from customer where id = ?");
    ps.setString(1,id);
    boolean found = false;
    ResultSet rs = ps.executeQuery();
    while(rs.next()){
      if ( rs.getString(1).equals(id)
              && rs.getString(2).equals(firstname)
              && rs.getString(3).equals(lastname)
              && rs.getString(4).equals(nic)
              && rs.getString(5).equals(passport)
              && rs.getString(6).equals(address)
              && rs.getString(7).equals(expectedDate)
              && rs.getString(8).equals(gender)
              && rs.getString(9).equals(contact)){
        found = true;
      }
    }
    assertTrue(found);
  }

}
