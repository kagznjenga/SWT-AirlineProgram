import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class ticketUnitTest {

  ticket test;
  Connection con;
  @BeforeEach
  void setUp() throws SQLException {
    test = new ticket();
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinedb","DBManager","1234");
  }

  @AfterEach
  void tearDown() throws SQLException {
    con.close();
  }

  @Test
  public void testCountrySelection() throws SQLException {
    String testDeparture = "India";
    Boolean isFound = false;
    PreparedStatement pst = con.prepareStatement("SELECT depart from flight");
    ResultSet rs = pst.executeQuery();
    while (rs.next()){
      if(testDeparture.equals(rs.getString(1))){
        isFound = true;
      }
    }
    assertTrue(isFound);
  }
  @Test
  public void testCustomerID() throws SQLException {
    String id = "";
    Boolean isFound = false;
    PreparedStatement pst = con.prepareStatement("SELECT id from flight");
    ResultSet rs = pst.executeQuery();
    while(rs.next()){
      if(id == rs.getString(1)){
        isFound = true;
      }
    }
    assertTrue(isFound);

  }
  @Test
  public void testClass(){
    String test = "Economy";
    Boolean isValid;
    if(test.equals("Economy") || test.equals("Business")){
      isValid = true;
    } else {
      isValid = false;
    }
    assertTrue(isValid);

  }
  @Test
  public void testPrice(){
    int test = 100;
    Boolean isValid;
    if (test > 0) {
      isValid = true;
    } else {
      isValid = false;
    }
    assertTrue(isValid);

  }
  @Test
  public void testSeats(){
    int test = 1;
    Boolean isValid;
    if(test >= 1){
      isValid = true;
    } else {
      isValid = false;
    }
    assertTrue(isValid);
  }

}