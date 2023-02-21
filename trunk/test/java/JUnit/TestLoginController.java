package JUnit;

import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedLoginException;
import com.cappellinispirito.ispwproject202223jfx.model.beansinterface.LogInBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.LogInBeanClass;
import org.junit.jupiter.api.Test;

import com.cappellinispirito.ispwproject202223jfx.controller.LogInController;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class TestLoginController {
    private static final String FEDERICO = "Cap";
    private static final String CORRECT_PASSWORD = "Cap";
    //Federico Cappellini MATRICOLA: 0272280
    @Test
    void testLoginCorrectCredentials() {
        boolean result;

        LogInController controlLogin = LogInController.getInstance();

        LogInBean loginBean = new LogInBeanClass();
        loginBean.setUsername(FEDERICO);
        loginBean.setPassword(CORRECT_PASSWORD);

        try {
            controlLogin.checkCredentials(loginBean);
            result = true;
        } catch (FailedLoginException | SQLException e) {
            result = false;
        }

        assertTrue(result);

    }

    @Test
    void testLoginIncorrectPassword(){

        LogInController controlLogin = LogInController.getInstance();
        final String username = "FEDERICO";
        final String wrongPassword = "wrong";

        LogInBean loginBean = new LogInBeanClass();
        loginBean.setUsername(username);
        loginBean.setPassword(wrongPassword);

        assertThrows(FailedLoginException.class, ()->controlLogin.checkCredentials(loginBean));
    }
}
