import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LoginUnitTest {

  Login myLogin = new Login();

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @Test
  void testLoginCredentials() throws SQLException {
    JTextField userName = new JTextField("john");
    JPasswordField userPass = new JPasswordField();
    userPass.setText("123");
    myLogin.setTxtuser(userName);
    myLogin.setTxtpass(userPass);

    myLogin.getjButton1().doClick();
    ResultSet myResult = myLogin.getRs();
    assertFalse(myResult.next());
  }

  @Test
  void testBlank(){
    myLogin.getjButton1().doClick();
    assertTrue(myLogin.getRs() == null);

  }
  @Test
  void testWrongLoginCredentials() throws SQLException {
    String expectedUserName = "john";
    JTextField userName = new JTextField(expectedUserName);
    String expectedPassword = "123456";
    JPasswordField passwordField = new JPasswordField(expectedPassword);
    myLogin.setTxtuser(userName);
    myLogin.setTxtpass(passwordField);
    myLogin.getjButton1().doClick();
    assertFalse(myLogin.getRs().next());
  }
}