package Selenium;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

public class TestSearchProduct {

    private WebDriver driver;
    private static final String SEARCH_INPUT = "searchInput";
    JavascriptExecutor js;
    //dada
    @After
    public void tearDown() {
        driver.quit();
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Paola\\IdeaProjects\\HealthyCart\\trunk\\test\\java\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void testSearchProduct() {
        driver.get("https://it.openfoodfacts.org/product/8000500310427/nutella-biscuits");
        driver.manage().window().setSize(new Dimension(945, 1012));
        System.out.println(driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div/div/div/section[2]/div/div/div/div/div/div[6]/ul/li/div/div/table/tbody/tr[2]/td[2]/span")).getText());
        assertThat(driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div/div/div/section[2]/div/div/div/div/div/div[6]/ul/li/div/div/table/tbody/tr[2]/td[2]/span")).getText(), is("24,5 g"));
    }




}
