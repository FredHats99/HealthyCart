package JUnit;

import com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions.FailedLoginException;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.LogInBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.LogInBeanClass;
import org.junit.jupiter.api.Test;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.LogInController;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class TestLoginController {
    private static final String FEDERICO = "Cap";
    private static final String CORRECT_PASSWORD = "Cap";

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
