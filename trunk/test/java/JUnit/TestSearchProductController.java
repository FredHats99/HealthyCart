package JUnit;

import com.cappellinispirito.ispwproject202223jfx.controller.SearchProductController;
import com.cappellinispirito.ispwproject202223jfx.model.beansInterface.ResultsFromSearchBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.NameImageBarcodeFromSearchBeanClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class TestSearchProductController {

    private static final String GIORDANO = "Ringo";
    //Federico Cappellini MATRICOLA: 0272280
    @Test
    void testCorrectSearchProduct() {
        boolean result;

        SearchProductController controlProducts = SearchProductController.getInstance();
        ResultsFromSearchBean bean  = new NameImageBarcodeFromSearchBeanClass();
        bean.setNameToSearch(GIORDANO);
        try {
            controlProducts.searchProduct(bean);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        assertTrue(result);

    }
}
