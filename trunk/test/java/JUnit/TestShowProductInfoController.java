package JUnit;

import com.cappellinispirito.ispwproject202223jfx.controller.ShowProductInfoController;
import com.cappellinispirito.ispwproject202223jfx.model.exceptions.FailedQueryToOpenFoodFacts;
import com.cappellinispirito.ispwproject202223jfx.model.beansInterface.NameToItemSearchBean;
import com.cappellinispirito.ispwproject202223jfx.view.beans.NameToItemSearchBeanClass;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;


class TestShowProductInfoController {
    public static final String GIORDANO = "Ringo alla Vaniglia";
    boolean result;
    //Giordano Spirito MATRICOLA: 0268514
    @Test
    void TestCorrectShowProductInfo(){
        ShowProductInfoController controller = ShowProductInfoController.getInstance();
        NameToItemSearchBean bean = new NameToItemSearchBeanClass();
        bean.setName(GIORDANO);

        try{
            controller.findProductInfo(bean);
            result = true;
        } catch (Exception e){
            result = false;
        }
        Assertions.assertTrue(result);
    }
}
