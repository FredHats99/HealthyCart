package JUnit;

import com.cappellinispirito.ispwproject202223jfx.controller.RegistrationController;
import com.cappellinispirito.ispwproject202223jfx.model.beansInterface.RegistrationBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.RegistrationBeanClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLIntegrityConstraintViolationException;


class TestRegistrationController {

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
