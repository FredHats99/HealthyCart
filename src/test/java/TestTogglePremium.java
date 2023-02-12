import com.cappellinispirito.ispw_project_202223_jfx.Model.dao.UserAccountDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TestTogglePremium {
    private static final String GIORDANO = "Cap";

    @Test
    public void TestPremiumSwitch(){
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
