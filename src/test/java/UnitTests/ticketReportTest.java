package UnitTests;

import classes.ticketreport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class ticketReportTest {
  ticketreport test;
  Connection con;
  @BeforeEach
  void setUp() throws SQLException {
    test = new ticketreport();
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinedb","DBManager","1234");
  }

  @AfterEach
  void tearDown() throws SQLException {
    test.dispose();
    con.close();
  }

  @Test
  public void testTable() throws SQLException {

    PreparedStatement pst = con.prepareStatement("select * from ticket");
    ResultSet rs = pst.executeQuery();
    ResultSetMetaData rsm = rs.getMetaData();
    String columns[] = {"TicketNo",
            "Flight No",
            "Customer ID",
            "Class",  "Price",
            "Seats",
            "Date"};
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
      v2.add(rs.getString(2));
      v2.add(rs.getString(3));
      v2.add(rs.getString(4));
      v2.add(rs.getString(5));
      v2.add(rs.getString(6));
      v2.add(rs.getString(7));
      Df.addRow(v2);
    }
    jTable.setModel(Df);
    test.getjButton1().doClick();
    boolean isSame = true;
    if(jTable.getRowCount() == test.getjTable1().getRowCount()){
      for (int row = 0; row < jTable.getRowCount(); row++){
        for (int column = 0; column < 7; column++){
          if(!jTable.getModel().getValueAt(row,column).toString().trim().equals(test.getjTable1().getModel().getValueAt(row,column).toString().trim())){
            isSame = false;
          }
        }
      }
    } else {
      isSame = false;
    }
    assertTrue(isSame);
    test.LoadData();
  }

}
