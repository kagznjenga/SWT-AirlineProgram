import com.toedter.calendar.JDateChooser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.table.DefaultTableModel;
import java.io.InputStream;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class addflightTest {

  addflight myFlight = new addflight();
  Connection con;

  @BeforeEach
  void setUp() throws SQLException {
    myFlight = new addflight();
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinedb","DBManager","1234");
  }

  @AfterEach
  void tearDown() throws SQLException {
    con.close();
    myFlight.dispose();
  }

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

  @Test
  public void testAutoId() throws SQLException {
    PreparedStatement pst = con.prepareStatement("select * from flight");
    ResultSet rs = pst.executeQuery();
    ResultSetMetaData rsm = rs.getMetaData();
    DefaultTableModel Df = new DefaultTableModel();
    String columns[] = {"", "", "", "", "", "", "", ""};
    for (String s : columns) {
      Df.addColumn(s);
    }
    while (rs.next()) {
      Vector v2 = new Vector();
      v2.add(rs.getString(1));
      v2.add(rs.getString(2));
      v2.add(rs.getString(3));
      v2.add(rs.getString(4));
      v2.add(rs.getString(5));
      v2.add(rs.getString(6));
      v2.add(rs.getString(7));
      v2.add(rs.getString(8));
      Df.addRow(v2);
    }
    pst = con.prepareStatement("DELETE FROM flight");
    pst.execute();
    int rows = Df.getRowCount();
    System.out.println(Df.getRowCount());
    System.out.println(Df.getColumnCount());
    myFlight.autoID();
    myFlight.autoID();

    pst.close();
    for (int row = 0; row < rows; row++) {
      PreparedStatement preparedStatement = con.prepareStatement("insert into flight values (?,?,?,?,?,?,?,?)");
      System.out.println(row);
      preparedStatement.setString(1, Df.getValueAt(row, 0).toString());
      preparedStatement.setString(2, Df.getValueAt(row, 1).toString());
      preparedStatement.setString(3, Df.getValueAt(row, 2).toString());
      preparedStatement.setString(4, Df.getValueAt(row, 3).toString());
      preparedStatement.setString(5, Df.getValueAt(row, 4).toString());
      preparedStatement.setString(6, Df.getValueAt(row, 5).toString());
      preparedStatement.setString(7, Df.getValueAt(row, 6).toString());
      preparedStatement.setString(8, Df.getValueAt(row, 7).toString());
      preparedStatement.execute();

    }
    assertEquals("FO001", myFlight.getTxtflightid().getText());
  }


    @Test
  public void testJbutton1() throws SQLException {
    String id = "TI001";
    String flightname = "test";

    String source = "India";
    String depart = "India";

    Date date = new Date(2017,6,7);


    String departtime = "7:00PM";
    String arrtime = "8:00PM";
    String flightcharge = "100";
    myFlight.getTxtflightid().setText(id);
    myFlight.getTxtflightname().setText(flightname);
    myFlight.getTxtsource().setSelectedItem(source);
    myFlight.getTxtdepart().setSelectedItem(depart);
    myFlight.getTxtDate().setDate(date);
    myFlight.getTxtdtime().setText(departtime);
    myFlight.getTxtarrtime().setText(arrtime);
    myFlight.getTxtflightcharge().setText(flightcharge);
    myFlight.getjButton1().doClick();
    PreparedStatement ps = con.prepareStatement("select * from flight where id = ?");
    ps.setString(1,id);
    ResultSet rs = ps.executeQuery();
    DateFormat da = new SimpleDateFormat("yyyy-MM-dd");
    boolean found = false;
    myFlight.getjButton2().doClick();
    while(rs.next()){
      if(rs.getString(1).equals(id) &&
              rs.getString(2).equals(flightname) &&
              rs.getString(3).equals(source) &&
              rs.getString(4).equals(depart) &&
              rs.getString(5).equals(da.format(date)) &&
              rs.getString(6).equals(departtime) &&
              rs.getString(7).equals(arrtime) &&
              rs.getString(8).equals(flightcharge)
      ){
        found = true;
        break;
      }
    }
    assertTrue(found);
  }
}
