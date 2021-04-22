

import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.fixture.JComponentFixture;
import org.assertj.swing.fixture.JLabelFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.core.GenericTypeMatcher;

import static org.junit.jupiter.api.Assertions.*;

class LoginGuiTest {

    FrameFixture window;
    Login frame;

    @BeforeEach
    void setUp() {
        frame = GuiActionRunner.execute(Login::new);
        window = new FrameFixture(frame);
        window.show();
    }

    @AfterEach
    void tearDown() {
        window.cleanUp () ;
    }

    @Test
    void testGUI(){
        window.textBox("UserNameTB").enterText("Samuel");
        window.textBox("UserPass").enterText("911");
        GenericTypeMatcher<JButton> textMatcher = new GenericTypeMatcher<JButton>(JButton.class) {
            @Override protected boolean isMatching(JButton button) {
                return "Login".equals(button.getText());
            }
        };
        window.button(JButtonMatcher.withText("Login")).click();
        window.close();
    }

}