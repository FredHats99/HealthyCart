package JUnit;

import com.cappellinispirito.ispwproject202223jfx.model.dao.UserAccountDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class TestTogglePremium {
    //Giordano Spirito MATRICOLA: 0268514
    @Test
    void TestPremiumSwitch(){
        boolean result;
        UserAccountDAO accountDAO = new UserAccountDAO();
        try{
            boolean isPremium = false;
            accountDAO.updateToPremium("Cap", isPremium);
            result = true;
        } catch (Exception e){
            e.printStackTrace();
            result = false;
        }
        Assertions.assertTrue(result);
    }
}
