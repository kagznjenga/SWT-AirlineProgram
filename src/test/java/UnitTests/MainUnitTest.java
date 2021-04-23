import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
public class MainUnitTest {
    Main main = new Main();

    @Test
    void testJMenuItem1(){
        main.getjMenuItem1().doClick();
        Component[] component = main.getjDesktopPane1().getComponents();
        boolean found = false;
        for (Component component1 : component) {
            if (component1 instanceof addCustomer){
                found = true;
            }
        }
        assertTrue(found);
    }

    @Test
    void testJmenuItem5(){
        main.getjMenuItem5().doClick();
        Component[] components = main.getjDesktopPane1().getComponents();
        boolean found = false;
        for (Component component1 : components) {
            if (component1 instanceof userCreation){
                found = true;
            }
        }
        assertTrue(found);
    }
    @Test
    void testJMenuItem6(){
        main.getjMenuItem6().doClick();
        Component[] components = main.getjDesktopPane1().getComponents();
        boolean found = false;
        for (Component component1 : components) {
            if (component1 instanceof ticketreport){
                found = true;
            }
        }
        assertTrue(found);
    }
    @Test
    void testJMenuItem4(){
        main.getjMenuItem4().doClick();
        Component[] components = main.getjDesktopPane1().getComponents();
        boolean found = false;
        for (Component component1 : components) {
            if (component1 instanceof addflight){
                found = true;
            }
        }
        assertTrue(found);
    }
    @Test
    void testJMenuItem3(){
        main.getjMenuItem3().doClick();
        Component[] components = main.getjDesktopPane1().getComponents();
        boolean found = false;
        for (Component component1 : components) {
            if (component1 instanceof ticket){
                found = true;
            }
        }
        assertTrue(found);
    }

    @Test
    void testJMenuItem2(){
        main.getjMenuItem2().doClick();
        Component[] components = main.getjDesktopPane1().getComponents();
        boolean found = false;
        for (Component component1 : components) {
            if (component1 instanceof searchCustomer){
                found = true;
            }
        }
        assertTrue(found);
    }
}
