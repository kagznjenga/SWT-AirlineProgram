import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.fest.swing.fixture.FrameFixture;

import javax.swing.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

  Login myLogin = new Login();

  @BeforeEach
  void setUp() {
//    FrameFixture window = new FrameFixture(new Main());
//    window.show();
  }

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
  void testGUI(){
//    System.setProperty("webdriver.gecko.driver", "C:\\Users\\kagwi\\OneDrive - Florida Gulf Coast University\\Spring 2021\\Software Testing\\geckodriver-v0.29.1-win64\\geckodriver.exe");
//    WebDriver driver1 = new FirefoxDriver();
//    driver1.getWindowHandle();
    //assertTrue(driver1.getTitle().startsWith("google"));


  }

}