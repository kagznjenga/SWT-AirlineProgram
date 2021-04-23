import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.table.DefaultTableModel;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.util.Vector;

class userCreateUnitTest {
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
    public void testAutoIdEmpty() throws SQLException {
        PreparedStatement pst = con.prepareStatement("select * from user");
        ResultSet rs = pst.executeQuery();
        ResultSetMetaData rsm = rs.getMetaData();
        DefaultTableModel Df = new DefaultTableModel();
        String columns[] = {"", "", "", "", "", ""};
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
            Df.addRow(v2);
        }
        pst = con.prepareStatement("DELETE FROM user");
        pst.execute();
        int rows = Df.getRowCount();
        System.out.println(Df.getRowCount());
        System.out.println(Df.getColumnCount());
        user.autoID();
        user.autoID();

        pst.close();
        for (int row = 0; row < rows; row++) {
            PreparedStatement preparedStatement = con.prepareStatement("insert into user values (?,?,?,?,?)");
            System.out.println(row);
            preparedStatement.setString(1, Df.getValueAt(row, 0).toString());
            preparedStatement.setString(2, Df.getValueAt(row, 1).toString());
            preparedStatement.setString(3, Df.getValueAt(row, 2).toString());
            preparedStatement.setString(4, Df.getValueAt(row, 3).toString());
            preparedStatement.setString(5, Df.getValueAt(row, 4).toString());
            preparedStatement.execute();

        }
        assertEquals("UO001", user.getTxtuserid().getText());
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
        user.getjButton2().doClick();
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
