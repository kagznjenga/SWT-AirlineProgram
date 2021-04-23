import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class ticketTest {

  ticket test;
  Connection con;
  @BeforeEach
  void setUp() throws SQLException {
    test = new ticket();
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinedb","DBManager","1234");
  }

  @AfterEach
  void tearDown() throws SQLException {
    test.dispose();
    con.close();
  }

  @Test void testJButton() throws SQLException {
    String ticketid = "TO0050";
    String flightid = "FO003";
    String custid = "CS001";
    String flightclass = "Economy";
    String price = "9000";
    String seats = "1";
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String date = LocalDate.now().format(dateFormat);
    test.getTxtticketno().setText(ticketid);
    test.getFlightno().setText(flightid);
    test.getTxtcustid().setText(custid);
    test.getTxtclass().setSelectedItem(flightclass);
    test.getTxtprice().setText(price);
    test.getTxtseats().setValue(Integer.parseInt(seats));
    test.getjButton1().doClick();
    PreparedStatement pst = con.prepareStatement("select * from ticket where id = ?");
    pst.setString(1,ticketid);
    ResultSet rs = pst.executeQuery();
    boolean isFound = false;
    while(rs.next()){
      if (rs.getString(1).equals(ticketid) && rs.getString(2).equals(flightid)
              && rs.getString(3).equals(custid) && rs.getString(4).equals(flightclass) &&
              rs.getString(5).equals(price) && rs.getString(6).equals(seats) &&
              rs.getString(7).equals(date)){
        isFound = true;
      }
    }
    assertTrue(isFound);

  }

  @Test
  public void testJbutton4(){
    String expectedFirstName = "john";
    String expectedLastName = "Alex";
    String expectedPassport = "3443";
    String id = "";
    test.getTxtcustid().setText(id);
    test.getjButton4().doClick();
    assertFalse(test.getTxtfirstname().getText().equals(expectedFirstName)
            && test.getTxtlastname().getText().equals(expectedLastName)
            && test.getTxtpassport().getText().equals(expectedPassport));
  }

  @Test
  public void testJbutton4ElseStatement() throws SQLException {
    String expectedFirstName = "john";
    String expectedLastName = "Alex";
    String expectedPassport = "3443";
    String id = "CS001";
    PreparedStatement ps = con.prepareStatement("Insert into customer (id, firstname, lastname, nic, passport, address, dob, gender, contact, photo) values (?,?,?,?,?,?,?,?,?,?)");
    ps.setString(1, id);
    ps.setString(2, expectedFirstName);
    ps.setString(3, expectedLastName);
    ps.setString(4, "3423222");
    ps.setString(5, expectedPassport);
    ps.setString(6, "Uk");
    ps.setString(7, "1996-06-01");
    ps.setString(8,"Male");
    ps.setString(9,"34324234");
    ps.setString(10,"");
    ps.execute();
    test.getTxtcustid().setText(id);
    test.getjButton4().doClick();
    assertTrue(test.getTxtfirstname().getText().equals(expectedFirstName)
            && test.getTxtlastname().getText().equals(expectedLastName)
            && test.getTxtpassport().getText().equals(expectedPassport));
  }

  @Test
  public void testTxtSeatsChanged(){
    test.getTxtprice().setText("100");
    test.getTxtseats().setValue(10);
    assertEquals(1000,Integer.parseInt(test.getTxttotal().getText()));
    test.getjTable1();
  }

  @Test
  public void testAutoId() throws SQLException {
    PreparedStatement pst = con.prepareStatement("select * from ticket");
    ResultSet rs = pst.executeQuery();
    ResultSetMetaData rsm = rs.getMetaData();
    DefaultTableModel Df = new DefaultTableModel();
    String columns[] = {"","","","","","",""};
    for (String s : columns) {
      Df.addColumn(s);
    }
    while(rs.next())
    {
      Vector v2 = new Vector();
      v2.add(rs.getString(1));
      v2.add(rs.getString(2));
      v2.add(rs.getString(3));
      v2.add(rs.getString(4));
      v2.add(rs.getString(5));
      v2.add(rs.getString(6));
      v2.add(rs.getString(7));
      Df.addRow(v2);
    }
    pst = con.prepareStatement("DELETE FROM ticket");
    pst.execute();
    int rows = Df.getRowCount();
    System.out.println(Df.getRowCount());
    System.out.println(Df.getColumnCount());
    test.autoID();

    pst.close();
    for(int row = 0; row < rows; row++){
      PreparedStatement preparedStatement = con.prepareStatement("insert into ticket (id, flightid, custid, class, price, seats, date) values (?,?,?,?,?,?,?)");
      System.out.println(row);
      preparedStatement.setString(1, Df.getValueAt(row,0).toString());
      preparedStatement.setString(2, Df.getValueAt(row,1).toString());
      preparedStatement.setString(3, Df.getValueAt(row,2).toString());
      preparedStatement.setString(4,Df.getValueAt(row,3).toString());
      preparedStatement.setInt(5, Integer.parseInt(Df.getValueAt(row,4).toString()));
      preparedStatement.setInt(6, Integer.parseInt(Df.getValueAt(row,5).toString()));
      preparedStatement.setString(7, Df.getValueAt(row,6).toString());
      preparedStatement.execute();

    }
    assertEquals("TO001",test.getTxtticketno().getText());

  }

  @Test
  public void testJbutton3() throws SQLException {
    String expectedSource = "India";
    String expectedDepart = "India";
    test.getTxtsource().setSelectedItem(expectedSource);
    test.getTxtdepart().setSelectedItem(expectedDepart);
    test.getjButton3().doClick();
    PreparedStatement pst = con.prepareStatement("SELECT * from flight WHERE source = ? and depart = ?");
    pst.setString(1, expectedSource.trim());
    pst.setString(2, expectedDepart.trim());
    ResultSet rs = pst.executeQuery();
    ResultSetMetaData rsm = rs.getMetaData();
    String columns[] = {"Flight No",
            "Flight Name",
            "Source",
            "Departure",  "Date",
            "DepTime",
            "ArrTime",
            "Charge"};
    JTable jTable = new JTable();
    DefaultTableModel Df = new DefaultTableModel();
    for (String column : columns) {
      Df.addColumn(column);
    }

    Df.setRowCount(0);
    while(rs.next())
    {
      Vector v2 = new Vector();
      v2.add(rs.getString(1));
      System.out.println(rs.getString(1));
      v2.add(rs.getString(2));
      v2.add(rs.getString(3));
      v2.add(rs.getString(4));
      v2.add(rs.getString(5));
      v2.add(rs.getString(6));
      v2.add(rs.getString(7));
      v2.add(rs.getString(8));
      Df.addRow(v2);
    }
    jTable.setModel(Df);
    boolean isSame = true;
    if(jTable.getRowCount() == test.getjTable1().getRowCount()){
      for (int row = 0; row < jTable.getRowCount(); row++){
        for (int column = 0; column < 8; column++){
          if(!jTable.getModel().getValueAt(row,column).toString().trim().equals(test.getjTable1().getModel().getValueAt(row,column).toString().trim())){
            isSame = false;
          }
        }
      }
    } else {
      isSame = false;
    }
    assertTrue(isSame);
  }

  @Test
  public void testJbutton2(){
    test.getjButton2().doClick();
  }
}
