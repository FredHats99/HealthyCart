package JUnit;

import com.cappellinispirito.ispw_project_202223_jfx.Controller.SearchProductController;
import com.cappellinispirito.ispw_project_202223_jfx.Model.beansInterface.ResultsFromSearchBean;
import com.cappellinispirito.ispw_project_202223_jfx.View.beans.NameImageBarcodeFromSearchBeanClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestSearchProductController {

    private static final String GIORDANO = "Ringo";
    //Federico Cappellini MATRICOLA: 0272280
    @Test
    void testCorrectSearchProduct() {
        boolean result;

        SearchProductController controlProducts = SearchProductController.getInstance();
        ResultsFromSearchBean bean  = new NameImageBarcodeFromSearchBeanClass();
        bean.setNameToSearch(GIORDANO);
        try {
            controlProducts.SearchProduct(bean);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        assertTrue(result);

    }
}
