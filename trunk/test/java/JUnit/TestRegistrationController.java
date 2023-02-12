package JUnit;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.RegistrationController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions.FailedRegistrationException;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.RegistrationBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.RegistrationBeanClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLIntegrityConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRegistrationController {

    private static final String NONREGISTEREDUSER = "Cappellini";
    private static final String NONREGISTEREDPASSWORD = "Federico";
    //Giordano Spirito MATRICOLA: 0268514
    @Test
    void testRegistrationNonExistingUser() throws Exception {

        RegistrationBean registrationBean = new RegistrationBeanClass();
        boolean result;
        registrationBean.setUsername(NONREGISTEREDUSER);
        registrationBean.setPassword(NONREGISTEREDPASSWORD);

        RegistrationController registrationController = new RegistrationController();
        try{
            registrationController.register(registrationBean);
            result = true;
        } catch (SQLIntegrityConstraintViolationException e) {
            result = false;
        }

        Assertions.assertTrue(result);
    }
}
