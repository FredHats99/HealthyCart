package JUnit;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.SearchProductController;
import com.cappellinispirito.ispw_project_202223_jfx.Controller.ShowProductInfoController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.Exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.NameToItemSearchBean;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.ResultsFromSearchBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.NameImageBarcodeFromSearchBeanClass;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.NameToItemSearchBeanClass;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class TestShowProductInfoController {
    public static final String GIORDANO = "Ringo alla Vaniglia";
    boolean result;
    @Test
    public void TestCorrectShowProductInfo() throws FailedQueryToOpenFoodFacts, SQLException, IOException, ParseException {
        ShowProductInfoController controller = ShowProductInfoController.getInstance();
        NameToItemSearchBean bean = new NameToItemSearchBeanClass();
        bean.setName(GIORDANO);

        try{
            controller.findProductInfo(bean);
            result = true;
        } catch (Exception e){
            result = false;
        }
        assertTrue(result);
    }
}
