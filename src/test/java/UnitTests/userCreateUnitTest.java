import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

public class userCreateUnitTest {
    Connection con;
    Statement s;
    userCreation user;
    ResultSet rs;

    @BeforeEach
    public void setUp() throws SQLException {
        user = new userCreation();
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinedb","DBManager","1234");
        s = con.createStatement();
    }
    @AfterEach
    public void tearDown() throws SQLException {
        user.dispose();
        con.close();
        s.close();
        rs.close();
    }

    @Test
    public void testAutoId() throws SQLException {
        rs = s.executeQuery("select MAX(id) from user");
        rs.next();
        String largestIdValue = rs.getString("MAX(id)");
        user.autoID();
        String newLargestIdValue = user.getTxtuserid().getText();
        int newValue = Integer.parseInt(newLargestIdValue.substring(2));
        int oldValue = Integer.parseInt(largestIdValue.substring(2));
        assertTrue(newValue > oldValue);
    }

    @Test
    public void testAction1Performed() throws SQLException {
        userCreation user = new userCreation();
        user.autoID();
        String userName = "test";
        String firstName = "joe";
        String lastName = "doe";
        String password = "123";
        String id = user.getTxtuserid().getText();
        user.getTxtusername().setText(userName);
        user.getTxtfirstname().setText(firstName);
        user.getTxtlastname().setText(lastName);
        user.getTxtpassword().setText(password);
        user.getjButton1().doClick();
        rs = s.executeQuery("select * from user");
        boolean found = false;
        while(rs.next()){
            if(rs.getString(1).equals(id) &&
                    rs.getString(2).equals(firstName) &&
                    rs.getString(3).equals(lastName) &&
                    rs.getString(4).equals(userName) &&
                    rs.getString(5).equals(password)
            ){
                found = true;
                break;
            }
        }
        assertTrue(found);

    }
}
