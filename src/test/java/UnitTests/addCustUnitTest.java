import com.toedter.calendar.JDateChooser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class addCustomerUnitTest {

  addCustomer myCustomer = new addCustomer();
  Connection con;

  @BeforeEach
  void setUp() throws SQLException {
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinedb","DBManager","1234");
    myCustomer = new addCustomer();

  }

  @AfterEach
  void tearDown() throws SQLException {
    con.close();
    myCustomer.dispose();
  }

  @Test
  void testAddingCustomerToDBMale() throws ParseException {
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
    myCustomer.getjButton3().doClick();
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
  public void testAutoId() throws SQLException {
    PreparedStatement pst = con.prepareStatement("select * from customer");
    ResultSet rs = pst.executeQuery();
    ResultSetMetaData rsm = rs.getMetaData();
    DefaultTableModel Df = new DefaultTableModel();
    String columns[] = {"","","","","","","","","",""};
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
      v2.add(rs.getString(8));
      v2.add(rs.getString(9));
      v2.add(rs.getBlob(10));
      Df.addRow(v2);
    }
    pst = con.prepareStatement("DELETE FROM customer");
    pst.execute();
    int rows = Df.getRowCount();
    System.out.println(Df.getRowCount());
    System.out.println(Df.getColumnCount());
    myCustomer.autoID();
    myCustomer.autoID();

    pst.close();
    for(int row = 0; row < rows; row++){
      PreparedStatement preparedStatement = con.prepareStatement("insert into customer values (?,?,?,?,?,?,?,?,?,?)");
      System.out.println(row);
      preparedStatement.setString(1, Df.getValueAt(row,0).toString());
      preparedStatement.setString(2, Df.getValueAt(row,1).toString());
      preparedStatement.setString(3, Df.getValueAt(row,2).toString());
      preparedStatement.setString(4,Df.getValueAt(row,3).toString());
      preparedStatement.setString(5, Df.getValueAt(row,4).toString());
      preparedStatement.setString(6, Df.getValueAt(row,5).toString());
      preparedStatement.setString(7, Df.getValueAt(row,6).toString());
      preparedStatement.setString(8,Df.getValueAt(row,7).toString());
      preparedStatement.setString(9,Df.getValueAt(row,8).toString());
      preparedStatement.setBlob(10, (InputStream) Df.getValueAt(row,9));
      preparedStatement.execute();

    }
    assertEquals("CS001",myCustomer.getTxtid().getText());

  }


  @Test
  void testAddingCustomerToDBFemale() throws ParseException {
    JDateChooser dobChooser = new JDateChooser();
    JRadioButton radioButton = new JRadioButton("feMale");
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
    myCustomer.setR2(radioButton);
    myCustomer.setTxtaddress(myAddress);
    myCustomer.setTxtcontact(myContact);
    myCustomer.setTxtfirstname(myfName);
    myCustomer.setTxtlastname(mylName);
    myCustomer.getTxtlastname();
    myCustomer.setTxtnic(myNic);
    myCustomer.getTxtpassport().setText(myPassport.getText());
    myCustomer.getBrowsePic().doClick();

    myCustomer.getAddCustomer().doClick();
    myCustomer.getjButton3().doClick();
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
