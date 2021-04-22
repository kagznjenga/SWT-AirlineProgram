import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class searchCustIntegTest {

  searchCustomer myCustomer = new searchCustomer();

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @Test
  void testCustomerSearchMock() {
    searchCustomer myCustSearchMock = mock(searchCustomer.class);

    doAnswer((i) -> {
      assertTrue("Jim".equals(i.getArguments()[0]));
      return null;
            }).when(myCustSearchMock).setFirstname(anyString());

    when(myCustSearchMock.getFirstname()).thenReturn("Jim");

    myCustSearchMock.setFirstname("Jim");
    assertEquals("Jim", myCustSearchMock.getFirstname());

    System.out.println("Mock Interaction tested successfully");
  }

}